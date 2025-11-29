package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DropdownPage {

    private WebDriver driver;

    @FindBy(id = "dropdown")
    public WebElement dropdown;

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}