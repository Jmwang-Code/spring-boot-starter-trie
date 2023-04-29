package com.cn.jmw.loading;

import com.cn.jmw.JdbcProvider;
import com.cn.jmw.adapter.AdapterFactory;
import com.cn.jmw.adapter.JdbcAdapter;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.provider.AbstractFactoryProvider;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.TrieNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jmw
 * @Description TODO
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class JdbcAdapterTest {
    @Autowired
    private ProviderEntity providerEntity;

    @Test
    public void jdbcAdapter() {
        System.out.println(providerEntity.getRunnableThreadNum());
        boolean test = AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(0),new Tire()).test();
        log.info("test:" + test);
    }

    @Test
    public void test() {
        AbstractFactoryProvider jdbcProvider = new JdbcProvider(new Tire());
        try {
            boolean test = jdbcProvider.test(providerEntity);
            System.out.println(test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void execute() {
        AbstractFactoryProvider jdbcProvider = new JdbcProvider(new Tire());
        try {
            boolean execute = jdbcProvider.execute(providerEntity);
            System.out.println(execute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
