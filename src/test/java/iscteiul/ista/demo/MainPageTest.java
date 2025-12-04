package iscteiul.ista.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.jetbrains.com/");
        mainPage = new MainPage(driver);

        mainPage.removeCookieBannerIfPresent();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================
    // SEARCH TEST
    // =========================================================

    @Test
    public void search() {

        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();

        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test-id='search-input']")
                )
        );

        searchField.sendKeys("Selenium");

        // remover popup de auto-sugestões
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('[data-test=\"search-suggestion-popup\"]').forEach(e => e.remove());"
        );

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-test='full-search-button']")
                )
        );

        submitButton.click();

        wait.until(ExpectedConditions.urlContains("Selenium"));
        assertTrue(driver.getCurrentUrl().contains("Selenium"));
    }

    // =========================================================
    // TOOLS MENU TEST
    // =========================================================

    @Test
    public void toolsMenu() {

        mainPage.removeCookieBannerIfPresent();

        mainPage.toolsMenu.click(); // clique funciona na versão atual do site

        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[data-test-marker='Developer Tools'] div[class*='submenuWrapper']")
                )
        );

        assertTrue(menuPopup.isDisplayed());
    }

    // =========================================================
    // NAVIGATION TO ALL TOOLS TEST
    // =========================================================

    @Test
    public void navigationToAllTools() {

        mainPage.removeCookieBannerIfPresent();

        // 1) clicar no primeiro botão Developer Tools
        mainPage.seeDeveloperToolsButton.click();

        mainPage.removeCookieBannerIfPresent(); // pode reaparecer nesta página

        // 2) clicar no verdadeiro link que funciona, não no botão bloqueado
        WebElement suggestionLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-test='suggestion-link']")
                )
        );
        suggestionLink.click();

        // 3) esperar pela página final "All Tools"
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
