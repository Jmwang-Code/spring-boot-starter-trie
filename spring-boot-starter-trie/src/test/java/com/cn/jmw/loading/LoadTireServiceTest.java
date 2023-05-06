package com.cn.jmw.loading;

import com.cn.jmw.JdbcProvider;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.loading.lo.*;
import com.cn.jmw.provider.AbstractFactoryProvider;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.TrieNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 20:56
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class LoadTireServiceTest {

    @Autowired
    private ProviderEntity providerEntity;

    @Test
    public void test(){
        Tire trieNode = new Tire();
        LoadTireService loadTireService = new LoadTireServiceImpl(providerEntity,trieNode);
        loadTireService.loadConsumer(o -> {
            log.info("加载判断逻辑");
            //判断缓存层面是否存在数据
            //判断数据库层面是否存在数据

            //应该采取何种方式加载数据
            boolean execute = false;
            try {
                execute = o.execute(providerEntity);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 10; i++) {
            System.out.println("----------------------------------------------");
        }

        loadTireService.loadPredicate(abstractFactoryProvider -> {
            log.info("加载判断逻辑");
            //判断缓存层面是否存在数据
            //判断数据库层面是否存在数据

            //应该采取何种方式加载数据
            boolean execute = false;
            try {
                execute = abstractFactoryProvider.execute(providerEntity);
            }catch (Exception e){
                e.printStackTrace();
            }
            return execute;
        });
        for (int i = 0; i < 10; i++) {
            System.out.println("----------------------------------------------");
        }
        Object o = loadTireService.loadFunction(abstractFactoryProvider -> {
            log.info("加载判断逻辑");
            //判断缓存层面是否存在数据
            //判断数据库层面是否存在数据

            //应该采取何种方式加载数据
            boolean execute = false;
            try {
                execute = abstractFactoryProvider.execute(providerEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return execute;
        });
        for (int i = 0; i < 10; i++) {
            System.out.println("----------------------------------------------");
        }
        loadTireService.loadSupplier(() -> {
            log.info("加载判断逻辑");
            //判断缓存层面是否存在数据
            //判断数据库层面是否存在数据

            //应该采取何种方式加载数据
            boolean execute = false;
            try {
                execute = new JdbcProvider(new Tire()).execute(providerEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return execute;
        });
    }
}
