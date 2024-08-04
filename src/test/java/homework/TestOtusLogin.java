package homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.Set;

public class TestOtusLogin {

    private WebDriver driver = null;
    private WebDriverWait webDriverWait = null;
    private static final Logger logger = LogManager.getLogger(TestOtusLogin.class);

    @BeforeAll
    public static void init(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void testOtusLogin() {

        try {
            driver.get("https://otus.ru");

            WebElement loginButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/section/div/button")));
            loginButton.click();

            // Ожидание полей авторизации
            WebElement usernameField = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div")));
            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/div/div[2]/div"));
            WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/button"));

            // Вводим данные для авторизации
            usernameField.sendKeys("testuser@example.com"); // Используйте реальные данные
            passwordField.sendKeys("testpassword"); // Используйте реальные данные
            submitButton.click();

            // Ожидание завершения авторизации
            webDriverWait.until(ExpectedConditions.urlContains("dashboard")); // Замените на реальный URL после авторизации

            // Получаем все cookies
            Set<Cookie> cookies = driver.manage().getCookies();
            for (Cookie cookie : cookies) {
                logger.info("Cookie: Name=" + cookie.getName() + ", Value=" + cookie.getValue());
            }

        } catch (Exception e) {
            logger.error("Произошла ошибка во время теста", e);
        } finally {
            // Закрываем браузер
            driver.quit();
        }
    }
}