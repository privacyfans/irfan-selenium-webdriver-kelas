package com.challange.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest {

    WebDriver driver;
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser){
        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }else{
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }


        String url ="http://automationpractice.com/index.php";
        driver.get(url);
        //driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Parameters({"search_text","expedtedUrl","messageError","browser"})
    @Test(priority = 1, groups = {"searchTest"})
    public void searchResutlTest(String search_text, String expectedUrl, String messageError, String browser){
        //input text No Product Found
        WebElement usernameField = driver.findElement(By.id("search_query_top"));
        usernameField.sendKeys(search_text);
        System.out.println(search_text);
        WebElement buttonSearch = driver.findElement(By.name("submit_search"));
        buttonSearch.click();

        String actualURL = driver.getCurrentUrl();
        System.out.println(actualURL);
        System.out.println(expectedUrl);
        Assert.assertEquals(actualURL,expectedUrl,"URL expedted dan URL actual Tidak Sama");

        List<WebElement> elems = driver.findElements(By.cssSelector(".alert.alert-warning"));
        if (elems.size() == 0) {
            System.out.println("My element was not found on the page");
        } else {
            System.out.println("My element was found on the page");
            WebElement toastMessage = driver.findElement(By.cssSelector(".alert.alert-warning"));
            String actualMessage = toastMessage.getText();
            System.out.println(actualMessage);
            System.out.println(messageError);
            Assert.assertTrue(actualMessage.contains(messageError),"Actual Message Password Tidak sama dengan Expected Message");
        }

        sleep(3000);
    }

//

    private void sleep(long time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
