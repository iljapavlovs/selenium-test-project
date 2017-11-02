package com.mycompany.selenium.tests;


import com.mycompany.selenium.enums.Language;
import com.mycompany.selenium.enums.Location;
import com.mycompany.selenium.enums.TimePeriod;
import com.mycompany.selenium.pageobejcts.SearchPage;
import com.mycompany.selenium.pageobejcts.SearchResultPage;
import com.mycompany.selenium.pageobejcts.TopMenuBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class SomethingTest {
    /**
     * TAKEAWAYS:
     * *Page Object* methods should be named as from Business logic perspective, more abstract -
     * avoid naming methods as "clickOnSaveButton()"
     * it helps to make your test more readable and more maintainable
     * Page Object returns some info about the page (e.g. new page object, text from page, a boolean result for some check, etc. -
     * never a WebElement (but there might be some exceptions - like with `searchResultElements`)
     * Put explicit wait into Page Object Constructor
     * in order to wait until page ready state
     * or you can use Loadable Component pattern
     * Repeating actions in multiple Page Objects should be put into separate helper class (as with Select example)
     * Make method static so that there will be no need to initialize an instance
     * Recommended to return other Page Objects in a method which end up going to another page
     * If a method stays on the same page, then return the same Page Object ("return this").
     * This will make your tests more "fluent" (https://en.wikipedia.org/wiki/Fluent_interface)
     * Keep folder structure clean
     */


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
    public void testSmth() throws Exception {
        TopMenuBar topMenuBar = new TopMenuBar(driver);
        topMenuBar.switchLangTo(Language.RU);

        assertEquals(topMenuBar.getPageHeaderHeadText(), "ОБЪЯВЛЕНИЯ");


    }

    @Test
    public void testSearch() throws Exception {
        TopMenuBar topMenuBar = new TopMenuBar(driver);
        SearchPage searchPage = topMenuBar
                .switchLangTo(Language.RU)
                .goToSearch();


        SearchResultPage searchResultPage = searchPage.searchForPhrase("Компьютер")
                .selectSubDivision("Электротехника")
                .selectCategory("Компьютеры, оргтехника")
                .selectLocation(Location.RIGA)
                .selectTimePeriod(TimePeriod.ALL_PERIOD)
                .performSearch();

        assertTrue(searchResultPage.getSearchResultElementsCount() > 0);
        assertTrue(searchResultPage.searchResultElements.get(0).isDisplayed());


    }

}
