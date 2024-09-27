package com.mycompany.selenium;


import com.mycompany.selenium.enums.Location;
import com.mycompany.selenium.enums.Period;
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

public class CleanCodeTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.30-win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://ss.com");
    }


    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void clickLanguage() throws Exception {
        TopMenuBar topMenuBar = new TopMenuBar(driver);
        topMenuBar.changeLanguage();

        assertEquals(topMenuBar.getLanguageLink().getAttribute("title"), "Latviski");
        assertEquals(topMenuBar.getPageHeaderHeadText(), "ОБЪЯВЛЕНИЯ");

    }

    @Test
    public void openSearchForm() throws Exception {
        TopMenuBar topMenuBar = new TopMenuBar(driver);
        topMenuBar.changeLanguage()
                .goToSearch();

        SearchPage searchPage = new SearchPage(driver);

        SearchResultPage searchResultPage = searchPage.searchForPhrase("Компьютеры")
                .selectSubDivision("Электротехника")
                .selectCity(Location.RIGA)
                .selectPeriod(Period.MONTH)
                .submitSearch();

        assertTrue(searchResultPage.getSearchResultElementsCount() > 0);
        assertTrue(searchResultPage.searchResultElements.get(0).isDisplayed());

    }


}
