package iscteiul.ista.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotificationMessagesPage {

    private WebDriver driver;

    // página: https://the-internet.herokuapp.com/notification_message_rendered

    @FindBy(linkText = "Click here")
    private WebElement clickHereLink;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public NotificationMessagesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
    }

    public void triggerNotification() {
        clickHereLink.click();
    }

    public String getMessageText() {
        // o texto vem com um "×" no fim, convém limpar
        return flashMessage.getText().replace("×", "").trim();
    }
}
