package com.nopcommerce.tests;

import com.nopcommerce.base.BasePage;
import com.nopcommerce.base.BaseTest;
import com.nopcommerce.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    @Test(priority = 1)
    public void validatePageTitleTest(){
       String pageTitle= homePage.getPageTitle();
       //this is hard assert
        Assert.assertEquals(pageTitle, Constants.PAGE_TITLE);

    }
    @Test(priority = 2)
    public void verifyLogoTest(){
        boolean logoStatus = homePage.verifyLogo();
        Assert.assertTrue(logoStatus);

    }
    @Test(priority = 3)
    public void verifyRegisterLinkTest(){
        Assert.assertTrue(homePage.verifyRegisterLink());
    }

    @Test(priority = 4)
    public void verifyLoginLinkTest(){
        Assert.assertTrue(homePage.verifyLoginLink());
    }


}
