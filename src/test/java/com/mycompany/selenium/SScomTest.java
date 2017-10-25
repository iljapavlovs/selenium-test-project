package com.mycompany.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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


}
