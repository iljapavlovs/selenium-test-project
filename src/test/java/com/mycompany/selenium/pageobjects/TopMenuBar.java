package com.mycompany.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuBar {

    @FindBy(css = ".menu_lang .a_menu")
    private WebElement changeLanguageLink;

    @FindBy(css = "h1")
    private WebElement pageHeaderHead;

    @FindBy(css = "#main_table a[href*='search']")
    private WebElement searchLink;


//    public static final By LANGUAGE_LOCATOR = By.cssSelector(".menu_lang .a_menu");
//    private static final By SEARCH_LINK_LOCATOR = By.cssSelector(".menu_main a[href='/ru/search/']");

    private WebDriver driver;
    private WebDriverWait wait;


    public TopMenuBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(changeLanguageLink));

    }

    public TopMenuBar changeLanguage() {
        wait.until(ExpectedConditions.elementToBeClickable(changeLanguageLink));
        changeLanguageLink.click();
        return this;

    }

    public WebElement getLanguageLink() {
        return changeLanguageLink;
    }

    public String getPageHeaderHeadText() {
        return pageHeaderHead.getText();
    }

    public void goToSearch() {
        searchLink.click();
    }
}
