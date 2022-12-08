package com.cn.jmw.uitls;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月06日 23:57
 * @Version 1.0
 */
public class YamlUtils<T extends Object> {

    private T t;

//    static ObjectMapper MAPPER = new ObjectMapper();
//
//    static {
//        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public YamlUtils(T t) {
        super();
        this.t = t;
    }

    YamlUtils() {
    }

    public static YamlUtils.Builder builder() {
        return new YamlUtils.Builder();
    }

    public static void main(String[] args) {
        HashMap build = YamlUtils.builder().fileUrl("/application.yml")
                .build(HashMap.class);
    }

    public static class Builder {

        private File file;
        private String fileUrl;

        Builder() {
        }

        public YamlUtils.Builder file(File file) {
            this.file = file;
            return this;
        }
        public YamlUtils.Builder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public <K> K build(Class<K> clazz) {
            K k = null;
            Yaml yaml = new Yaml();
            if (file instanceof File) {
                try (InputStream inputStream = new FileInputStream(file);) {
                    k = yaml.loadAs(inputStream, clazz);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (fileUrl instanceof String) {
                try (InputStream inputStream = YamlUtils.class.getResourceAsStream(fileUrl)) {
                    k = yaml.loadAs(inputStream, clazz);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new PropertiesIsNullException("Unrecognized input parameter type");
            }
            return k;
        }

        public static <T> T getObjByClass(Class<T> clazz) {
            T t = null;
            try {
                t = clazz.getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return t;
        }

    }



}