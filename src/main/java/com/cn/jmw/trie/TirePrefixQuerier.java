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

    private Tokenizer prefix;

    public TirePrefixQuerier(TrieNode trieRootNode, Tokenizer prefix) {
        this.prefix = prefix;
        this.trieRootNode = trieRootNode;
    }

    public TriePrefixQueryResult queryAllPrefix(){
        TriePrefixQueryResult triePrefixQueryResult = new TriePrefixQueryResult();
        //返回结果集
        Map<String, TrieCode[]> map = triePrefixQueryResult.getMap();

        //当前查询字符长度
        int length = this.prefix.length();

        //根节点探测
        TrieNode trieNode = this.trieRootNode;

        //定位树
        TrieNode location = location(trieNode, length);

        //钻取集合
        System.out.println(location);


        return triePrefixQueryResult;
    }

    private TrieNode location(TrieNode trieNode,int length){
        //定位树
        TrieNode trieNodeRe = trieNode;
        for (int i = 0; i < prefix.length()+1; i++) {
            /**
             * 1.如果到达终点，则将当前节点置空。
             * 2.如果未到达终点，则将将当前子树的引用指向
             */
            if (i == length) {
                //如果探测位置以及等于字符长度，说明到达终点
                break;
            } else {
                //获取当前位置字符
                int c = prefix.charAt(i);
                //当前前缀子树返回
                trieNodeRe = trieNodeRe.getBranch(c);
            }
            /**
             * 1.当前节点等于null,说明已经到底，可以考虑回退的问题了。
             */
            if (trieNodeRe == null) {
                trieNodeRe = this.trieRootNode;
            }
        }
        return trieNodeRe;
    }

    private void Drilling(Map<String, TrieCode[]> map,TrieNode trieNode){

    }
}
