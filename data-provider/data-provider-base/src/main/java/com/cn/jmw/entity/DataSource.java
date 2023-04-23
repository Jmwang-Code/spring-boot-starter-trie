package com.cn.jmw.entity;

import lombok.Data;

import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月09日 21:03
 * @Version 1.0
 */
@Data
public class DataSource {
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    //默认使用sql语句，而不是使用sql生成器
    private boolean useSql = true;
    private List<SqlCode> sqlCode;
    private boolean useSqlQenerator = false;
    private List<ProviderEntity.SqlQenerator> sqlQenerators;
    private int maxWaitMillis = 60 * 1000;
    private LoadOn loadOn;
}
