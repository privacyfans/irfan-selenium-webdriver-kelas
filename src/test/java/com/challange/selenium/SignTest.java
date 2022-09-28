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

public class SignTest {
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
    @Parameters({"username","password","errorMessage","browser"})
    @Test(priority = 4,groups = {"SignTest"})
    public void loginInvalid(String username,String password, String messageError, String browser){


        WebElement buttonSign = driver.findElement(By.cssSelector(".header_user_info"));
        buttonSign.click();

        WebElement usernameField = driver.findElement(By.id("email"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.id("SubmitLogin"));
        buttonLogin.click();



        List<WebElement> elems = driver.findElements(By.cssSelector(".alert.alert-danger"));
        if (elems.size() == 0) {
            System.out.println("Success Login");
//            String expedtedURL = "http://automationpractice.com/index.php?controller=my-account";
//            String actualURL = driver.getCurrentUrl();
//            Assert.assertEquals(actualURL,expedtedURL,"URL expedted dan URL actual Tidak Sama");
        } else {

            System.out.println("Faild Login");
//            String expedtedURL = "http://automationpractice.com/index.php?controller=authentication";
//            String actualURL = driver.getCurrentUrl();
//            Assert.assertEquals(actualURL,expedtedURL,"URL expedted dan URL actual Tidak Sama");
//            System.out.println("My element was found on the page");
//            WebElement toastMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
//            String actualMessage = toastMessage.getText();
//            System.out.println(actualMessage);
//            System.out.println(messageError);
//            Assert.assertTrue(actualMessage.contains(messageError),"Actual Message Password Tidak sama dengan Expected Message");

                   }
        sleep(3000);
        // driver.quit();
    }

    private void sleep(long time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
