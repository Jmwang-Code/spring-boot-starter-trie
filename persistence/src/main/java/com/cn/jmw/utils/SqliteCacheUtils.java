package com.cn.jmw.utils;

import com.cn.jmw.base.JdbcSqlQueryRunner;
import com.cn.jmw.entity.ProviderEntity;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月21日 17:04
 * @Version 1.0
 */
public class SqliteCacheUtils {

    //SQLite本地连接存储文件
    protected static final String SQLITE_DRIVER_FILE = "org.sqlite.JDBC";
    protected static final String SQLITE_URL_FILE = "jdbc:sqlite:";

    protected static final String DATA_TABLE = "<![CDATA[\n" +
            "        CREATE TABLE IF NOT EXISTS DATA (\n" +
            "                ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "                NAME VARCHAR(200) NOT NULL,\n" +
            "                CODE INTEGER NOT NULL,\n" +
            "                DELETED INT NOT NULL DEFAULT 0\n" +
            "        );\n" +
            "        ]]>";

    protected static final String CHECK_EXISTS_SQL = "<![CDATA[\n" +
            "        SELECT EXISTS(SELECT name from sqlite_master where type = 'table' and name = 'DATA')\n" +
            "        ]]>";

    protected static final String QUERY_SQL ="<![CDATA[\n" +
            "    SELECT ID, NAME, CODE FROM DATA WHERE DELETED = 0\n" +
            "        ]]>";

    protected static final String INSERT_SQL = "<![CDATA[\n" +
            "        INSERT INTO DATA (NAME, CODE) VALUES (?, ?)\n" +
            "        ]]>";

    public static boolean dbExist;

    protected boolean closed;

    public static Connection getConnection(String sqliteFilePath) {
        Connection conn;
        try {
            Class.forName(SQLITE_DRIVER_FILE);
            conn = DriverManager.getConnection(SQLITE_URL_FILE + sqliteFilePath);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static boolean tableExist(Connection connection){
        JdbcSqlQueryRunner jdbcSqlQueryRunner = new JdbcSqlQueryRunner();
        int update;
        try {
            update = jdbcSqlQueryRunner.update(connection, CHECK_EXISTS_SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbExist = update==0?false:true;
        return dbExist;
    }

    public static boolean createTable(Connection connection){
        JdbcSqlQueryRunner jdbcSqlQueryRunner = new JdbcSqlQueryRunner();
        int update;
        try {
            update = jdbcSqlQueryRunner.update(connection, DATA_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update==0?false:true;
    }

}
