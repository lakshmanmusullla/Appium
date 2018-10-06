package com.android.mobileautomation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidAppTest {
    private static AppiumDriverLocalService service;
    public static AppiumServiceBuilder builder= new AppiumServiceBuilder();
    WebDriver driver;

    @BeforeTest
    public void startAppiumServer() throws InterruptedException
    {
        service = AppiumDriverLocalService.buildService(builder.usingPort(Integer.parseInt("4723")));
        service.start();
        Thread.sleep(25000);
       String service_url = service.getUrl().toString();
       System.out.println(service_url);
    }
    @BeforeMethod
    public void initiateAndroidDriver() throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Lucky34");
        caps.setCapability("udid", "*************"); //Give Device ID of your mobile phone
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("appPackage", "net.one97.paytm");
        caps.setCapability("appActivity", "net.one97.paytm.landingpage.activity.AJRMainActivity");
        caps.setCapability("noReset", "true");

        driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    }

    @Test
    public void VerfiyMoreOptionInHomePage()
    {

       String s=driver.findElement(By.id("net.one97.paytm:id/grid_text_1")).getText();
        if(s.equals("More"))
        {
            System.out.println("Success");
        }
    /*  if(driver.findElement(By.xpath("//android.widget.TextView[@text='More']")).isDisplayed())
      {
          System.out.println("More option is found successfully");
      }*/
    //List<MobileElement> s=driver.findElement(By.xpath("//android.widget.TextView[@text='More']"));
   //  Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='More']")).isDisplayed());


    }
    @AfterTest
    public void kilserver()
    {
        service.stop();
    }
}
