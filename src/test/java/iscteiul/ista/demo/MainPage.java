package iscteiul.ista.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private final WebDriver driver;

    // Item "Developer Tools" no menu principal (versão desktop)
    @FindBy(css = "[data-test='main-menu-item'][data-test-marker='Developer Tools']")
    public WebElement toolsMenu;

    // Botão "Developer Tools" dentro do submenu (em destaque)
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    // BOTÃO CORRETO: "Find your tool" (o clique que estava a falhar antes)
    @FindBy(css = "a[data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    // Botão da pesquisa no header (lupa)
    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Fecha o banner de cookies (se aparecer)
    public void closeCookieBanner() {
        try {
            WebElement cookieBanner = driver.findElement(By.cssSelector(".ch2-container"));
            WebElement acceptBtn = cookieBanner.findElement(By.cssSelector("button"));

            Thread.sleep(1000);
            acceptBtn.click();
            Thread.sleep(500);
        } catch (Exception ignored) {
            // Se não aparecer, não há problema
        }
    }
}
