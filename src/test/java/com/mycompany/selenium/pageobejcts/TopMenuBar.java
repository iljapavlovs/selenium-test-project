package com.mycompany.selenium.pageobejcts;


import com.mycompany.selenium.enums.Language;
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

public class TopMenuBar {
    //    @FindBy(css = ".menu_lang .a_menu")
//    private WebElement changeLanguageLink;
    @FindBy(css = "[href='/ru/']")
    public WebElement switchLangToRuLink;
    @FindBy(css = "[href='/lv/']")
    public WebElement switchLangToLvLink;
    private WebDriver driver;
    @FindBy(xpath = "//*[@class='page_header_head']/h1")
    private WebElement pageHeaderHead;
    @FindBy(css = "#main_table a[href*='search']")
    private WebElement searchLink;
    private WebDriverWait wait;

    public TopMenuBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        /*Put explicit wait into Page Object Constructor
            * in order to wait until page ready state
            * or you can use Loadable Component pattern*/
        wait.until(ExpectedConditions.visibilityOf(pageHeaderHead));
    }

    public TopMenuBar switchLangTo(Language language) {
        switch (language) {
            case LV:
                if (switchLangToLvLink.isDisplayed()) {
                    switchLangToLvLink.click();
                    break;
                }
            case RU:
                if (switchLangToRuLink.isDisplayed()) {
                    switchLangToRuLink.click();
                }
        }
        return this;
    }

    public String getPageHeaderHeadText() {
        return pageHeaderHead.getText();
    }


    public SearchPage goToSearch() {
        searchLink.click();
        return new SearchPage(driver);
    }

}


