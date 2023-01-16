package com.cn.jmw.trie;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jmw
 * @Description 前缀树节点
 * @date 2022年12月05日 17:35
 * @Version 1.0
 * @see #add(int[], int, int)
 * @see #remove(int[], int, int)
 */
public class TrieNode implements Comparable<TrieNode>, Serializable {

    @Serial
    private static final long serialVersionUID = 5727284941846160588L;

    private final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private final static Lock r = rwl.readLock();

    private final static Lock w = rwl.writeLock();

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
     * 分支信息
     */
    public TrieNode[] branches = null;

    /**
     * 无参构造器
     */
    public TrieNode() {
    }

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
     * @return int
     * @throws
     * @Param [trieNode]
     * @Date 2022/12/5 18:51
     */
    @Override
    public int compareTo(TrieNode trieNode) {
        int tc = this.c, oc = trieNode.c;
        return tc > oc ? 1 : (tc == oc ? 0 : -1);
    }

    /**
     * 获取TrieNode,节点通过前缀方式
     * @return com.cn.jmw.trie.TrieNode
     * @throws
     * @Param [c]
     * @Date 2023/1/16 15:52
     */
    public TrieNode get(int c) {
        r.lock();
        try {
            return getBranch(c);
        } finally {
            r.unlock();
        }
    }

    /**
     * 通过传入字符查询当前数组索引，获取分支节点
     * @Param [c]
     * @return com.cn.jmw.trie.TrieNode
     * @exception
     * @Date 2023/1/16 19:38
     */
    public TrieNode getBranch(int c) {
        r.lock();
        try {
            int index = getIndex(c);
            if (index<0){
                return null;
            }else {
                return this.branches[index];
            }
//            return index < 0 ? null : this.branches[index];
        } finally {
            r.unlock();
        }
    }

    /**
     * 获取数组索引
     * @Param [c]
     * @return int
     * @exception
     * @Date 2023/1/16 19:39
     */
    public int getIndex(int c) {
        r.lock();
        try {
            if (branches == null) {
                return -1;
            }
            int i = Arrays.binarySearch(this.branches, new TrieNode(c));
            return i;
//            return branches==null?-1:Arrays.binarySearch(this.branches, new TrieNode(c));
        } finally {
            r.unlock();
        }
    }


    /**
     * @return com.cn.jmw.trie.TrieNode
     * @throws
     * @Param [newBranch]
     * @Date 2022/12/6 20:59
     * @description 添加词组
     */
    public boolean add(int[] word, int code, int type) {
        w.lock();
        try {

            return true;
        } finally {
            w.unlock();
        }
    }

    /**
     * @return boolean
     * @throws
     * @Param [word, code, type]
     * @Date 2022/12/6 21:07
     * description 删除词组
     */
    public boolean remove(int[] word, int code, int type) {
        w.lock();
        try {

            return true;
        } finally {
            w.unlock();
        }
    }


}
