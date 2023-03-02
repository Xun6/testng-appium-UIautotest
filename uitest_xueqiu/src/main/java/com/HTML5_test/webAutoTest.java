package com.HTML5_test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 纯html5站自动化测试demo
 */
public class webAutoTest {
    private AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        BaseOptions baseOptions = new BaseOptions();
        baseOptions.setCapability("browserName","Chrome");
        baseOptions.setCapability("deviceNmae","随便");
        baseOptions.setCapability("platformName","android");
        baseOptions.setCapability("chromedriverExecutable","E:\\chromeDriver\\70.0.3538\\chromedriver.exe"); //指定版本的chromedriver驱动，与当前chrome版本匹配，当进行webview页面自动化定位时所必要的依赖驱动
        baseOptions.setCapability("showChromedriverLog","true"); //打印分析webview时相关的chromedriver日志信息

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AppiumDriver(remoteUrl,baseOptions);                    //根据Appium服务器URL和功能创建一个新实例。
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 隐式等待
    }

    @Test
    public void web_test(){
        driver.get("https://testerhome.com");
        driver.findElement(By.cssSelector("#mobile-search-form > input")).sendKeys("1231312312");
        System.out.println(driver.getPageSource()); //获取当前界面源
        System.out.println("============================");
        System.out.println(driver.getWindowHandles());
        System.out.println(driver.getWindowHandle());
    }

    @AfterTest
    public void teardown() throws InterruptedException {
        Thread.sleep(10);
        driver.quit();
    }
}
