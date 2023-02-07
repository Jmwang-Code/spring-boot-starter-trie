package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
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
        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Replace,1, 0);
        trieNode.add(TokenizerManager.getIntArray("南宁"), MultiCodeMode.Replace,1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天门"), MultiCodeMode.Replace,1, 0);
        trieNode.add(TokenizerManager.getIntArray("南天南"), MultiCodeMode.Replace,1, 0);
    }

    @Test
    public void querier(){
        TrieNode trieNode1 = trieNode.get(TokenizerManager.getIntLocal("南"));
        trieNode1.print();

        TrieQuerier trieQuerier = new TrieQuerier(trieNode, new TokenizerObject("南京"), false);

        TrieQueryResult query = trieQuerier.query();
        TrieCode[] codes = query.getCodes();
        int offset = query.getOffset();
        String word = query.getWord();
        System.out.println(codes);
        System.out.println(word);
        System.out.println(offset);
    }
}
