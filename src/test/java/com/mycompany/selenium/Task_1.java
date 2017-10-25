package com.mycompany.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Function;

import static org.testng.Assert.assertTrue;

/**
 * Created by ekaterina.kazinets on 10/24/2017.
 */
public class Task_1 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.30-win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://ss.com");
    }

    @Test
    public void testChangeLanguage() throws Exception {
        WebElement lang = driver.findElement(By.cssSelector(".menu_lang .a_menu"));
        if (!lang.getText().equals("RU")) {
            lang.click();

        }
        Assert.assertEquals(lang.getText(), "RU");
        Assert.assertEquals(lang.getAttribute("title"), "По-русски");

    }

    @Test
    public void testSimpleSearch() {
        WebElement lang = driver.findElement(By.cssSelector(".menu_lang .a_menu"));
        lang.click();

        WebElement search_link = driver.findElement(By.cssSelector(".menu_main a[href='/ru/search/']"));
        search_link.click();
        WebElement search_button = driver.findElement(By.cssSelector("[type='submit']"));
        WebElement search_word = driver.findElement(By.cssSelector(".in1"));

        WebElement search_cid = driver.findElement(By.cssSelector("[name='cid_0']"));
        Select select_cid = new Select(search_cid);

        WebElement search_city = driver.findElement(By.cssSelector("#s_region_select"));
        Select select_city = new Select(search_city);

        WebElement search_period = driver.findElement(By.cssSelector("[name='pr']"));
        Select select_period = new Select(search_period);

        String text = "компьютер";

        search_word.sendKeys(text);
        select_cid.selectByIndex(1);
        select_city.selectByIndex(1);
        select_period.selectByIndex(4);

        search_button.submit();

        List<WebElement> result = driver.findElements(By.cssSelector("[name='mid[]']"));
        assertTrue(result.size() > 0);
        assertTrue(result.get(0).isDisplayed());

    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
