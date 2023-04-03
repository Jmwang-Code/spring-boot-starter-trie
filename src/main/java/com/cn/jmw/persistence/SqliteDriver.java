package com.cn.jmw.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月01日 12:15
 * @Version 1.0
 */
public class SqliteDriver {

    //SQLite数据库本地连接
    public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    public static final String SQLITE_URL = "jdbc:sqlite:~/test.db";

    //SQLite数据库内存连接
    public static final String SQLITE_DRIVER_MEMORY = "org.sqlite.JDBC";
    public static final String SQLITE_URL_MEMORY = "jdbc:sqlite::memory:";

    //SQLite数据库远程连接
    public static final String SQLITE_DRIVER_REMOTE = "org.sqlite.JDBC";
    public static final String SQLITE_URL_REMOTE = "jdbc:sqlite://localhost/~/test.db";

    //SQLite生成存储文件的路径
    public static final String SQLITE_FILE_PATH = "D:\\sqlite\\test.db";

    //SQLite本地连接存储文件
    public static final String SQLITE_DRIVER_FILE = "org.sqlite.JDBC";
    public static final String SQLITE_URL_FILE = "jdbc:sqlite:" + SQLITE_FILE_PATH;



}
