package com.cn.jmw.reader;


import java.io.File;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月11日 11:49
 * @Version 1.0
 */
public class ProfileSelector {


    public static <T extends Object> T choose(String file, Class<T> c) {
        return suffixSelector(file, c);
    }

    public static <T extends Object> T choose(File file, Class<T> c) {
        return suffixSelector(file.getAbsolutePath(), c);
    }

    public static <T extends Object> T suffixSelector(String file, Class<T> c) {
        if (file.endsWith("xml")) {
            if ("java.util.HashMap".equals(c.getName())) {
                return (T) XmlUtils.xmlToMap(file);
            }else {
                return XmlUtils.XmlToObject(c,file);
            }
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
