package com.mycompany.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by ekaterina.kazinets on 10/28/2017.
 */
public class Task_2 {

    private WebDriver driver;


    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.30-win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://ss.com");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    public void OpenSearchPage() {
        WebElement lang = driver.findElement(By.cssSelector(".menu_lang .a_menu"));
        lang.click();

        WebElement search_link = driver.findElement(By.cssSelector(".menu_main a[href='/ru/search/']"));
        search_link.click();
    }

    public void FillSearchForm() {
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
        select_cid.selectByVisibleText("Электротехника");
        select_city.selectByVisibleText("Рига");
        select_period.selectByVisibleText("Среди всех объявлений");

        search_button.submit();

    }


    @Test
    public void testSortingByPrice() {

        OpenSearchPage();
        FillSearchForm();

        WebElement price_link1 = driver.findElement(By.cssSelector("a.a18"));
        price_link1.click();
        WebElement price_link2 = driver.findElement(By.cssSelector("a.a19"));
        price_link2.click();

        List<WebElement> priceList = driver.findElements(By.cssSelector("a.amopt"));
        List<BigDecimal> priceValuesList = new ArrayList<BigDecimal>();

        for (WebElement price : priceList) {
            priceValuesList.add(new BigDecimal(price.getText().substring(0, price.getText().length() - 2).replaceAll(",", "")));
        }

        List<BigDecimal> pricesSortedBySiteDesc = new ArrayList<BigDecimal>(priceValuesList);
        Collections.sort(priceValuesList, Collections.reverseOrder());
        Assert.assertEquals(pricesSortedBySiteDesc, priceValuesList);


    }


    @Test
    public void testOpenSale() {
        OpenSearchPage();
        FillSearchForm();
        WebElement price_link1 = driver.findElement(By.cssSelector("a.a18"));
        price_link1.click();
        WebElement price_link2 = driver.findElement(By.cssSelector("a.a19"));
        price_link2.click();

        Select transactionDropDown = new Select(driver.findElement(By.cssSelector(".filter_second_line_dv > span:nth-child(3)>select")));
        transactionDropDown.selectByVisibleText("Продажа");

        WebElement title_search = driver.findElement(By.cssSelector(".headtitle"));
        assertEquals(title_search.getText(), "Результат поиска / Продажа");

    }

    @Test
    public void testExtendedSearchSelect3Adv() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        OpenSearchPage();
        FillSearchForm();
        Select transactionDropDown = new Select(driver.findElement(By.cssSelector(".filter_second_line_dv > span:nth-child(3)>select")));
        transactionDropDown.selectByVisibleText("Продажа");

        WebElement extendedSearchLink = driver.findElement(By.cssSelector("a.a9a"));
        extendedSearchLink.click();

        WebElement minPriceInput = driver.findElement(By.name("topt[8][min]"));
        minPriceInput.sendKeys("0");

        WebElement maxPriceInput = driver.findElement(By.name("topt[8][max]"));
        maxPriceInput.sendKeys("300");

        WebElement extSearchBtn = driver.findElement(By.id("sbtn"));
        extSearchBtn.submit();

        WebElement pricelink1 = driver.findElement(By.cssSelector("a.a18"));
        pricelink1.click();
        WebElement pricelink2 = driver.findElement(By.cssSelector("a.a19"));
        pricelink2.click();

        List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        checkboxList.get(1).click();
        wait.until(ExpectedConditions.attributeContains(checkboxList.get(1), "checked", "true"));
        checkboxList.get(2).click();
        wait.until(ExpectedConditions.attributeContains(checkboxList.get(2), "checked", "true"));
        checkboxList.get(3).click();
        wait.until(ExpectedConditions.attributeContains(checkboxList.get(3), "checked", "true"));

        WebElement selectedCounter = driver.findElement(By.cssSelector("#sel_cnt_obj"));
        // WebDriverWait wait = new WebDriverWait(driver, 10);
        // wait.until(ExpectedConditions.visibilityOf(selectedCounter));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(selectedCounter.getText().substring(1, 2), "3");

    }

    @Test
    public void testExtendedSearchShow3Ads() {
        OpenSearchPage();
        FillSearchForm();
        Select transactionDropDown = new Select(driver.findElement(By.cssSelector(".filter_second_line_dv > span:nth-child(3)>select")));
        transactionDropDown.selectByVisibleText("Продажа");

        WebElement extendedSearchLink = driver.findElement(By.cssSelector("a.a9a"));
        extendedSearchLink.click();

        WebElement minPriceInput = driver.findElement(By.name("topt[8][min]"));
        minPriceInput.sendKeys("0");

        WebElement maxPriceInput = driver.findElement(By.name("topt[8][max]"));
        maxPriceInput.sendKeys("300");

        WebElement extSearchBtn = driver.findElement(By.id("sbtn"));
        extSearchBtn.submit();

        WebElement pricelink1 = driver.findElement(By.cssSelector("a.a18"));
        pricelink1.click();
        WebElement pricelink2 = driver.findElement(By.cssSelector("a.a19"));
        pricelink2.click();


        List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        checkboxList.get(1).click();
        checkboxList.get(2).click();
        checkboxList.get(3).click();

        List<String> checkboxIdList = new ArrayList<String>();
        checkboxIdList.add(checkboxList.get(1).getAttribute("id"));
        checkboxIdList.add(checkboxList.get(2).getAttribute("id"));
        checkboxIdList.add(checkboxList.get(3).getAttribute("id"));

        Collections.sort(checkboxIdList);
        System.out.println(checkboxIdList);

        WebElement showSelectedLink = driver.findElement(By.cssSelector("#show_selected_a"));
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        showSelectedLink.click();


        List<WebElement> shownCheckboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        List<String> shownCheckboxIdList = new ArrayList<String>();
        for (WebElement shownCheckbox : shownCheckboxList) {
            shownCheckboxIdList.add(new String(shownCheckbox.getAttribute("id")));
        }
        Collections.sort(shownCheckboxIdList);


        Assert.assertEquals(checkboxIdList, shownCheckboxIdList);

    }


}
