package com.cn.jmw.persistence;

import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.pojo.NodeTable;
import com.cn.jmw.utils.SqliteCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月21日 17:45
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class SqliteTest {

    @Autowired
    private ProviderEntity providerEntity;

    @Test
    public void sqlite() throws SQLException {
        File file = new File(System.getProperty("user.dir").replace("\\","/")+ "/local/sqlite/sqlite.cache");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        SqliteCacheUtils sqliteCacheUtils = new SqliteCacheUtils(System.getProperty("user.dir").replace("\\","/")+ "/local/sqlite/sqlite.cache");
        boolean test1 = sqliteCacheUtils.insert( "test", 1, 1);
        List<NodeTable> test = sqliteCacheUtils.query("test", 1, 1);
        boolean test2 = sqliteCacheUtils.delete("test", 1, 1);
        List<NodeTable> test3 = sqliteCacheUtils.query("test", 1, 1);
        sqliteCacheUtils.close();
    }
}
