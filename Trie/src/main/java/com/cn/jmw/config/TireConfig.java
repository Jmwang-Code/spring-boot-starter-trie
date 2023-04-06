package com.cn.jmw.config;

import com.cn.jmw.trie.Tire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月20日 15:53
 * @Version 1.0
 */
@Configuration
public class TireConfig {

    @Bean
    CacheTire tire(){
        return new CacheTire();
    }

    @DependsOn("cacheTire")
    @Bean
    Tire getTire(){
        return new Tire<>();
    }
}
