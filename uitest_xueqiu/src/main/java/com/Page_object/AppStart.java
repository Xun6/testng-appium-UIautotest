package com.Page_object;

import com.Util.ToWaitIn;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppStart {
    private static AndroidDriver driver;

    /**
     * 启动app应用，进入主页面
     * @throws MalformedURLException
     */
    public static MainPage start() throws MalformedURLException {

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

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, baseOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // 添加隐式等待，此为全局等待
        ToWaitIn.wait(driver);// 显示等待，灵活处理弹窗(封装非同一类下)
        return new MainPage(driver);
    }

    /**
     * 退出app
     * @throws InterruptedException
     */
    public static void quit() throws InterruptedException {
        Thread.sleep(10); //暂停10秒
        driver.quit(); //退出
    }
}
