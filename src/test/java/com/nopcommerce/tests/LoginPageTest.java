package com.nopcommerce.tests;

import com.nopcommerce.base.BaseTest;
import com.nopcommerce.pages.HomePage;
import com.nopcommerce.utils.Constants;
import com.nopcommerce.utils.ElementUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @BeforeClass
    public void loginPageSetup (){
        loginPage=homePage.goToLoginPage();
    }
    @Test(priority = 1)
    public void verifyLoginPageNameTest() throws InterruptedException {
        Thread.sleep(2000);
        String pageName = loginPage.getLoginPageName();
        System.out.println(pageName);
        Assert.assertEquals(pageName, Constants.LOGIN_PAGE_NAME,"page name does not match");
    }
    @Test(priority = 2)
    public void verifyLoginButtonStatusTest(){
        Assert.assertTrue(loginPage.verifyLoginBtn(),"Login button is not available");
    }
    @Test(priority = 3)
    public void verifyRegisterButtonStatusTest(){
        Assert.assertTrue(loginPage.verifyRegisterButton(),"register button is not available");

    }
    @Test(priority = 4)
    public void validateUserLoginTest(){
        loginPage.doLogin(prop.getProperty("id"),prop.getProperty("pass"));
        Assert.assertTrue(homePage.verifyMyAccountLink(),"Login failed,my account link cannot be verified");
    }
    @Test(priority = 5)
    public void logoutTest(){
        homePage.doLogout();
        Assert.assertTrue(homePage.verifyLoginLink(),"Logout failed, login link cannot be verified");
    }






}
