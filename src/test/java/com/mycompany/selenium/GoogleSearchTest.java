package com.mycompany.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.krb5.internal.crypto.Des;

import java.net.URL;
import java.rmi.Remote;

import static org.junit.Assert.assertTrue;


public class GoogleSearchTest{

    private WebDriver driver;

    @BeforeClass
    public static void globalSetUp() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.33-win32/chromedriver.exe");

        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver-v0.17.0-win32/geckodriver.exe");

    }

    @Before
    public void setUp() throws Exception {
//        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        DesiredCapabilities cap = DesiredCapabilities.firefox();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

        driver.get("http://www.google.com");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testSearchCheese() throws Exception {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("cheese", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("cheese"));
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("cheese"));
    }

    @Test
    public void testSearchApple() throws Exception {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("apple", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("apple"));
        Thread.sleep(3000);
        assertTrue(driver.getTitle().contains("apple"));
    }


}
