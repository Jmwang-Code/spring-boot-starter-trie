package com.cn.jmw.uitls;

import java.io.*;
import java.util.Properties;

/**
 * @author jmw
 * @Description I want to define a loading mode.For example,reflect and annotate to load a file.
 * @date 2022年12月06日 23:56
 * @Version 1.0
 *
 * @commonCongraration("Files")
 * private Configuration configuration;
 *
 */
public class PropertiesUtils {

    PropertiesUtils(){}

    public static PropertiesUtils.Builder builder() {
        return new PropertiesUtils.Builder();
    }

    public static void main(String[] args) {
        Properties build = PropertiesUtils.builder().file("C:\\Users\\jmw\\Desktop\\jmw-code\\regression-search-tree\\src\\main\\resources\\hello.properties").build();
        System.out.println();
    }

    public static class Builder<T>{

        private T file;

        Builder(){}

        public PropertiesUtils.Builder file(T file) {
            this.file = file;
            return this;
        }

        public Properties build() {
            Properties properties = null;
            if (file instanceof File){
                properties = this.xmlToProperties((File) file);
            }else if (file instanceof String){
                properties = this.xmlToProperties((String) file);
            }else {
                throw new PropertiesIsNullException("properties is null");
            }
            return properties;
        }


        private Properties xmlToProperties(File file) {
            Properties properties = new Properties();
            try {
                properties.load(new BufferedReader(new FileReader(file)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties;
        }

        private Properties xmlToProperties(String file) {
            Properties properties = new Properties();
            try {
                properties.load(new BufferedReader(new FileReader(file)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties;
        }

    }
}
