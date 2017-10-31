package com.mycompany.selenium;

/**
 * do I need to do a new pull request if I made some changes after my first pull request?
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SScomTest {

    private WebDriver driver;

    @BeforeClass
    public void globalSetUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.30-win32/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://ss.com");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testDefaultLanguage() {
        assertTrue(driver.getTitle().equals("Sludinājumi - SS.COM"));
    }

    @Test
    public void testLanguageChangeLVtoRU() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement languageBarLV = driver.findElement(By.cssSelector("[title='Latviski']"));
        assertTrue(languageBarLV.isDisplayed());
    }

    @Test
    public void testLanguageChangeLVtoRUtoLT() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement languageBarLV = driver.findElement(By.cssSelector("[title='Latviski']"));
        languageBarLV.click();

        assertTrue(languageBarRU.isDisplayed());
    }

    @Test
    public void testSearch() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement searchBar = driver.findElement(By.cssSelector("[title='Искать объявления']"));
        searchBar.click();

        WebElement searchField = driver.findElement(By.name("txt"));
        searchField.sendKeys("Компьютер");

        Select divisionDropdown = new Select(driver.findElement(By.name("cid_0")));
        divisionDropdown.selectByVisibleText("Электротехника");

        Select categoryDropdown = new Select(driver.findElement(By.name("cid_1")));
        categoryDropdown.selectByVisibleText("Компьютеры, оргтехника");

        WebElement searchBtn = driver.findElement(By.id("sbtn"));
        searchBtn.submit();

        List<WebElement> priceList = driver.findElements(By.cssSelector("a.amopt"));
        assertTrue(priceList.get(0).isDisplayed());

    }


    @Test
    public void testSortResultsByPriceDesc() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement searchBar = driver.findElement(By.cssSelector("[title='Искать объявления']"));
        searchBar.click();

        WebElement searchField = driver.findElement(By.name("txt"));
        searchField.sendKeys("Компьютер");

        Select divisionDropdown = new Select(driver.findElement(By.name("cid_0")));
        divisionDropdown.selectByVisibleText("Электротехника");

        Select categoryDropdown = new Select(driver.findElement(By.name("cid_1")));
        categoryDropdown.selectByVisibleText("Компьютеры, оргтехника");

        WebElement searchBtn = driver.findElement(By.id("sbtn"));
        searchBtn.submit();

        WebElement priceBar1 = driver.findElement(By.linkText("Цена"));
        priceBar1.click();

        WebElement priceBar2 = driver.findElement(By.linkText("Цена"));
        priceBar2.click();

        List<WebElement> priceList2 = driver.findElements(By.cssSelector("a.amopt"));
        List <BigDecimal> priceValuesList = new ArrayList<BigDecimal>();

        for (WebElement price: priceList2) {
        priceValuesList.add(new BigDecimal(price.getText().substring(0, price.getText().length() - 2).replaceAll(",","")));
        }

        List <BigDecimal> pricesSortedBySiteDesc = new ArrayList<BigDecimal>(priceValuesList);
        //System.out.println(sortedBySite);
        Collections.sort(priceValuesList, Collections.reverseOrder());
        //System.out.println(priceValuesList);
        assertEquals(pricesSortedBySiteDesc, priceValuesList);
    }

    @Test
    public void testExtendedSearchShow3Count() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement searchBar = driver.findElement(By.cssSelector("[title='Искать объявления']"));
        searchBar.click();

        WebElement searchField = driver.findElement(By.name("txt"));
        searchField.sendKeys("Компьютер");

        Select divisionDropdown = new Select(driver.findElement(By.name("cid_0")));
        divisionDropdown.selectByVisibleText("Электротехника");

        Select categoryDropdown = new Select(driver.findElement(By.name("cid_1")));
        categoryDropdown.selectByVisibleText("Компьютеры, оргтехника");

        WebElement searchBtn = driver.findElement(By.id("sbtn"));
        searchBtn.submit();

        WebElement extendedSearchLink = driver.findElement(By.cssSelector("a.a9a"));
        extendedSearchLink.click();

        WebElement minPriceInput = driver.findElement(By.name("topt[8][min]"));
        minPriceInput.sendKeys("0");

        WebElement maxPriceInput = driver.findElement(By.name("topt[8][max]"));
        maxPriceInput.sendKeys("300");

        WebElement extSearchBtn = driver.findElement(By.id("sbtn"));
        extSearchBtn.submit();

        WebElement priceBar1 = driver.findElement(By.linkText("Цена"));
        priceBar1.click();

        WebElement priceBar2 = driver.findElement(By.linkText("Цена"));
        priceBar2.click();

        Select transactionTypeDropdown = new Select(driver.findElement(By.cssSelector(".filter_second_line_dv>span:nth-child(3)>select")));
        transactionTypeDropdown.selectByVisibleText("Продажа");

        final List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        List<WebElement> checkboxToClickList = new ArrayList<WebElement>(){
            {
                add(checkboxList.get(1));add(checkboxList.get(2)); add(checkboxList.get(4));
            }
        };

        for (WebElement checkboxToClick: checkboxToClickList) {
            checkboxToClick.click();
        }

        WebElement selectedCounter = driver.findElement(By.id("sel_cnt_obj"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(selectedCounter));
        assertEquals(selectedCounter.getText().substring(1, 2), "3");
    }

    @Test
    public void testExtendedSearchShow3Ads() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement searchBar = driver.findElement(By.cssSelector("[title='Искать объявления']"));
        searchBar.click();

        WebElement searchField = driver.findElement(By.name("txt"));
        searchField.sendKeys("Компьютер");

        Select divisionDropdown = new Select(driver.findElement(By.name("cid_0")));
        divisionDropdown.selectByVisibleText("Электротехника");

        Select categoryDropdown = new Select(driver.findElement(By.name("cid_1")));
        categoryDropdown.selectByVisibleText("Компьютеры, оргтехника");

        WebElement searchBtn = driver.findElement(By.id("sbtn"));
        searchBtn.submit();

        WebElement extendedSearchLink = driver.findElement(By.cssSelector("a.a9a"));
        extendedSearchLink.click();

        WebElement minPriceInput = driver.findElement(By.name("topt[8][min]"));
        minPriceInput.sendKeys("0");

        WebElement maxPriceInput = driver.findElement(By.name("topt[8][max]"));
        maxPriceInput.sendKeys("300");

        WebElement extSearchBtn = driver.findElement(By.id("sbtn"));
        extSearchBtn.submit();

        WebElement priceBar1 = driver.findElement(By.linkText("Цена"));
        priceBar1.click();

        WebElement priceBar2 = driver.findElement(By.linkText("Цена"));
        priceBar2.click();

        Select transactionTypeDropdown = new Select(driver.findElement(By.cssSelector(".filter_second_line_dv>span:nth-child(3)>select")));
        transactionTypeDropdown.selectByVisibleText("Продажа");

        final List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        List<WebElement> checkboxToClickList = new ArrayList<WebElement>(){
            {
                add(checkboxList.get(1));add(checkboxList.get(2)); add(checkboxList.get(4));
            }
        };

        for (WebElement checkboxToClick: checkboxToClickList) {
            checkboxToClick.click();
        }

        List<String> checkboxToClickIdList = new ArrayList<String>();
        for (WebElement checkboxToClick: checkboxToClickList) {
            checkboxToClickIdList.add(new String(checkboxToClick.getAttribute("id")));
        }

        Collections.sort(checkboxToClickIdList);
        //System.out.println(checkboxToClickIdList);

        WebElement showSelectedLink = driver.findElement(By.cssSelector("#show_selected_a"));
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);

//        THIS DID NOT HELP. Why?...
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.visibilityOf(showSelectedLink));

//        Only this helped :-(
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        showSelectedLink.click();

        List<WebElement> shownCheckboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        List <String> shownCheckboxIdList = new ArrayList<String>();
        for (WebElement shownCheckbox: shownCheckboxList) {
            shownCheckboxIdList.add(new String(shownCheckbox.getAttribute("id")));
        }
        Collections.sort(shownCheckboxIdList);
        //System.out.println(shownCheckboxIdList);

        assertEquals(checkboxToClickIdList, shownCheckboxIdList);

    }

}
