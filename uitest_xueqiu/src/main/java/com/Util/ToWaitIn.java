package com.Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.AppiumFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 封装显示等待,处理权限弹窗
 */
public class ToWaitIn {

    // ==封装显示等待,处理权限弹窗==
    public static void wait(AndroidDriver driver){
        // 添加显示等待,超时时间5秒, TODO 用来灵活处理弹窗
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // 等待直到获取元素或抛出异常
        try{
            WebElement element = webDriverWait.until(new AppiumFunction<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver input) {
                    return input.findElement(By.id("com.xueqiu.android:id/tv_agree"));
                }
            });
            System.out.println("是否定位到元素："+element.isDisplayed());
            element.click();
        } catch (Exception e){
            System.out.println("捕获超时异常，没有找到弹窗元素！");
            e.printStackTrace();
        }
    }
}
