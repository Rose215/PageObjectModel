package com.nopcommerce.utils;

import com.nopcommerce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;
    private JavaScriptUtil jsUtil;
    //ElementUtil constructor
    public ElementUtil(WebDriver driver){
        this.driver=driver;
        jsUtil=new JavaScriptUtil(this.driver);

    }
    public WebElement getElement(By locator){
        WebElement element=driver.findElement(locator);
        if (BasePage.highlight.equals("true")){
            jsUtil.flash(element);

        }


        return element;
    }
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }
    public void doSendKeys(By locator,String value){
        getElement(locator).sendKeys(value);
    }
    public void doClick(By locator){
        getElement(locator).click();
    }
    public boolean doIsDisplayed(By locator){
        return getElement(locator).isDisplayed();
    }
    public String doGetText(By locator){
        return getElement(locator).getText();
    }
    public WebElement waitForElementTobeVisible(By locator, int timeout){
        WebElement element = getElement(locator);
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void waitForElementPresent(By locator, int timeout){
        WebElement element = getElement(locator);
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
    // For dropdown list automation, we can always use this structure
    public boolean compareDropDownOptions(By locator,String[] expected){
        List<WebElement> options = getAllDropDownOptions(locator);
        int i  = 0;
        for (WebElement opt:options){
            if (!opt.getText().equals(expected[i])){
                return false;

            }
            i++;// same i=i+1;
        }
        return true;
    }
    public List<WebElement> getAllDropDownOptions(By locator){
        Select select = new Select(getElement(locator));
        List<WebElement> op = select.getOptions();
        return op;
    }
    public String randomString(int len){
        String abc = "123456abcdegfkgjldsgnjds";
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i=0; i<len; i++){
            sb.append(abc.charAt(sr.nextInt(abc.length())));

        }
        return sb.toString();

    }

}
