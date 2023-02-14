package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.tokenizer.Tokenizer;

import java.util.Map;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月13日 16:40
 * @Version 1.0
 */
public class TirePrefixQuerier {

    private TrieNode trieRootNode;

    private Tokenizer content;

    public TirePrefixQuerier(TrieNode trieRootNode, Tokenizer content) {
        this.content = content;
        this.trieRootNode = trieRootNode;
    }

    public TriePrefixQueryResult queryAllPrefix(){
        TriePrefixQueryResult triePrefixQueryResult = new TriePrefixQueryResult();

        //当前查询字符长度
        int length = this.content.length();

        //根节点探测
        TrieNode trieNode = this.trieRootNode;



        Map<String, TrieCode[]> map = triePrefixQueryResult.getMap();


        return triePrefixQueryResult;
    }
}
