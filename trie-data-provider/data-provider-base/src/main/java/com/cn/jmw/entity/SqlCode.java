package com.cn.jmw.entity;

import lombok.Data;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月19日 14:44
 * @Version 1.0
 */
@Data
public class SqlCode {

    //sql语句
    private String sql;
    //前缀树单词
    private String str;
    //前缀code
    private String code;
    //前缀类型
    private String type;
    //前缀扩展
    private String expand;
    //加载模式
    private LoadOn loadOn;
}
