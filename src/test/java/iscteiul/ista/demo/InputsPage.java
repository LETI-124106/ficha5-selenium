package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputsPage {

    private WebDriver driver;

    // página: https://the-internet.herokuapp.com/inputs
    @FindBy(css = "input[type='number']")
    private WebElement numberInput;

    public InputsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/inputs");
    }

    public void setNumber(String value) {
        numberInput.clear();
        numberInput.sendKeys(value);
    }

    public String getNumber() {
        return numberInput.getAttribute("value");
    }

}
