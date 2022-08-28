import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.AppiumFunction;
import io.appium.java_client.remote.options.BaseOptions;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
        baseOptions.setCapability("resetKeyboard","true"); // 将键盘重置为原始状态
        baseOptions.setCapability("noReset","true"); //不停止应用，不清除数据

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, baseOptions);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  // 添加隐式等待，此为全局等待
    }

    @Test
    public void sampleTest() {
        // 添加显示等待,超时时间5秒, TODO 用来灵活处理弹窗
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        // 等待直到获取元素或抛出异常
        try{
            WebElement element = webDriverWait.until(new AppiumFunction<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver input) {
                    return input.findElement(By.id("com.xueqiu.android:id/tv_agree"));
                }
            });
            System.out.println(element.isDisplayed());
            element.click();
        } catch (Exception e){
            System.out.println("捕获超时异常，没有找到弹窗元素！");
            e.printStackTrace();
        }

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

    @Test
    public void test_performance(){
        // 返回支持读取的系统状态信息类型，如cpu、内存、网络流量和电池
        List<String> performanceTypes = driver.getSupportedPerformanceDataTypes();
        System.out.println("------输出系统状态信息类型--------");
        for (String per : performanceTypes){
            try{
                // 返回支持读取的系统状态具体信息，如cpu、内存、网络流量和电池
                List<List<Object>> performanceData = driver.getPerformanceData("com.xueqiu.android", per, 5);
                System.out.println(performanceData.toString());
            }catch (Exception e){
                System.out.println(per.toString());
            }
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
