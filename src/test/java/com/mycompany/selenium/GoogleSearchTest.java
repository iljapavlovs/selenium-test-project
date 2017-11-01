package com.mycompany.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTest{

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.33-win32/chromedriver.exe");
//        driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver-v0.17.0-win32/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.get("http://www.google.com");
    }

    @Test
    public void testSearch() throws Exception {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("cheese", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("cheese"));
        assertTrue(driver.getTitle().contains("cheese"));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
