package com.cn.jmw.adapter;

import com.cn.jmw.entity.DataSource;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.TrieNode;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jmw
 * @Description 如果连接数据库 需要进行自定义扩展 则继承JdbcAdapter类
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
@Slf4j
public class MysqlAdapter extends JdbcAdapter {

    public MysqlAdapter(DataSource dataSource, Tire trieNode) {
        super(dataSource, trieNode);
    }
}
