package com.mycompany.selenium.tests;


import com.mycompany.selenium.enums.Location;
import com.mycompany.selenium.pageobjects.SearchPage;
import com.mycompany.selenium.pageobjects.SearchResultPage;
import com.mycompany.selenium.pageobjects.TopMenuBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SomethingTest {


    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.33-win32/chromedriver.exe");
        driver = new ChromeDriver();


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
        topMenuBar.changeLanguage();

        assertEquals(topMenuBar.getPageHeaderHeadText(), "ОБЪЯВЛЕНИЯ");


    }

    @Test
    public void testSmth2() throws Exception {
        TopMenuBar topMenuBar = new TopMenuBar(driver);
        topMenuBar.changeLanguage();
        topMenuBar.goToSearch();
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchForPhrase("Компьютер");
        searchPage.selectSubDivision("Электротехника");
        searchPage.selectSubDivision2("Компьютеры, оргтехника");
        //searchPage.selectRegion("Рига");
        searchPage.selectRegion(Location.RIGA);
        searchPage.submitSearch();
        //Thread.sleep(5000);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        assertTrue(searchResultPage.getSearchResultElementsCount() > 0);
        assertTrue(searchResultPage.searchResultElements.get(0).isDisplayed());

    }

}
