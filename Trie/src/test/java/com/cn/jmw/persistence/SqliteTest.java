package com.cn.jmw.persistence;

import com.cn.jmw.adapter.AdapterFactory;
import com.cn.jmw.entity.ProviderEntity;
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
    public void jdbcAdapter() throws SQLException {
        File file = new File(providerEntity.getPersistencePath());
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        Connection conn = SqliteCacheUtils.getConnection(providerEntity.getPersistencePath());
        Statement stmt = conn.createStatement();
        stmt.execute("create table test(id int primary key, name varchar(255))");
        stmt.execute("insert into test values(1, 'Hello')");
        stmt.execute("insert into test values(2, 'World')");
        ResultSet rs = stmt.executeQuery("select * from test");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
