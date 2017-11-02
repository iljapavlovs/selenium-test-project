package com.mycompany.selenium.Helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by ekaterina.kazinets on 11/2/2017.
 */
public class WebDriverHelper {
    public static void selectDropDrownValueByValue(WebElement dropDownElement, String value) {
        Select select = new Select(dropDownElement);
        select.selectByValue(value);
    }
}
