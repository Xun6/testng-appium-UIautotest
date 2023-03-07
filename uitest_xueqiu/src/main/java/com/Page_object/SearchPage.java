package com.Page_object;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPage {
    protected AndroidDriver driver;
    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * 搜索框搜索
     * @param name 搜索内容
     * @return SearchPage object
     */
    public SearchPage search(String name){
        WebElement el4 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));
        el4.sendKeys(name);
        driver.findElement(By.id("name")).click();
        return new SearchPage(driver);
    }
}
