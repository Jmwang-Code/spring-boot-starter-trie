package com.cn.jmw.service;

import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.entity.TrieQueryResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月20日 15:58
 * @Version 1.0
 */
@Service
public class TireServiceImpl implements TireService{
    private final Tire tire;

    public TireServiceImpl(Tire tire) {
        this.tire = tire;
    }

    @Override
    public boolean add(int[] word, MultiCodeMode mode, int code, int type) {
        return tire.add(word, mode, code, type);
    }

    @Override
    public boolean add(int[] word, MultiCodeMode mode, int code) {
        return tire.add(word, mode, code);
    }

    @Override
    public boolean add(int[] word, MultiCodeMode mode) {
        return tire.add(word, mode);
    }

    @Override
    public boolean add(String word, MultiCodeMode mode, int code, int type) {
        return tire.add(word, mode, code, type);
    }

    @Override
    public boolean add(String word, MultiCodeMode mode, int code) {
        return tire.add(word, mode, code);
    }

    @Override
    public boolean add(String word, MultiCodeMode mode) {
        return tire.add(word, mode);
    }

    @Override
    public TrieQueryResult get(String word) {
        return tire.get(word);
    }

    @Override
    public List<TrieQueryResult> getAll(String word) {
        return tire.getAll(word);
    }

    @Override
    public int getSize() {
        return tire.getSize();
    }

    @Override
    public TriePrefixQueryResult getPrefix(String word) {
        return tire.getPrefix(word);
    }

    @Override
    public boolean remove(String word, int code, int type) {
        return tire.remove(word,code,type);
    }

    @Override
    public boolean remove(int[] word, int code, int type) {
        return tire.remove(word,code,type);
    }

    @Override
    public int getDeep(String word) {
        return tire.getDeep(word);
    }

    @Override
    public void clear() {
        tire.clear();
    }
}
