package iscteiul.ista.demo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private final WebDriver driver;

    // =========================================================
    // ELEMENTOS PRINCIPAIS
    // =========================================================

    // Botão para abrir barra de pesquisa
    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    // Item do menu Developer Tools
    @FindBy(xpath = "//*[@data-test='main-menu-item' and @data-test-marker='Developer Tools']")
    public WebElement toolsMenu;

    // Botão "See Developer Tools" na página inicial
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    // Botão "Find Your Tools" na página seguinte
    @FindBy(xpath = "//*[@data-test='suggestion-action']")
    public WebElement findYourToolsButton;


    // =========================================================
    // CONSTRUTOR
    // =========================================================
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // =========================================================
    // REMOVER QUALQUER COOKIE BANNER
    // =========================================================
    public void removeCookieBannerIfPresent() {
        try {
            Thread.sleep(300); // esperar para o banner ser injetado

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // remover todos os possíveis banners do site JetBrains
            js.executeScript(
                    "document.querySelectorAll('.ch2-container, .cookie-notification, #gdpr-consent-tool, .consent-container')" +
                            ".forEach(e => e.remove());"
            );

        } catch (Exception ignored) {}
    }
}
