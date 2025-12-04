package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationMessagesPageTest {


    private WebDriver driver;
    private NotificationMessagesPage notifPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        notifPage = new NotificationMessagesPage(driver);
        notifPage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void clickingLinkShowsValidNotificationMessage() {
        notifPage.triggerNotification();
        String message = notifPage.getMessageText();

        // mensagens possíveis na página (não são sempre iguais)
        Set<String> validMessages = Set.of(
                "Action successful",
                "Action unsuccesful, please try again", // sim, está mesmo mal escrito no site
                "Action unsuccessful, please try again"
        );

        assertTrue(validMessages.stream().anyMatch(message::contains),
                "Mensagem inesperada: " + message);
    }
}
