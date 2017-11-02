package com.mycompany.selenium;


import com.mycompany.selenium.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTest{

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;
    private WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory driverFactory = new DriverFactory();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverFactory.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.33-win32/chromedriver.exe");
//        driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver-v0.17.0-win32/geckodriver.exe");
        driver = getDriver();

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
