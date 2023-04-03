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

    //SQLite本地持久化连接代码
    public static void main(String[] args) throws Exception {
        Class.forName(SQLITE_DRIVER_FILE);
        Connection conn = DriverManager.getConnection(SQLITE_URL_FILE);
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

    //SQLite内存连接代码
    public static void main1(String[] args) throws Exception {
        Class.forName(SQLITE_DRIVER_MEMORY);
        Connection conn = DriverManager.getConnection(SQLITE_URL_MEMORY);
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

    //SQLite远程连接代码
    public static void main2(String[] args) throws Exception {
        Class.forName(SQLITE_DRIVER_REMOTE);
        Connection conn = DriverManager.getConnection(SQLITE_URL_REMOTE);
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

    //SQLite本地连接代码
    public static void main3(String[] args) throws Exception {
        Class.forName(SQLITE_DRIVER);
        Connection conn = DriverManager.getConnection(SQLITE_URL);
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
