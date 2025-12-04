package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {

    // Botão de "Developer Tools" no topo
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    // Botão / link que realmente recebe o clique ("Find your tool")
    @FindBy(xpath = "//*[@data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    // Item de menu "Developer Tools" no menu principal (o botão que abre o submenu)
    @FindBy(css = "nav[data-test='main-menu'] div[data-test-marker='Developer Tools'] button[data-test='main-menu-item-action']")
    public WebElement toolsMenu;

    // Botão de pesquisa (lupa) no cabeçalho
    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
