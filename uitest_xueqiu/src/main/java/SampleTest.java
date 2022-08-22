import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        BaseOptions baseOptions = new BaseOptions();
        baseOptions.setCapability("platformName","Android");
        baseOptions.setCapability("deviceName", "vivo");
        baseOptions.setCapability("appPackage", "com.xueqiu.android");
        baseOptions.setCapability("appActivity", ".view.WelcomeActivityAlias");
        baseOptions.setCapability("autoGrantPermissions", "true");
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("deviceName", "vivo");
//        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
//        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
//        desiredCapabilities.setCapability("autoGrantPermissions", "true");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, baseOptions);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  // 添加隐式等待，此为全局等待
    }

    @Test
    public void sampleTest() {
//        MobileElement el3 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
//        el3.click();
//        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
//        el4.sendKeys("alibaba");
        WebElement el3 = driver.findElement(By.id("com.xueqiu.android:id/home_search"));
        el3.click();
        WebElement el4 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
        el4.sendKeys("alibaba");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
