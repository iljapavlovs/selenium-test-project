package com.mycompany.selenium.pageobejcts;


import com.mycompany.selenium.enums.Location;
import com.mycompany.selenium.enums.TimePeriod;
import com.mycompany.selenium.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
Page Object should expose the "interface" to the Page
(contains behavior - methods to interact with the page)
 */

public class SearchPage {
    private static final String SEARCH_CRITERIA_INPUT_LOCATOR = "ptxt";
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = SEARCH_CRITERIA_INPUT_LOCATOR)
    private WebElement searchCriteriaInput;


    // assume that locator is not dynamic
    @FindBy(name = "cid_0")
    private WebElement subDivisionDropdown;

    // assume that locator is not dynamic
    @FindBy(name = "cid_1")
    private WebElement categoryDropdown;

    @FindBy(id = "s_region_select")
    private WebElement locationDropdown;

    // assume that locator is not dynamic
    @FindBy(name = "pr")
    private WebElement timePeriodDropdown;

    @FindBy(id = "sbtn")
    private WebElement searchButton;

    @FindBy(xpath = "//*[contains(text(),'Искомое слово') or contains(text(),'Meklējamais vārds')]")
    private WebElement searchCriteriaTitle;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_CRITERIA_INPUT_LOCATOR)));
    }


    public SearchPage searchForPhrase(String phraseToSearchFor) {
        searchCriteriaInput.sendKeys(phraseToSearchFor);
        return this;
    }

    public SearchPage selectSubDivision(String divisionToSelect) {
        WebDriverHelper.selectDropdownValueByText(subDivisionDropdown, divisionToSelect);
        return this;
    }

    public SearchPage selectCategory(String categoryToSelect) {

        WebDriverHelper.selectDropdownValueByText(categoryDropdown, categoryToSelect);
        return this;
    }


    public SearchPage selectLocation(Location location) {
        WebDriverHelper.selectDropdownValueByValue(locationDropdown, location.getLocation());
        return this;
    }

    public SearchPage selectTimePeriod(TimePeriod timePeriod) {
        WebDriverHelper.selectDropdownValueByValue(timePeriodDropdown, timePeriod.getDayCount());
        return this;
    }

    public SearchResultPage performSearch() {
        searchCriteriaTitle.click();
        this.searchButton.click();
        return new SearchResultPage(driver);
    }


}
