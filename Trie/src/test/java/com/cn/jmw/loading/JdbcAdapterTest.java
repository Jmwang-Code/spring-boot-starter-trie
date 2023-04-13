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
        boolean test = new JdbcAdapter().test(providerEntity.getDataSources().get(0));
        log.info("test:"+test);
    }

    @Test
    public void createDataAdapter()  {
//        System.out.println(providerEntity.getRunnableThreadNum());
//        for (int i = 0; i < providerEntity.getDataSources().size(); i++) {
//            AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(i));
//        }
        JdbcProvider jdbcProvider = new JdbcProvider();
        try {
            boolean test = jdbcProvider.test(providerEntity);
            System.out.println(test);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
