package homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.time.Duration;
import java.util.List;

public class TestDuckDuckGoSearch {

    private WebDriver driver = null;
    private WebDriverWait webDriverWait = null;


    private static final Logger logger = LogManager.getLogger(TestDuckDuckGoSearch.class);

    @BeforeAll
    public static void init(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        //driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void testDuckDuckGoSearch() {
        try {
            driver.get("https://duckduckgo.com/");

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("ОТУС");
            searchBox.submit();

            List<WebElement> results = null;
            try {
                results = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Онлайн‑курсы для профессионалов, дистанционное обу')]")));
                System.out.println("Элементы найдены");
            } catch (Exception e) {
                System.err.println("Элементы не найдены");
            }

            if (results != null && !results.isEmpty()) {
                String firstResultText = results.get(0).getText();
                System.out.println(firstResultText);
                if (firstResultText.contains("Онлайн‑курсы для профессионалов, дистанционное обучение")) {
                    System.out.println("Тест пройден: первый результат соответствует ожиданиям");
                } else {
                    System.err.println("Тест не пройден: первый результат не соответствует ожиданиям");
                }
            }
        } catch (Exception e) {
            System.err.println("Произошла ошибка во время теста: " + e.getMessage());
        }
    }
}