package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

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
        driver.get("https://www.jetbrains.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);

        // fechar cookies
        mainPage.closeCookieBanner();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {

        mainPage.searchButton.click();

        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-test-id='search-input']")
                )
        );

        searchField.sendKeys("Selenium");

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-test='full-search-button']")
                )
        );
        submitButton.click();

        WebElement searchPageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-test-id='search-input']")
                )
        );

        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {

        Actions actions = new Actions(driver);

        // garantir que o item "Developer Tools" está visível antes do hover
        WebElement devToolsMenu = wait.until(
                ExpectedConditions.visibilityOf(mainPage.toolsMenu)
        );

        // passar o rato por cima do "Developer Tools"
        actions.moveToElement(devToolsMenu).perform();

        // esperar que apareça um elemento com o texto "CLion" dentro do submenu
        WebElement clionItem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[text()='CLion']")
                )
        );

        assertTrue(clionItem.isDisplayed());
    }


    @Test
    public void navigationToAllTools() {

        Actions actions = new Actions(driver);

        // 1. Hover para abrir o submenu
        actions.moveToElement(mainPage.toolsMenu).perform();

        // 2. Clicar no botão “Developer Tools”
        WebElement devToolsBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[text()='Developer Tools']")
                )
        );
        devToolsBtn.click();

        // 3. Clicar no link “Find your tool”
        WebElement findToolBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[data-test='suggestion-link']")
                )
        );
        findToolBtn.click();

        // 4. Esperar pela página final
        WebElement productsPage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("products-page")
                )
        );

        assertTrue(productsPage.isDisplayed());

        // 5. Verificar título
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
