package com.Page_object;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 主页面
 */
public class MainPage {
    protected AndroidDriver driver;

    public MainPage(AndroidDriver driver){
        this.driver = driver;
    }

    /**
     * 在主页面点击搜索功能栏
     * @return SearchPage object
     */
    public SearchPage toSearch(){
        WebElement el3 = driver.findElement(By.id("com.xueqiu.android:id/home_search"));
        el3.click();
        return new SearchPage(driver);
    }
}
