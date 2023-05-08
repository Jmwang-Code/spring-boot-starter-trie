package com.cn.jmw;

import com.cn.jmw.adapter.Adapter;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月11日 16:17
 * @Version 1.0
 */
public enum AdapterEnum {

    JDBC("com.cn.jmw.adapter.JdbcAdapter"),
    MYSQL("com.cn.jmw.adapter.MysqlAdapter"),
    ORACLE,
    SQLSERVER,
//    HIVE,
//    HBASE,
//    HDFS,
//    KAFKA,
//    REDIS,
//    ES,
//    SOLR,
    SQLITE,
    H2,
    MONGODB("com.cn.jmw.adapter.MongoDbAdapter");

    public String className;

    public String getClassName() {
        return className;
    }

    AdapterEnum(){

    }

    AdapterEnum(String className){
        this.className = className;
    }

    public static AdapterEnum getAdapterEnum(String name){
        for (AdapterEnum adapterEnum : AdapterEnum.values()) {
            if(adapterEnum.name().equals(name)){
                return adapterEnum;
            }
        }
        return null;
    }
}
