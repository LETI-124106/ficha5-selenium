package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        mainPage = new MainPage(driver);
        driver.get("https://www.jetbrains.com/");

        try {
            WebElement acceptButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Accept') or contains(text(),'Got it')]")));
            if (acceptButton.isDisplayed()) acceptButton.click();
        } catch (Exception e) {
            // Ignorar banner
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void search() {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();

        WebElement searchField = wait.until(ExpectedConditions.visibilityOf(mainPage.searchField));
        searchField.click();
        searchField.clear();
        searchField.sendKeys("Selenium");

        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchSubmitButton)).click();

        boolean urlChanged = wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("q=Selenium"),
                ExpectedConditions.urlContains("s=Selenium")
        ));
        assertTrue(urlChanged, "A pesquisa falhou.");
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[data-test-marker='Developer Tools'] div[class*='submenuWrapper']")
                )
        );

        assertTrue(menuPopup.isDisplayed());
    }
    @Test
    public void navigationToAllTools() {

        mainPage.removeCookieBannerIfPresent();

        // 1. abrir o menu Developer Tools
        mainPage.seeDeveloperToolsButton.click();

        mainPage.removeCookieBannerIfPresent(); // pode reaparecer nesta página

        // 2. clicar no link "Find your tool" correto (o invisível bloqueia)
        WebElement suggestionLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-test='suggestion-link']")
                )
        );
        suggestionLink.click();

        // 3. esperar pela página All Tools
        WebElement productsPage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("products-page")
                )
        );

        assertTrue(productsPage.isDisplayed());
        assertEquals(
                "All Developer Tools and Products by JetBrains",
                driver.getTitle()
        );
    }


}