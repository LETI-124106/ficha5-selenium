package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // espera implícita básica
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // espera explícita para elementos dinâmicos
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.jetbrains.com/");

        // inicializa o Page Object com os elementos da página
        mainPage = new MainPage(driver);

        // Tentar fechar um eventual banner de cookies, sem rebentar o teste
        try {
            // tentativa genérica: procurar primeiro botão que fale em cookies
            java.util.List<WebElement> cookieButtons =
                    driver.findElements(By.xpath("//button[contains(translate(., 'COOKIES', 'cookies'), 'cookies')]"));

            if (!cookieButtons.isEmpty()) {
                cookieButtons.get(0).click();
            }
        } catch (Exception ignored) {
            // Se não encontrar nada, seguimos na mesma
        }


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // 1) Teste à funcionalidade de pesquisa
    @Test
    public void search() throws InterruptedException {
        // clicar na lupa
        mainPage.searchButton.click();



        // campo de pesquisa — vamos assumir que é um <input type="search">
        WebElement searchField = driver.findElement(By.cssSelector("[data-test-id='search-input']"));


        searchField.sendKeys("Selenium");



        // botão que executa a pesquisa
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-test='full-search-button']")
                )
        );
        submitButton.click();

        // na página de resultados, procuramos novamente o mesmo tipo de input
        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test-id='search-input']"));




        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    // 2) Teste de abrir o menu Tools
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

    // 3) Teste de navegação até “All Developer Tools…”
    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("products-page")
                )
        );

        assertTrue(productsList.isDisplayed());
        assertEquals(
                "All Developer Tools and Products by JetBrains",
                driver.getTitle()
        );
    }
}
