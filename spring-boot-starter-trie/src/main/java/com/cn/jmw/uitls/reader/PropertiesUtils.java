package com.cn.jmw.uitls.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * @author jmw
 * @Description I want to define a loading mode.For example,reflect and annotate to load a file.
 * @date 2022年12月06日 23:56
 * @Version 1.0
 * @commonCongraration("Files") private Configuration configuration;
 */
public class PropertiesUtils {

    PropertiesUtils() {
    }

    public static Builder builder() {
        return new Builder();
    }

//    public static void main(String[] args) {
//        Properties build = PropertiesUtils.builder().file("C:\\Users\\jmw\\Desktop\\jmw-code\\regression-search-tree\\src\\main\\resources\\hello.properties").build();
//        System.out.println();
//    }

    public static class Builder<T> implements InterUtils {

        private T file;

        Builder() {
        }

        public Builder file(T file) {
            this.file = file;
            return this;
        }

        public Properties build() {
            Properties properties = null;
            InputStream resourceAsStream = null;
            try {
                if (file instanceof File) {
                    resourceAsStream = PropertiesUtils.class.getResourceAsStream(((File) file).getAbsolutePath());
                    properties = this.xmlToProperties(resourceAsStream);
                } else if (file instanceof String) {
                    resourceAsStream = PropertiesUtils.class.getResourceAsStream((String) file);
                    properties = this.xmlToProperties(resourceAsStream);
                } else {
                    throw new PropertiesIsNullOrUnknownException("properties is null");
                }
                return properties;
            } finally {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


        private Properties xmlToProperties(InputStream resourceAsStream) {
            Properties properties = new Properties();
            try {
                properties.load(resourceAsStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties;
        }

        private Properties xmlToProperties(Reader reader) {
            Properties properties = new Properties();
            try {
                properties.load(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties;
        }

    }
}
