package com.cn.jmw.loading;

import com.cn.jmw.JdbcProvider;
import com.cn.jmw.adapter.AdapterFactory;
import com.cn.jmw.adapter.JdbcAdapter;
import com.cn.jmw.entity.ProviderEntity;
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
        boolean test = AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(0)).test();
        log.info("test:" + test);
    }

    @Test
    public void test() {
//        System.out.println(providerEntity.getRunnableThreadNum());
//        for (int i = 0; i < providerEntity.getDataSources().size(); i++) {
////            AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(i));
//            System.out.println(AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(i)).test(providerEntity.getDataSources().get(i)));
//        }
        JdbcProvider jdbcProvider = new JdbcProvider();
        try {
            boolean test = jdbcProvider.test(providerEntity);
            System.out.println(test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void execute() {
        JdbcProvider jdbcProvider = new JdbcProvider();
        try {
            boolean execute = jdbcProvider.execute(providerEntity);
            System.out.println(execute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
