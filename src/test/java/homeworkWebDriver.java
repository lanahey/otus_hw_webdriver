import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class homeworkWebDriver {

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger(homeworkWebDriver.class);

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void testDuckGoSearch() {

    }

    @Test
    void testOtusLogin() {
    }

    @Test
    void testPhotoFlash() {
    }

    @AfterEach
    void tearDown() {
        if (driver != null)
            driver.quit();

    }
}