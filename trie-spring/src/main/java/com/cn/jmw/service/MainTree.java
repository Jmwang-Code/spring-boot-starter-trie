package com.cn.jmw.service;

import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.loading.lo.LoadTireService;
import com.cn.jmw.loading.lo.LoadTireServiceImpl;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.TrieNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author jmw
 * @Description 加载模块，主树注入数据
 * @date 2023年04月28日 17:08
 * @Version 1.0 TODO
 */
@Component
@Slf4j
public class MainTree {

    @Autowired
    private ProviderEntity providerEntity;

    @Autowired
    private Tire tire;

    @Bean
    @DependsOn({"providerEntity","tire"})
    public void MainTree(){
        LoadTireService loadTireService = new LoadTireServiceImpl(providerEntity,tire);
        loadTireService.loadConsumer(abstractFactoryProvider -> {
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
        });
    }

}
