package com.mycompany.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        //No need to set Driver path since driver executable path will be set during Selenium Grid setup
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.30-win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\s1eamk\\AppData\\Local\\Microsoft\\AppV\\Client\\Integration\\590455D7-2D2E-48A6-9412-DD2A08B56F24\\Root\\VFS\\ProgramFilesX86\\Google\\Chrome\\Application\\chrome.exe");

        driver = new ChromeDriver(options);

//        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver-v0.17.0-win32/geckodriver.exe");
//        driver = new FirefoxDriver();
//        DesiredCapabilities cap = DesiredCapabilities.firefox();
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

        driver.get("http://www.google.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(groups = {"smoke"})
    public void testSearchCheese() throws Exception {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("cheese", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("cheese"));
        Thread.sleep(5000);
        assertTrue(driver.getTitle().contains("cheese"));
    }

    @Test(groups = {"smoke", "regression"})
    public void testSearchApple() throws Exception {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("apple", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("apple"));
        Thread.sleep(5000);
        assertTrue(driver.getTitle().contains("apple"));
    }


}
