package com.mycompany.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void testLanguageChange() {
        WebElement languageBarRU = driver.findElement(By.cssSelector("[title='По-русски']"));
        languageBarRU.click();

        WebElement languageBarLV = driver.findElement(By.cssSelector("[title='Latviski']"));
        Assert.assertTrue(languageBarLV.isDisplayed());
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

        List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        assertTrue (checkboxList.get(0).isDisplayed());

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
        Assert.assertEquals(pricesSortedBySiteDesc, priceValuesList);
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

        List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        checkboxList.get(1).click();
        checkboxList.get(2).click();
        checkboxList.get(4).click();
        WebElement selectedCounter = driver.findElement(By.id("sel_cnt_obj"));
        Assert.assertEquals(selectedCounter.getText().substring(1, 2), "3");
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



        List<WebElement> descriptionList = driver.findElements(By.className(".am"));
        WebElement[] selectedDescriptionList = {descriptionList.get(1), descriptionList.get(2), descriptionList.get(4)};


//        List <String> selectedDescriptionList = new ArrayList<String>();
//        for (WebElement description: selectedDescriptionList) {
//            selectedDescriptionList.add(new String(description.getText()));
//        }

        System.out.println(selectedDescriptionList);
        List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        checkboxList.get(1).click();
        checkboxList.get(2).click();
        checkboxList.get(4).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement showSelectedLink = driver.findElement(By.cssSelector("#show_selected_a"));
        Actions action = new Actions(driver);
        action.moveToElement(showSelectedLink).click().perform();
        //showSelectedLink.click();
        List<WebElement> descriptionListShown = driver.findElements(By.className("am"));

        System.out.println(descriptionListShown);
        //Assert.assertEquals(selectedDescriptionList, descriptionListShown);


    }

}