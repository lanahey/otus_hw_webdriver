import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertSame;

public class myFirstTest {

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger(myFirstTest.class);

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Tag("auth")
    @Test
    void test() {
        driver.get("https://otus.ru/");
    }

    @Tag("auth")
    @Test
    void test05() {

        driver.get("https://otus.ru");
        driver.manage().addCookie(new Cookie("Otus1","Value1"));
        driver.manage().addCookie(new Cookie("Otus2","Value2"));
        Cookie cookie3 = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(cookie3);
    }

    @Disabled
    @Test
    void test1() {
        
    }
    @Tag("param")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test2(int arg) {
        driver.manage().window().setSize(new Dimension(1000,600));
        System.out.println("новый тест с параметром" + arg);
        //assertSame(arg, 2);
        logger.error("Это ERROR сообщение");

    }

    @AfterEach
    void tearDown() {
        if (driver != null)
            driver.quit();

    }
}
