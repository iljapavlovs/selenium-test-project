package com.mycompany.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdsSiteTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-v2.30-win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://ss.com");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void changeLanguage() throws Exception {
        WebElement langSwitcher = driver.findElement(By.cssSelector(".menu_lang .a_menu"));
        langSwitcher.click();

        assertTrue(driver.getTitle().contentEquals("Объявления - SS.COM"));
    }

    @Test
    public void openSearchPage() throws Exception {
        WebElement searchLink = driver.findElement(By.cssSelector(".menu_main .a_menu[href='/ru/search/']"));
        searchLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ptxt")));

        assertTrue(driver.getTitle().contains("Поиск объявлений"));
    }

    @Test
    public void searchPhrase() throws Exception {
        WebElement searchInput = driver.findElement(By.id("ptxt"));
        searchInput.sendKeys("Компьютеры");

        Select sectionDropdown = new Select(driver.findElement(By.name("cid_0")));
        sectionDropdown.selectByVisibleText("Электротехника");

        Select cityDropdown = new Select(driver.findElement(By.id("s_region_select")));
        cityDropdown.selectByVisibleText("Рига");

        Select periodDropdown = new Select(driver.findElement(By.cssSelector(".in1s[name='pr']")));
        periodDropdown.selectByValue("30");

        WebElement searchButton = driver.findElement(By.id("sbtn"));
        searchButton.click();

        List<WebElement> resultsList = driver.findElements((By.cssSelector(".d1")));
        assertTrue(resultsList.size() > 0);
    }

    @Test
    public void sortByPrice() throws Exception {
        WebElement priceLink = driver.findElement(By.cssSelector(".a18"));
        priceLink.click();

        List<WebElement> pricesList = driver.findElements((By.cssSelector(".amopt")));
        String pricePrv = "0 E";
        for (WebElement element : pricesList) {
            //System.out.println(element.getText());
            String price = element.getText();
            assertTrue(Float.parseFloat(pricePrv.substring(0, pricePrv.length() - 2)) <= Float.parseFloat(price.substring(0, price.length() - 2)));
            pricePrv = price;
        }

    }

}
