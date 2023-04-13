package com.cn.jmw.config;

import com.cn.jmw.entity.ProviderEntity;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author jmw
 * @Description 线程池bean
 * @date 2023年04月11日 16:30
 * @Version 1.0
 */
@Component
@Slf4j
public class ThreadPoolConfig {

    @Autowired
    private ProviderEntity providerEntity;

    @DependsOn("providerEntity")
    @Bean
    @Qualifier(value = "configurationCheckThreadPool")
    public ExecutorService executorService() {
//        int nThreads = Runtime.getRuntime().availableProcessors();
        log.info("可用运行线程为" + providerEntity.getRunnableThreadNum());
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("ConfigurationCheck-pool-%d").build();

        ExecutorService configurationCheckThreadPool = new ThreadPoolExecutor(
                providerEntity.getRunnableThreadNum(),
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        return configurationCheckThreadPool;
    }
}
