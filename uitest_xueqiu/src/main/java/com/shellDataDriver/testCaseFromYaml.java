package com.shellDataDriver;

import com.Util.ToWaitIn;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 从yaml文件中管理测试步骤，封装测试case
 */
public class testCaseFromYaml {
    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // 添加隐式等待，此为全局等待
        ToWaitIn.wait(driver);  // 显示等待，灵活处理弹窗
    }

    // yaml文件数据驱动测试
    @Test
    public void testcaseDemo(){
            CaseDemo caseDemo = new CaseDemo();
            caseDemo.run(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
