package com.cn.jmw.trie.entity;

import com.cn.jmw.trie.TrieCode;
import com.cn.jmw.trie.tokenizer.TokenizerUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月13日 16:41
 * @Version 1.0
 */
public class TriePrefixQueryResult {

    private Map<int[], TrieCode[]> map = new HashMap<>();

    public void setMap(Map<int[], TrieCode[]> map) {
        this.map = map;
    }

    public Map<int[], TrieCode[]> getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriePrefixQueryResult that = (TriePrefixQueryResult) o;
        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<int[], TrieCode[]> entry : map.entrySet()) {
            int[] key = entry.getKey();
            stringBuilder.append(TokenizerUtil.toString(key)).append(",");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    /**
     * TODO
     * key_sort
     */

    /**
     * TODO
     * value_sort
     */

}
