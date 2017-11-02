package com.mycompany.selenium.pageobjects;

import com.mycompany.selenium.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        Select select = new Select(subDivisionDropdown);
        select.selectByVisibleText(divisionToSelect);
        WebDriverHelper.selectDropdownValueByText(subDivisionDropdown, divisionToSelect);
    }

    public void selectSubDivision2(String division2ToSelect) {
        Select select = new Select(subDivision2Dropdown);
        select.selectByVisibleText(division2ToSelect);
        WebDriverHelper.selectDropdownValueByText(subDivision2Dropdown, division2ToSelect);
    }
}