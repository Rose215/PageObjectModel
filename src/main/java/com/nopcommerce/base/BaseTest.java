package com.nopcommerce.base;

import com.nopcommerce.pages.HomePage;
import com.nopcommerce.pages.LoginPage;
import com.nopcommerce.pages.RegisterPage;
import com.nopcommerce.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    public BasePage basePage;
    public Properties prop;
    public LoginPage loginPage;
    public HomePage homePage;
    public WebDriver driver;
    public RegisterPage registerPage;
    public SearchPage searchPage;

    @Parameters("browser")
    @BeforeTest
    public void setup(String browserName) {
        basePage = new BasePage();
        // We need to get the browser first, then we can initialize driver
        prop =basePage.init_prop();

        // We get our browser type first using getProperty method, which return us the browser type
        String browserType = prop.getProperty("browser");

        if (browserName!=null){
            browserType=browserName;
        }
        // Once we get browser type, we can now initialize our driver
       driver = basePage.init_driver(browserType);
       homePage = new HomePage(driver);
       driver.get(prop.getProperty("url"));


    }

    @AfterTest
    public void tearDown() {
        driver.quit();

    }
}


