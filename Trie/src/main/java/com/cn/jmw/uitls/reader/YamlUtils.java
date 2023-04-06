package com.cn.jmw.uitls.reader;

import org.yaml.snakeyaml.Yaml;

import java.io.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月06日 23:57
 * @Version 1.0
 */
public class YamlUtils<T extends Object>  {

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

    public static Builder builder() {
        return new Builder();
    }

//    public static void main(String[] args) {
//        HashMap build = YamlUtils.builder().fileUrl("/application.yml")
//                .build(HashMap.class);
//    }

    public static class Builder implements InterUtils {

        private File file;
        private String fileUrl;

        Builder() {
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }
        public Builder fileUrl(String fileUrl) {
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
                throw new PropertiesIsNullOrUnknownException("Unrecognized input parameter type");
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