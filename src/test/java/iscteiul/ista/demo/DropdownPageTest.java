package iscteiul.ista.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DropdownPageTest {

    private WebDriver driver;
    private DropdownPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        page = new DropdownPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDropdown() {
        Select select = new Select(page.dropdown);

        select.selectByVisibleText("Option 1");
        assertEquals("1", select.getFirstSelectedOption().getAttribute("value"));

        select.selectByVisibleText("Option 2");
        assertEquals("2", select.getFirstSelectedOption().getAttribute("value"));
    }
}