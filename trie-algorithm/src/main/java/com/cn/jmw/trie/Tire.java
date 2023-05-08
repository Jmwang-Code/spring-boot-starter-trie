package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.entity.TrieQueryResult;
import com.cn.jmw.trie.tokenizer.TokenizerObject;
import com.cn.jmw.trie.tokenizer.TokenizerUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
//无法做到实时刷新
    private transient int deep = 0;

    private transient int size = 0;

//    private transient int modCount = 0;

    private TrieNode mainTree;

    public TrieNode getMainTree() {
        return mainTree;
    }

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
        if (!lengthLimit(word))return false;
        w.lock();
        try {
            boolean add = mainTree.add(word, mode, code, type);
            if (add)size++;
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
        if (!lengthLimit(word))return false;
        w.lock();
        try {
            boolean add = mainTree.add(TokenizerUtil.codePoints(word), mode, code, type);
            if (add)size++;
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

    //获取第一个匹配到的数据
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

    //获取所有匹配到的数据
    public List<TrieQueryResult> getAll(String word) {
        r.lock();
        try {
            TrieQuerier trieQuerier = new TrieQuerier(mainTree, new TokenizerObject(word), false);
            List<TrieQueryResult> query = trieQuerier.queryAll();
            return query;
        } finally {
            r.unlock();
        }
    }

    //获取所有匹配到前缀的数据
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
        w.lock();
        try {
            mainTree = null;
            size = 0;
            deep = 0;
        } finally {
            w.unlock();
        }
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
            w.unlock();
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
            w.unlock();
        }
    }

    public int getSize() {
        r.lock();
        try {
            return size;
        } finally {
            r.unlock();
        }
    }

    /**
     * 获取辅助子树深度
     */
    public int getDeep(String word) {
        TrieQuerier trieQuerier = new TrieQuerier(mainTree, new TokenizerObject(word), true);
        return trieQuerier.getDeep();
    }

    public boolean lengthLimit(int[] word) {
        return word.length<=30;
    }

    public boolean lengthLimit(String word) {
        return word.length()<=30;
    }

}



