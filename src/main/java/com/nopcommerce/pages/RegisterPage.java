package com.nopcommerce.pages;

import com.nopcommerce.base.BasePage;
import com.nopcommerce.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        elementUtil=new ElementUtil(driver);

    }
    private By registerPageName  = By.xpath("//div[@class='page-title']/h1");
    private By genderMale = By.id("gender-male");
    private By genderFemale = By.id("gender-female");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By dobDay = By.name("DateOfBirthDay");
    private By dobMonth = By.name("DateOfBirthMonth");
    private By dobYear = By.name("DateOfBirthYear");

    private By email = By.id("Email");
    private By company = By.id("Company");
    private By password = By.id("Password");
    private By conPassword = By.id("ConfirmPassword");
    private By registerBtn = By.id("register-button");
    private By registerContinueBtn = By.name("register-continue");

    public String getRegisterPageName(){
        return elementUtil.doGetText(registerPageName);

    }
    public HomePage registerNewUser(String gender, String fName, String lName, String day,String month,
                                String year,String email, String company,String password) throws InterruptedException {
       Thread.sleep(2500);
        if (gender.equalsIgnoreCase("male")){
            elementUtil.doClick(genderMale);
        }else {
            elementUtil.doClick(genderFemale);
        }
        Thread.sleep(2500);
        elementUtil.doSendKeys(firstName,fName);
        elementUtil.doSendKeys(lastName,lName);
        elementUtil.doSendKeys(dobDay,day);
        elementUtil.doSendKeys(dobMonth,month);
        elementUtil.doSendKeys(dobYear,year);
        elementUtil.doSendKeys(this.email,elementUtil.randomString(6)+email);
        elementUtil.doSendKeys(this.company,company);
        elementUtil.doSendKeys(this.password,password);
        elementUtil.doSendKeys(conPassword,password);
        elementUtil.doClick(registerBtn);
        Thread.sleep(2500);
        elementUtil.doClick(registerContinueBtn);
        Thread.sleep(2500);
        return new HomePage(driver);


    }












}
