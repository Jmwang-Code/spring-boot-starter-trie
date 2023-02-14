package com.cn.jmw.trie.entity;

import com.cn.jmw.trie.TrieCode;

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

    private Map<String, TrieCode[]> map = new HashMap<>();

    public void setMap(Map<String, TrieCode[]> map) {
        this.map = map;
    }

    public Map<String, TrieCode[]> getMap() {
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
        return "TriePrefixQueryResult{" +
                "map=" + map +
                '}';
    }
}
