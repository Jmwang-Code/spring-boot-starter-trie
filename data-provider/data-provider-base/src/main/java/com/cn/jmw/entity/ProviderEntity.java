package com.cn.jmw.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月06日 10:02
 * @Version 1.0
 */
@Data
public class ProviderEntity {

    private String source;
    private List<DataSource> password;

    private class DataSource {
        private String type;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }
}
