package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.tokenizer.TokenizerManager;
import com.cn.jmw.trie.tokenizer.TokenizerObject;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author jmw
 * @Description TODO
 * @date 2023��02��13�� 16:53
 * @Version 1.0
 */
public class TirePrefixQuerierTest {

    static TrieNode trieNode = new TrieNode();

    {
        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南宁"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天门"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天南"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天"), MultiCodeMode.Replace, 1, 0);
    }

    @Test
    public void one(){
        for (int i = 0; i < 10000000; i++) {
            int[] arr= new int[1+(int)(Math.random()*10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = 19969+(int)(Math.random()*20000);
            }
            trieNode.add(arr, MultiCodeMode.Replace, 1, 0);
        }

        long l = System.currentTimeMillis();
        TirePrefixQuerier tirePrefixQuerier = new TirePrefixQuerier(trieNode, new TokenizerObject("南"));
        TriePrefixQueryResult triePrefixQueryResult = tirePrefixQuerier.queryAllPrefix();
        System.out.println(triePrefixQueryResult);
        long end = System.currentTimeMillis() - l;
        System.out.println(end);
    }
}
