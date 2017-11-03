package com.mycompany.selenium;


import com.mycompany.selenium.core.DriverFactory;
import com.mycompany.selenium.listeners.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mycompany.selenium.constants.Constants.APP_URL;

@Listeners(ScreenshotListener.class)
public class TestBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;
    protected WebDriver driver;

    public static WebDriver getDriver() throws Exception {
        return driverFactory.get().getDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void instantiateDriverObject() throws Exception {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory driverFactory = new DriverFactory();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };

        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(APP_URL);

    }

    @AfterMethod(alwaysRun = true)
    public void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }


}
