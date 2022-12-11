package com.cn.jmw.uitls;


import java.io.File;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月11日 11:49
 * @Version 1.0
 */
public class ProfileSelector {

    public static void main(String[] args) {
        //YML只能转换成任意（Hashmap）
        HashMap choose = ProfileSelector.choose("/application.yml", HashMap.class);
        System.out.println(choose);
        //Properties只能转换成(Properties)
        Properties choose1 = ProfileSelector.choose("/hello.properties", Properties.class);
        System.out.println(choose1);
        //xml转换成任意对象需要和注解一起使用，转换成Map、JSON不需要
        HashMap choose2 = ProfileSelector.choose("/triedata-config.xml", HashMap.class);
        System.out.println(choose2);
    }

    public static <T extends Object> T choose(String file, Class<T> c) {
        return suffixSelector(file, c);
    }

    public static <T extends Object> T choose(File file, Class<T> c) {
        return suffixSelector(file.getAbsolutePath(), c);
    }

    public static <T extends Object> T suffixSelector(String file, Class<T> c) {
        if (file.endsWith("xml")) {
            return (T) XmlUtils.xmlToMap(file);
        } else if (file.endsWith("yml")) {
            //return T
            return (T) YamlUtils.builder().fileUrl(file).build(c);
        } else if (file.endsWith("properties")) {
            //return properties
            return (T) PropertiesUtils.builder().file(file).build();
        } else {
            throw new PropertiesIsNullOrUnknownException("Properties unknown.");
        }
    }


}
