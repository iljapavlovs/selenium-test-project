package com.mycompany.selenium.pageobejcts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/*
Page Object should expose the "interface" to the Page
(contains behavior - methods to interact with the page)
 */

public class SearchResultPage extends Page {
    //NOTE - only make instance variables public when you really need to!
    @FindBy(css = "[id*='tr_']:not([id*='tr_bnr_'])")
    public List<WebElement> searchResultElements;

    @FindBy(id = "page_main")
    private WebElement searchresultMainSection;


    public SearchResultPage(WebDriver driver) {
        super(driver);

        /*Put explicit wait into Page Object Constructor
            * in order to wait until page ready state
            * or you can use Loadable Component pattern*/
        wait.until(ExpectedConditions.visibilityOf(searchresultMainSection));
    }


    public int getSearchResultElementsCount() {
        return searchResultElements.size();
    }
}
