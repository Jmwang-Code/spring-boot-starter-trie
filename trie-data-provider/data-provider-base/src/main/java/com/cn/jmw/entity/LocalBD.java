package com.cn.jmw.entity;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月27日 14:21
 * @Version 1.0
 */
public enum LocalBD {

    H2(System.getProperty("user.dir").replace("\\","/")+ "/local/h2/h2.cache")
    ,SQLITE(System.getProperty("user.dir").replace("\\","/")+ "/local/sqlite/sqlite.cache");

    private String path;

    public String getPath(){
        return path;
    }

    LocalBD(String path) {
        this.path = path;
    }
}
