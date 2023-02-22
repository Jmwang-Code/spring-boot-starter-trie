package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.*;
import com.cn.jmw.trie.tokenizer.TokenizerManager;
import com.cn.jmw.trie.tokenizer.TokenizerObject;
import com.cn.jmw.trie.tokenizer.TokenizerUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月21日 16:08
 * @Version 1.0
 */
public class Tire<K, V> implements Serializable {

    private final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private final static Lock r = rwl.readLock();

    private final static Lock w = rwl.writeLock();

    private transient int deep = 0;

    private transient int size = 0;

//    private transient int modCount = 0;

    private TrieNode mainTree;
    /**
     * 比较器
     */
    private final Comparator<? super K> comparator;

    @Serial
    private static final long serialVersionUID = -5026535756839841605L;

    public Tire() {
        this.comparator = null;
        mainTree = new TrieNode();
    }

    public Tire(Comparator<? super K> comparator) {
        this.comparator = comparator;
        mainTree = new TrieNode();
    }

    public boolean add(int[] word, MultiCodeMode mode, int code, int type) {
        w.lock();
        try {
            boolean add = mainTree.add(word, mode, code, type);
            size++;
            return add;
        } finally {
            w.unlock();
        }
    }

    public boolean add(int[] word, MultiCodeMode mode, int code) {
        return add(word, mode, code, -1);
    }

    public boolean add(int[] word, MultiCodeMode mode) {
        return add(word, mode, -1, -1);
    }

    public boolean add(String word, MultiCodeMode mode, int code, int type) {
        w.lock();
        try {
            boolean add = mainTree.add(TokenizerUtil.codePoints(word), mode, code, type);
            size++;
            return add;
        } finally {
            w.unlock();
        }
    }

    public boolean add(String word, MultiCodeMode mode, int code) {
        return add(TokenizerUtil.codePoints(word), mode, code, -1);
    }

    public boolean add(String word, MultiCodeMode mode) {
        return add(TokenizerUtil.codePoints(word), mode, -1, -1);
    }

    public TrieQueryResult get(String word) {
        r.lock();
        try {
            TrieQuerier trieQuerier = new TrieQuerier(mainTree, new TokenizerObject(word), true);
            TrieQueryResult query = trieQuerier.query();
            return query;
        } finally {
            r.unlock();
        }
    }

    public TriePrefixQueryResult getPrefix(String word) {
        r.lock();
        try {
            TirePrefixQuerier tirePrefixQuerier = new TirePrefixQuerier(mainTree, new TokenizerObject(word));
            TriePrefixQueryResult triePrefixQueryResult = tirePrefixQuerier.queryAllPrefix();
            return triePrefixQueryResult;
        } finally {
            r.unlock();
        }
    }

    public void clear() {
        mainTree = null;
        size = 0;
        deep = 0;
    }

    /**
     * 必须是具体的字符串，不能模糊
     */
    public boolean remove(String word, int code, int type) {
        //TODO
        w.lock();
        try {
            boolean remove = mainTree.remove(TokenizerUtil.codePoints(word), code, type);
            size--;
            return remove;
        } finally {
            r.unlock();
        }
    }

    /**
     * 必须是具体的字符串，不能模糊
     */
    public boolean remove(int[] word, int code, int type) {
        //TODO
        w.lock();
        try {
            boolean remove = mainTree.remove(word, code, type);
            size--;
            return remove;
        } finally {
            r.unlock();
        }
    }

    /**
     * 获取深度
     */
    public int getDeep() {
        //TODO
        return -1;
    }

}



