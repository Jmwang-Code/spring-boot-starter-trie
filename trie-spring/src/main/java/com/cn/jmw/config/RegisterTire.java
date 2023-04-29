package com.cn.jmw.config;

import com.cn.jmw.trie.Tire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jmw
 * @Description RegisterTire 注册前缀树，空节点，无数据
 * @date 2023年04月21日 14:35
 * @Version 1.0
 */
@Configuration
public class RegisterTire {

    @Bean
    public Tire tire() {
        return new Tire();
    }
}
