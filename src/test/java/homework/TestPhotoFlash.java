package homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class TestPhotoFlash {

    private WebDriver driver = null;
    private WebDriverWait webDriverWait = null;
    private static final Logger logger = LogManager.getLogger(TestPhotoFlash.class);

    @BeforeAll
    public static void init(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        driver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void testPhotoFlash() {
        try {
            driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");

            WebElement image = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div/ul[2]/li[1]/span/a/div[1]")));
            image.click();

            WebElement modalImage = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Close')]")));
            // Проверка, что изображение отображается в модальном окне
            if (modalImage.isDisplayed()) {
                logger.info("Изображение успешно открылось в модальном окне.");
            } else {
                logger.error("Изображение не открылось в модальном окне.");
            }

        } catch (Exception e) {
            logger.error("Произошла ошибка во время теста", e);
        }
    }
}