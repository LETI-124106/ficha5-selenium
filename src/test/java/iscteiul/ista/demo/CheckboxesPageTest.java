package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboxesPageTest {

    private WebDriver driver;
    private CheckboxesPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        page = new CheckboxesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCheckboxes() {
        // Checkbox 1 deve estar desmarcado → marcar
        if (!page.checkbox1.isSelected()) {
            page.checkbox1.click();
        }
        assertTrue(page.checkbox1.isSelected());

        // Checkbox 2 deve estar marcado → desmarcar
        if (page.checkbox2.isSelected()) {
            page.checkbox2.click();
        }
        assertFalse(page.checkbox2.isSelected());
    }
}