package com.Util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;

/**
 * 获取并解析yaml文件
 */
public class ReadYaml {
    public static List<String> readYamlUtil(){
        List<String> list = null;
        try{
            Yaml yaml = new Yaml();
            URL url = ReadYaml.class.getClassLoader().getResource("simpleParame.yml");
            if (url != null){
                //获取yaml文件中的配置数据，然后转化为 list
                list = yaml.load(new FileInputStream(url.getFile()));
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //测试
//    public static void main(String[] args) {
//        com.Util.ReadYaml.readYamlUtil();
//    }
}
