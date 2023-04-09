package com.cn.jmw;

import com.cn.jmw.entity.ProviderEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author jmwang-code
 */
@SpringBootApplication
//@EnableConfigurationProperties({ProviderEntity.class})
public class RegressionSearchTree {

    public static void main(String[] args) {
        SpringApplication.run(RegressionSearchTree.class, args);
    }

}
