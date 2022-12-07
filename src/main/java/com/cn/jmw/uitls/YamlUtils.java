package com.cn.jmw.uitls;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月06日 23:57
 * @Version 1.0
 */
public class YamlUtils {

    YamlUtils() {
    }

    public static YamlUtils.Builder builder() {
        return new YamlUtils.Builder();
    }

    public static void main(String[] args) {
        Object build = YamlUtils.builder().file("/application.yml")
                .build(HashMap.class);
        HashMap objByClass = Builder.getObjByClass(HashMap.class);
        System.out.println();
    }

    public static class Builder<T,K> {

        private T file;

        Builder() {
        }

        public YamlUtils.Builder file(T file) {
            this.file = file;
            return this;
        }

        public <K> K build(Class<K> clazz) {
            K k = null;
            Yaml yaml = new Yaml();
            if (file instanceof File) {
                try (InputStream inputStream = new FileInputStream((File) file);) {
                    k = yaml.loadAs(inputStream, clazz);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (file instanceof String) {
                try (InputStream inputStream = YamlUtils.class.getResourceAsStream((String)file)) {
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
