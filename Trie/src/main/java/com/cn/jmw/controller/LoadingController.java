package com.cn.jmw.controller;

import com.cn.jmw.JdbcProvider;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.provider.AbstractFactoryProvider;
import com.cn.jmw.trie.Tire;
import com.cn.jmw.trie.TrieNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月13日 18:46
 * @Version 1.0
 */
@Tag(name = "加载")
@RestController
public class LoadingController {
    @Autowired
    private ProviderEntity providerEntity;


    @Operation(summary = "loading")
    @GetMapping("/loading")
    public Boolean getJdbcProvider(){
        AbstractFactoryProvider jdbcProvider = new JdbcProvider(new Tire());
        boolean test;
        try {
            test = jdbcProvider.test(providerEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return test;
    }
}
