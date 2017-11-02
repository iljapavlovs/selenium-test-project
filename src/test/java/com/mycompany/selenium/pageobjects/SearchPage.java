package com.mycompany.selenium.pageobjects;

import com.mycompany.selenium.enums.Location;
import com.mycompany.selenium.enums.Period;
import com.mycompany.selenium.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    static final String SEARCH_CRITERIA_INPUT_LOCATOR = "ptxt";
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = SEARCH_CRITERIA_INPUT_LOCATOR)
    private WebElement searchCriteriaInput;

    @FindBy(name = "cid_0")
    private WebElement subDivisionDropdown;

    @FindBy(id = "s_region_select")
    private WebElement cityDropdown;

    @FindBy(css = ".in1s[name='pr']")
    private WebElement periodDropdown;

    @FindBy(id = "sbtn")
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
//        Select select = new Select(subDivisionDropdown);
//        select.selectByVisibleText(divisionToSelect);
        WebDriverHelper.selectDropdownValueByText(subDivisionDropdown, divisionToSelect);
        return this;
    }

    public SearchPage selectCity(Location location) {
        WebDriverHelper.selectDropdownValueByValue(cityDropdown, location.getLocation());
        return this;
    }

    public SearchPage selectPeriod(Period period) {
        WebDriverHelper.selectDropdownValueByValue(periodDropdown, period.getPeriod());
        return this;
    }

    public SearchResultPage submitSearch() {
        submitButton.click();
        return new SearchResultPage(driver);
    }
}
