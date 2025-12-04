package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InputsPageTest {

    private WebDriver driver;
    private InputsPage inputsPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        inputsPage = new InputsPage(driver);
        inputsPage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void userCanTypeNumberInInput() {
        String expected = "12345";

        inputsPage.setNumber(expected);

        assertEquals(expected, inputsPage.getNumber());
    }



}
