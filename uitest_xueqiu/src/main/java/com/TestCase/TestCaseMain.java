package com.TestCase;

import com.Page_object.AppStart;
import com.Page_object.SearchPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestCaseMain {
    @BeforeTest
    public void setUp() throws MalformedURLException {
        AppStart.start().toSearch().search("腾讯");   //启动app，进入搜索页面
    }
    @Test
    public void test_search(){

    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        AppStart.quit();    //退出app
    }
}
