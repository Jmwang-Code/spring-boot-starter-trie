package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.entity.TrieQueryResult;
import com.cn.jmw.trie.tokenizer.TokenizerManager;
import com.cn.jmw.trie.tokenizer.TokenizerObject;
import org.junit.jupiter.api.Test;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月07日 17:36
 * @Version 1.0
 */
public class TrieQuerierTest {

    static TrieNode trieNode = new TrieNode();

    {
        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南宁"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天门"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天南"), MultiCodeMode.Replace, 1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天"), MultiCodeMode.Replace, 1, 0);
    }

    /**
     * @Param []
     * @return void
     * @exception
     * @Date 2023/2/20 10:12
     * 常数项-查询-前缀
     * 1-2ms
     */
    @Test
    public void constantItemQueryPrefix(){
        long l = System.currentTimeMillis();
        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("阿达的南天门发放"), true);

        TrieQueryResult query = trieQuerier.query();
        String word = query.getWord();
        System.out.println(word);
        long end = System.currentTimeMillis() - l;
        System.out.println(end);
    }

    /**
     * @Param []
     * @return void
     * @exception
     * @Date 2023/2/20 10:15
     * 千万级别-N-项查询前缀
     * 1-2ms
     */
    @Test
    public void tenMillionNQueryPrefixes() {
        for (int i = 0; i < 10000000; i++) {
            int[] arr = new int[1 + (int) (Math.random() * 10)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = 19969 + (int) (Math.random() * 20000);
            }
            trieNode.add(arr, MultiCodeMode.Replace, 1, 0);
        }

        long l = System.currentTimeMillis();
        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("阿达的南天门发放"), true);
        int deep = trieQuerier.getDeep();
        System.out.println("deep:"+deep);
        TrieQueryResult query = trieQuerier.query();
        String word = query.getWord();
        System.out.println(word);
        long end = System.currentTimeMillis() - l;
        System.out.println(end);
    }

    @Test
    public void querier() {
//        TrieNode trieNode1 = trieNode.get(TokenizerManager.getIntLocal("南"));
//        trieNode1.print();

        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("阿达的南天门发放"), true);
        trieNode.print();
        int deep = trieQuerier.getDeep();
        System.out.println("deep:"+deep);
        TrieQueryResult query = trieQuerier.query();
        String word = query.getWord();
        TrieCode[] codes = query.getCodes();
        System.out.println(word);
        int offset = query.getOffset();
//        System.out.println(codes);
        System.out.println(offset);

    }





}