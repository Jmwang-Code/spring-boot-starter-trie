package com.cn.jmw.base;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月05日 18:13
 * @Version 1.0
 */
public class JdbcConnection {

    private Connection conn;

    public JdbcConnection(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);

    }

}
