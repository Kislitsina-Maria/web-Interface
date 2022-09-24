package ru.netologe.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestOrderCard {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void ShouldTest() {
        driver.get("http://localhost:7777");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Мария Кислицина");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79030248197");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();

        String actual = driver.findElement(By.className("paragraph")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        Assertions.assertEquals(expected, actual);


    }
}
