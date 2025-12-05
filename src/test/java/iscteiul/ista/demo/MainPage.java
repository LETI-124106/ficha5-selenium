package iscteiul.ista.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ============================================
    //               SEARCH ELEMENTS
    // ============================================

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    @FindBy(css = "[data-test-id='search-input']")
    public WebElement searchField;

    @FindBy(css = "button[data-test='full-search-button']")
    public WebElement searchSubmitButton;

    // ============================================
    //                  MENU TOOLS
    // ============================================

    @FindBy(xpath = "//div[@data-test='main-menu-item' and @data-test-marker='Developer Tools']")
    public WebElement toolsMenu;

    @FindBy(css = "div[data-test-marker='Developer Tools'] div[class*='submenuWrapper']")
    public WebElement mainSubmenu;

    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(css = "[data-test='site-nav-dropdown-action-all-tools']")
    public WebElement findYourToolsButton;

    // ============================================
    //      COOKIE BANNER (REMOVE IF PRESENT)
    // ============================================

    public void removeCookieBannerIfPresent() {
        try {
            WebElement cookieBanner = driver.findElement(
                    By.cssSelector("div.ch2-container, div[data-test='cookie-banner']")
            );

            WebElement acceptButton = cookieBanner.findElement(
                    By.xpath(".//button[contains(text(),'Accept') or contains(text(),'Got it')]")
            );

            if (acceptButton.isDisplayed()) {
                acceptButton.click();
            }

        } catch (Exception ignored) {
            // Banner não apareceu → ignorar
        }
    }
}
