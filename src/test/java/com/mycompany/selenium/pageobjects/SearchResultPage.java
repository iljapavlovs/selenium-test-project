package com.mycompany.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage {
    @FindBy(css = "[id*='tr_']:not([id*='tr_bnr_'])")
    public List<WebElement> searchResultElements;
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "page_main")
    private WebElement searchResultMainSection;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(searchResultMainSection));
    }

    public int getSearchResultElementsCount() {
        return searchResultElements.size();
    }


}
