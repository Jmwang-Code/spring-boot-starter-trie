package com.cn.jmw.trie;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author jmw
 * @Description 前缀树节点
 * @date 2022年12月05日 17:35
 * @Version 1.0
 */
public class TrieNode implements Comparable<TrieNode>, Serializable {

    @Serial
    private static final long serialVersionUID = 5727284941846160588L;

    /**
     * 树节点字符
     */
    private int c;

    /**
     * 节点上绑定的Code值
     */
    private int code;

    /**
     * 状态 (1:继续 2:是个词语但是还可以继续 3:确定)
     */
    private byte status = 1;

    /**
     * Code类型，用于扩展
     */
    private byte type;

    /**
     * 无参构造器
     */
    public TrieNode() {}

    /**
     * @see #c
     * c
     */
    private TrieNode(int c) {
        this.c = c;
    }

    /**
     * @see #c
     * c
     * @see #status
     * status
     */
    private TrieNode(int c, int status) {
        this.c = c;
        this.status = (byte) status;
    }

    /**
     * @see #c
     * c
     * @see #status
     * status
     * @see #code
     * code
     */
    public TrieNode(int c, int status, int code) {
        this.c = c;
        this.status = (byte) status;
        this.code = code;
    }

    /**
     * @see #c
     * c
     * @see #status
     * status
     * @see #code
     * code
     * @see #type
     * type
     */
    public TrieNode(int c, int status, int code, int type) {
        this.c = c;
        this.status = (byte) status;
        this.code = code;
        this.type = (byte) type;
    }

    /**
     * @Param [trieNode]
     * @return int
     * @exception
     * @Date 2022/12/5 18:51
     */
    @Override
    public int compareTo(TrieNode trieNode){
        int tc = this.c,oc = trieNode.c;
        return tc > oc ? 1 : (tc == oc ? 0 : -1);
    }
}
