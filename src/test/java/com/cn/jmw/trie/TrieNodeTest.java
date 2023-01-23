package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.tokenizer.TokenizerFactory;
import com.cn.jmw.trie.tokenizer.TokenizerManager;
import com.cn.jmw.trie.tokenizer.TokenizerUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年01月06日 11:15
 * @Version 1.0
 */
public class TrieNodeTest {

    static TrieNode trieNode = new TrieNode();


    Object data;
    boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * 更新缓存后锁定降级（异常处理为 在非嵌套中处理多个锁时特别棘手 时尚）
     * @return void
     * @Param []
     * @Date 2023/1/15 20:20
     */
    @Test
    public void ReentrantReadWriteLockTest() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                    data = 1;
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
            System.out.println(data);
        } finally {
            rwl.readLock().unlock();
        }
    }

    @Test
    public void add() {
        trieNode.add(new int[]{1, 2, 3}, MultiCodeMode.Append,1, 1);
        System.out.println(trieNode);
    }

    @Test
    public void remove() {
        trieNode.remove(new int[]{1, 2, 3}, 1, 1);
        System.out.println(trieNode);
    }

    @Test
    public void select() {

        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Replace,1, 0);

        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Replace,3, 0);

        trieNode.add(TokenizerManager.getIntArray("南京"), MultiCodeMode.Drop,1, 0);

        //这个GET只是 寻找当前层次的数据INT
        TrieNode trieNode1 = trieNode.get(TokenizerManager.getIntLocal("南"));
        System.out.println(trieNode1+"\n");
        trieNode1.print();
//        trieNode.print();
    }
}