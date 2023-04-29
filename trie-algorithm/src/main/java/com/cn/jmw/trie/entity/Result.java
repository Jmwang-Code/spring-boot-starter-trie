package com.cn.jmw.trie.entity;

/**
 * @author jmw
 * @Description 结果集
 * @date 2023年01月16日 20:20
 * @Version 1.0
 */
public class Result<T> {

    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
