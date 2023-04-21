package com.cn.jmw.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月06日 10:02
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "provider-entity")
@Component
public class ProviderEntity {

    private String name;
    private String source;
    private List<DataSource> dataSources;
    private int runnableThreadNum = 4;
    private String persistencePath = System.getProperty("user.dir").replace("\\","/")+ "/local/sqlite/text.cache";

    @Data
    public static class SqlQenerator {

        //TODO SQL方言生成器，不准备填坑，方言太多，不想写，懒得写，还得学我想躺平
    }


}
