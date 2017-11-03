package com.mycompany.selenium.pageobejcts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.mycompany.selenium.constants.Constants.WAIT_EXPLICIT_SECONDS;

public class Page {


    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, WAIT_EXPLICIT_SECONDS);
    }
}
