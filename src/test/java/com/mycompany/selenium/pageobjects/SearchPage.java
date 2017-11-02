package com.mycompany.selenium.pageobjects;

import com.mycompany.selenium.enums.Location;
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

    @FindBy(name = "cid_1")
    private WebElement subDivision2Dropdown;

    @FindBy(id = "s_region_select")
    private WebElement regionDropdown;

    @FindBy(id = "sbtn")
    private WebElement submitButton;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SEARCH_CRITERIA_INPUT_LOCATOR)));

    }

    public void searchForPhrase(String phraseToSearchFor) {
        searchCriteriaInput.sendKeys(phraseToSearchFor);
    }

    public void selectSubDivision(String divisionToSelect) {
        //subDivisionDropdown.click();
        WebDriverHelper.selectDropdownValueByText(subDivisionDropdown, divisionToSelect);
    }

    public void selectSubDivision2(String division2ToSelect) {
        WebDriverHelper.selectDropdownValueByText(subDivision2Dropdown, division2ToSelect);
    }

    public void selectRegion(Location location) {
        WebDriverHelper.selectDropdownValueByValue(regionDropdown, location.getLocation());
    }

    public void submitSearch() {
        submitButton.submit();
    }
}