package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.tokenizer.TokenizerManager;
import com.cn.jmw.trie.tokenizer.TokenizerObject;

/**
 * @author jmw
 * @Description TODO
 * @date 2023��02��13�� 16:53
 * @Version 1.0
 */
public class TirePrefixQuerierTest {

    static TrieNode trieNode = new TrieNode();

    {
        trieNode.add(TokenizerManager.getIntArray("�Ͼ�"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("����"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("������"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("������"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("����"), MultiCodeMode.Replace, 1, 0);
    }

    public void one(){
        TirePrefixQuerier ���� = new TirePrefixQuerier(trieNode, new TokenizerObject("����"));
        TriePrefixQueryResult triePrefixQueryResult = ����.queryAllPrefix();
        System.out.println(triePrefixQueryResult);
    }
}
