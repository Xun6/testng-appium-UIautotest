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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        // TODO DesiredCapabilities对象已过时不建议使用，
        //  详情见：https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md
        BaseOptions baseOptions = new BaseOptions();
        baseOptions.setCapability("platformName","Android");
        baseOptions.setCapability("deviceName", "vivo");
        baseOptions.setCapability("appPackage", "com.xueqiu.android");
        baseOptions.setCapability("appActivity", ".view.WelcomeActivityAlias");
        baseOptions.setCapability("autoGrantPermissions", "true");
        baseOptions.setCapability("unicodeKeyboard","true"); //使用unicode编码的键盘，可输入中文

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, baseOptions);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  // 添加隐式等待，此为全局等待
    }

    @Test
    public void sampleTest() {
        // TODO MobileElement对象被弃用，
        //  详情见：https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md
        WebElement el3 = driver.findElement(By.id("com.xueqiu.android:id/home_search"));
        el3.click();
        WebElement el4 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
        el4.sendKeys("阿里巴巴");

        // double-tap the screen at a specific point 双击屏幕上的某一点(模拟手势操作)
        // TODO click to see detail: https://appiumpro.com/editions/30-ios-specific-touch-action-methods
        Map<String, Object> args = new HashMap<>();
        args.put("x", 100);
        args.put("y", 200);
        driver.executeScript("mobile: doubleClickGesture", args);

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
