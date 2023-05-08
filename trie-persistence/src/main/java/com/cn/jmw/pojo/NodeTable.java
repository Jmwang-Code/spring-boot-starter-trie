package com.cn.jmw.pojo;


import lombok.Data;

@Data
/**
 * @author jmw
 * @Description H2存储节点表
 * @date 2023年04月21日 16:50
 * @Version 1.0
 */
public class NodeTable {

    private int id;

    private String name;

    private int code;

    private int type;

    private int deleted;
}
