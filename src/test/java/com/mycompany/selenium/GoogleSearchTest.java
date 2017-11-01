package com.mycompany.selenium;


import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Feature("Google Search")
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

    @AfterMethod
    public void tearDown(ITestResult testResult) throws Exception {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test failed: " + testResult.getMethod().getMethodName());
            takeScreenShot(testResult.getMethod().getMethodName());
        }

        driver.quit();
    }

    @Test
    @Story("Cheese Search")
    @Issue("ALR-123")
    @TmsLink("TMS-123")
    public void testSearchCheese() throws Exception {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("cheese", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("cheese"));
        assertTrue(driver.getTitle().contains("cheese"));
    }


    @Test
    @Flaky
    @Description("Some detailed test description")
    @Story("Apple Search")
    @Severity(SeverityLevel.CRITICAL)

    @Issues({
            @Issue("MYISSUE-1"),
            @Issue("MYISSUE-2")
    })
    @TmsLink("TMS-123")
    public void testSearchApple() throws Exception {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("apple", Keys.ENTER);
        new WebDriverWait(driver, 3).until(ExpectedConditions.titleContains("apple"));
        assertTrue(false, "Purposefully fail this test in order to generate screenshot");
    }


    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName) {
        System.out.println("Taking screenshot for failed test: " + methodName);
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}
