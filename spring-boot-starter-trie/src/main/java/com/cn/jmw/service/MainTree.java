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

    /**
     * 加载主树
     *
     * 依赖: 数据源提供者(providerEntity), 容器(tire)
     *
     * 创建一个加载树的服务（LoadTireService）
     */
    @Bean
    @DependsOn({"providerEntity","tire"})
    public void MainTree(){
        LoadTireService loadTireService = new LoadTireServiceImpl(providerEntity,tire);
        //有多种加载回调方式，此时只需要消费者某种提供者即可
        //被消费的提供者选择JDBCProvider，由于处理器T是AbstractFactoryProvider的子类，所以可以直接传入
        loadTireService.loadConsumer(abstractFactoryProvider -> {
            log.info("加载判断逻辑");
            //判断缓存层面是否存在数据
            //判断数据库层面是否存在数据
            //应该采取何种方式加载数据
            boolean execute = false;
            try {
                //调用了数据源提供者的execute方法
                execute = abstractFactoryProvider.execute(providerEntity);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
