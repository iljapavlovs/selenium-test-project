package com.mycompany.selenium.pageobejcts;

import com.mycompany.selenium.Enums.Location;
import com.mycompany.selenium.Enums.TimePeriod;
import com.mycompany.selenium.Helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ekaterina.kazinets on 11/2/2017.
 */
public class SearchPage {
    private static final String SEARCH_CRITERIA_INPUT_LOCATOR = "ptxt";
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = SEARCH_CRITERIA_INPUT_LOCATOR)
    private WebElement searchCriteriaInput;

    @FindBy(name = "cid_0")
    private WebElement subDivisionDropDown;

    @FindBy(id = "s_region_select")
    private WebElement regionDropDown;

    @FindBy(css = "[name='pr']")
    private WebElement periodDropDown;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;


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
        WebDriverHelper.selectDropDrownValueByValue(subDivisionDropDown, divisionToSelect);
        return this;
    }

    public SearchPage selectLocation(Location location) {
        WebDriverHelper.selectDropDrownValueByValue(regionDropDown, location.getLocation());
        return this;

    }

    public SearchPage selectPeriod(TimePeriod timePeriod) {
        WebDriverHelper.selectDropDrownValueByValue(periodDropDown, timePeriod.getTimePeriod());
        return this;

    }

    public SearchResultPage performSearch() {
        submitButton.submit();
        return new SearchResultPage(driver);
    }
}
