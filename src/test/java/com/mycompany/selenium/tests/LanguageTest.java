package com.mycompany.selenium.tests;

import com.mycompany.selenium.pageobejcts.TopMenuBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.mycompany.selenium.enums.Language.LV;
import static com.mycompany.selenium.enums.Language.RU;
import static org.testng.AssertJUnit.assertEquals;


public class LanguageTest {
    private TopMenuBar topMenuBar;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {

//        System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver-v2.30-win32/chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("C:\\Users\\s1eamk\\AppData\\Local\\Microsoft\\AppV\\Client\\Integration\\590455D7-2D2E-48A6-9412-DD2A08B56F24\\Root\\VFS\\ProgramFilesX86\\Google\\Chrome\\Application\\chrome.exe");
//
//        driver = new ChromeDriver(options);

//        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver-v0.17.0-win32/geckodriver.exe");
//        driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.33-win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\s1eamk\\AppData\\Local\\Microsoft\\AppV\\Client\\Integration\\590455D7-2D2E-48A6-9412-DD2A08B56F24\\Root\\VFS\\ProgramFilesX86\\Google\\Chrome\\Application\\chrome.exe");

        driver = new ChromeDriver(options);


        driver.manage().window().maximize();
        driver.get("http://ss.com");
    }

    @AfterMethod
    public void teardown() throws Exception {

        driver.quit();
    }

    @Test
    public void testInitialLanguage() throws Exception {
        //Arrange
        topMenuBar = new TopMenuBar(driver);

        //Assert
        assertEquals("SLUDINĀJUMI", topMenuBar.getPageHeaderHeadText());
    }

    @Test
    public void testSwitchingLanguageToRu() throws Exception {
        //Arrange
        topMenuBar = new TopMenuBar(driver);


        //Act
        topMenuBar.switchLangTo(RU);

        //Assert
        assertEquals("ОБЪЯВЛЕНИЯ", topMenuBar.getPageHeaderHeadText());
    }

    @Test
    public void testSwitchingLanguageToLv() throws Exception {
        //Arrange

        topMenuBar = new TopMenuBar(driver);

        //Act
        topMenuBar.switchLangTo(RU);
        topMenuBar.switchLangTo(LV);

        //Assert
        assertEquals("SLUDINĀJUMI", topMenuBar.getPageHeaderHeadText());
    }
}
