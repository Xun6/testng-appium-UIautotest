package com.shellDataDriver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 测试用例逻辑封装，从yaml文件中读取操作步骤
 */
public class CaseDemo {

    // ====使用指定类型存储读取的yaml数据====
    public static List<Map<String,String>> getYaml() {
        List<Map<String,String>> list = null;
        Yaml yaml = new Yaml();
        URL url = CaseDemo.class.getClassLoader().getResource("testCase.yml"); //获取文件路径
        try {
            list = yaml.load(new FileInputStream(url.getFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(list.toString()); //打印获取的数据
        return list;
    }


    // =====用例执行逻辑=====
    public void run(AndroidDriver driver) {
        List<Map<String,String>> mapList = getYaml();
        WebElement webElement = null;
        for (Map<String,String> map : mapList){
            if (map.containsKey("id")){
                webElement = driver.findElement(By.id(map.get("id")));
                webElement.click();
            } else if (map.containsKey("xpath")) {
                webElement = driver.findElement(By.xpath(map.get("xpath")));
                webElement.click();
            } else {
                System.out.println("没有找到id！！");
            }

            if (map.containsKey("input")){
                webElement.sendKeys(map.get("input"));
            }
        }
    }


    // 测试一下
//    public static void main(String[] args) throws FileNotFoundException {
//        CaseDemo.getYaml();
////        CaseDemo.yamlDataTransf();
//    }
}
