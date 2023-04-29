package com.cn.jmw.persistence;

import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.pojo.NodeTable;
import com.cn.jmw.utils.H2CacheUtils;
import com.cn.jmw.utils.SqliteCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月27日 14:27
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class H2Test {

    @Autowired
    private ProviderEntity providerEntity;

    @Test
    public void sqlite() throws SQLException {
        File file = new File(providerEntity.getPersistencePath().getPath());
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        H2CacheUtils h2CacheUtils = new H2CacheUtils(providerEntity.getPersistencePath().getPath());
        int test1 = h2CacheUtils.insert("test", 1, 1);
        List<NodeTable> test = h2CacheUtils.query("test", 1, 1);
        boolean test2 = h2CacheUtils.delete("test", 1, 1);
        List<NodeTable> test3 = h2CacheUtils.query("test", 1, 1);
        h2CacheUtils.close();
    }
}
