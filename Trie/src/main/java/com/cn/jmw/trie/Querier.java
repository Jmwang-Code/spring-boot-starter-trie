package com.cn.jmw.trie;

import com.cn.jmw.trie.tokenizer.Tokenizer;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月17日 15:00
 * @Version 1.0
 */
public abstract class Querier {

    /**
     * 前缀树根节点
     */
    TrieNode trieRootNode;

    /**
     * 查询字符串
     */
    Tokenizer content;
}
