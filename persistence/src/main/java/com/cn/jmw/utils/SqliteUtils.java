package com.cn.jmw.utils;

import com.cn.jmw.entity.ProviderEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月21日 17:04
 * @Version 1.0
 */
public class SqliteUtils {

    //SQLite本地连接存储文件
    protected static final String SQLITE_DRIVER_FILE = "org.sqlite.JDBC";
    protected static final String SQLITE_URL_FILE = "jdbc:sqlite:";

    public static Connection getConnection(String sqliteFilePath) {
        Connection conn;
        try {
            Class.forName(SQLITE_DRIVER_FILE);
            conn = DriverManager.getConnection(SQLITE_URL_FILE + sqliteFilePath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
