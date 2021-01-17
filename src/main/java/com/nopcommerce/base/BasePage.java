package com.nopcommerce.base;

import com.aventstack.extentreports.utils.FileUtil;
import com.nopcommerce.utils.OptionsManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();

    //public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;

//initialize web driver
    public WebDriver init_driver(String browser) {
        highlight=prop.getProperty("highlight");
        optionsManager= new OptionsManager(prop);


        System.out.println("browser value is: " + browser);
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
           // driver = new ChromeDriver();
            if(Boolean.parseBoolean(prop.getProperty("remote"))){
                init_remoteDriver("chrome");

            }else {
                tDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver();
            if(Boolean.parseBoolean(prop.getProperty("remote"))){
                init_remoteDriver("firefox");

            }else {
                tDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
            }
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            //driver = new InternetExplorerDriver();
            tDriver.set(new InternetExplorerDriver());

        }
       getDriver().manage().window().maximize();
       // getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return getDriver();

    }
    private void init_remoteDriver(String browser){
        if (browser.equals("chrome")){
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            dc.setCapability(ChromeOptions.CAPABILITY,optionsManager.getChromeOptions());
            try {
                tDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),dc));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        else if(browser.equals("firefox")){
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS,optionsManager.getFirefoxOptions());
            try {
                tDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),dc));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
    }

    public static synchronized WebDriver getDriver(){
        return tDriver.get();
    }
    // access to properties file
    public Properties init_prop() {
        prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./src/main/java/com/nopcommerce/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;

    }
    public String getScreenshot(){
        File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
