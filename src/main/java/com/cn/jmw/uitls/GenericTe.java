package com.cn.jmw.uitls;

import java.util.HashMap;

/**
 * @author jmw
 * @Description TODO
 * @date 2022年12月06日 23:57
 * @Version 1.0
 */
public class GenericTe<T extends Object> {//,K extends Object

    private String file;

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

    public GenericTe(T t) {
        super();
        this.t = t;
    }

//
//    public YamlUtils file(String file) {
//        this.file = file;
//        return this;
//    }
//
//    public <K> K build() {
//        K k = null;
//        Yaml yaml = new Yaml();
//       if (file instanceof String) {
//            try (InputStream inputStream = YamlUtils.class.getResourceAsStream((String) file)) {
//                k = (K) yaml.loadAs(inputStream, t.getClass());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            throw new PropertiesIsNullException("Unrecognized input parameter type");
//        }
//        return k;
//    }

    public static void main(String[] args) {
        GenericTe<HashMap> hashMapGenericTe = new GenericTe<>(new HashMap());
        HashMap t1 = hashMapGenericTe.getT();
        System.out.println(t1);
    }

}
