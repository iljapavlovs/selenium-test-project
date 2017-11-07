package com.mycompany.selenium.tests;


import com.mycompany.selenium.TestBase;
import com.mycompany.selenium.enums.Language;
import com.mycompany.selenium.enums.Location;
import com.mycompany.selenium.enums.TimePeriod;
import com.mycompany.selenium.pageobejcts.SearchPage;
import com.mycompany.selenium.pageobejcts.SearchResultPage;
import com.mycompany.selenium.pageobejcts.TopMenuBar;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Feature("Search")
public class SearchTest extends TestBase {
    /**
    TAKEAWAYS:
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
    * Dont hardcode data which may change with time - e.g. testing envs, test users, explicit waits, etc
        * parametrize it via external files - like config file for env URL or JSON for users data;
    * More general Fixtures can be put to BaseTest class and your Test class should extend the Base Test class.
        * This way test class only has testing code without any preparation and teardown code
    * DriverFactory - Hide the logic of initializing an object inside the factory
    * Common methods, fields, initilization can be moved to parent Page class for all page objects
    * Ensure that tests can be run in parallel in order to save time on test execution
    * Run parallel tests on Selenium Grid
        * In this case - instantiate RemoteWebDriver class instead of a Browser driver concrete implementation (e.g. Chromedriver)
    * Take screenshots on failures - this way it will be much easier to investigate the issues with tests
    * Use informative report like Allure2
     */


    @Test
    @Story("Search")
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
