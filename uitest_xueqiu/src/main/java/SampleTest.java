import com.Util.ReadYaml;
import com.Util.ToWaitIn;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
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
        baseOptions.setCapability("resetKeyboard","true"); // 将键盘重置为原始状态
        baseOptions.setCapability("noReset","true"); //不停止应用，不清除数据
//        baseOptions.setCapability("udid","9a1faa47"); // 指定连接的运行设备
//        baseOptions.setCapability("chromedriverExecutable",""); //指定版本的chromedriver驱动，当切换到webview页面进行定位时所必要的依赖
//        baseOptions.setCapability("showChromedriverLog","true"); //打印分析webview时相关的chromedriver日志信息

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, baseOptions);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  // 添加隐式等待，此为全局等待
        ToWaitIn.wait(driver);// 显示等待，灵活处理弹窗(封装非同一类下)
    }

    /**
     * 断言测试
     */
    @Test
    public void assertCase(){
        // 通过xpath定位元素
        driver.findElement(By.xpath("//*[@text='热门' and contains(@resource-id,'title_text')]")).click();
        // 设置断言
//        assert (driver.findElement(By.xpath("//*[@text='视频']")))!=null;
        Assert.assertNotEquals(driver.findElement(By.xpath("//*[@text='热门']")),null);
        System.out.println(">>>>>>>>");
        System.out.println("打印断言结果："+driver.findElement(By.xpath("//*[@text='热门']")));
        System.out.println("<<<<<<<<<");
    }

    /**
     * 参数化数据测试
     * @param name
     */
    @Test(dataProvider = "data")    // 参数化数据
    public void sampleTest(String name) {
        // TODO MobileElement对象被弃用，
        //  详情见：https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md
        WebElement el3 = driver.findElement(By.id("com.xueqiu.android:id/home_search"));
        el3.click();
        WebElement el4 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
        el4.sendKeys(name);
        driver.findElement(By.id("name")).click();

        // double-tap the screen at a specific point 双击屏幕上的某一点(模拟手势操作)
        // TODO click to see detail: https://appiumpro.com/editions/30-ios-specific-touch-action-methods
        Map<String, Object> args = new HashMap<>();
        args.put("x", 100);
        args.put("y", 200);
        driver.executeScript("mobile: doubleClickGesture", args);
    }


    /**
     * native 切换到 webview 上下文，拓展css方式定位元素
     */
    @Test
    public void test_webview(){
        driver.findElement(By.xpath("//*[@text='私募']")).click();
        System.out.println(driver.getContext());  //打印获取的当前上下文名称
//        driver.context("");  //转换为给定的上下文
        System.out.println(driver.getContextHandles()); // 获取可用的上下文名称列表
    }


//    @Test
//    @Parameters("name") // 参数化驱动,从.xml 文件获取参数
//    public void testParam(String name){
//        WebElement el3 = driver.findElement(By.id("com.xueqiu.android:id/home_search"));
//        el3.click();
//        WebElement el4 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
//        el4.sendKeys(name);
//        driver.findElement(By.id("name")).click();
//    }

    /**
     * 检查手机系统信息测试
     */
    @Test
    public void test_performance(){
        // TODO 返回支持读取的系统状态信息类型，如cpu、内存、网络流量和电池
        List<String> performanceTypes = driver.getSupportedPerformanceDataTypes();
        System.out.println("\n");
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
    public void tearDown() throws InterruptedException {
        Thread.sleep(10); //暂停10秒
        driver.quit();
    }


//    // ==方法一： 参数化数据==
//    @DataProvider(name = "data")
//    public Object[] provideData(){
//        Object[] o = new Object[]{
//          "阿里巴巴",
//          "拼多多"
//        };
//        return o;
//    }

    // ==方法二： 从yaml文件中获取参数化数据==
    @DataProvider(name = "data")
    public Object[] yamlDataProvide(){
        List<String> list = ReadYaml.readYamlUtil();    //获取数据
        Object[] objects = new Object[list.size()];
        for (int i =0; i<list.size(); i++){
            objects[i] = list.get(i);
        }
        return objects;
    }
}
