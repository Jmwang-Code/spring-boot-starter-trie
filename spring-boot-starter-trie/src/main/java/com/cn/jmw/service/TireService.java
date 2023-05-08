package com.cn.jmw.service;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.entity.TrieQueryResult;

import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月20日 15:56
 * @Version 1.0
 */
public interface TireService {

    /**
     * 添加新词
     * word 单词数组
     * mode 选择添加模式
     * code 绑定代码
     * type 扩展类型
     */
    boolean add(int[] word, MultiCodeMode mode, int code, int type);

    /**
     * 添加新词
     * word 单词数组
     * mode 选择添加模式
     * code 绑定代码
     */
    boolean add(int[] word, MultiCodeMode mode, int code);

    /**
     * 添加新词
     * word 单词数组
     * mode 选择添加模式
     */
    boolean add(int[] word, MultiCodeMode mode);

    /**
     * 添加新词
     * word 单词字符串
     * mode 选择添加模式
     * code 绑定代码
     * type 扩展类型
     */
    boolean add(String word, MultiCodeMode mode, int code, int type);

    /**
     * 添加新词
     * word 单词字符串
     * mode 选择添加模式
     * code 绑定代码
     */
    boolean add(String word, MultiCodeMode mode, int code);

    /**
     * 添加新词
     * word 单词字符串
     * mode 选择添加模式
     */
    boolean add(String word, MultiCodeMode mode);

    /**
     * 在一段话内 通过关键词 获取前缀树信息
     * word 单词字符串
     */
    TrieQueryResult get(String word);

    /**
     * 获取所有编码
     */
    public List<TrieQueryResult> getAll(String word);

    /**
     * 获取单词数据量
     */
    int getSize();

    /**
     * 获取通过前缀获取所有前缀组
     */
    TriePrefixQueryResult getPrefix(String word);

    /**
     * 通过字符串 移除单词以及对应的编码
     */
    boolean remove(String word, int code, int type);

    /**
     * 通过数组 移除单词以及对应的编码
     */
    boolean remove(int[] word, int code, int type);

    /**
     * 获取某个单词的辅助树深度
     */
    int getDeep(String word);

    /**
     * @deprecated 禁用,会删除缓存树，如果连接缓存源会删除所有缓存
     */
    void clear();
}
