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
public class H2Driver {

    //H2数据库本地连接
    public static final String H2_DRIVER = "org.h2.Driver";
    public static final String H2_URL = "jdbc:h2:~/test";

    //H2数据库内存连接
    public static final String H2_DRIVER_MEMORY = "org.h2.Driver";
    public static final String H2_URL_MEMORY = "jdbc:h2:mem:test";

    //H2数据库远程连接
    public static final String H2_DRIVER_REMOTE = "org.h2.Driver";
    public static final String H2_URL_REMOTE = "jdbc:h2:tcp://localhost/~/test";

    //h2生成存储文件的路径
    public static final String H2_FILE_PATH = "D:\\h2\\test";

    //h2本地连接存储文件
    public static final String H2_DRIVER_FILE = "org.h2.Driver";
    public static final String H2_URL_FILE = "jdbc:h2:file:" + H2_FILE_PATH;
    //h2本地连接代码
    public static void main(String[] args) throws Exception {
        Class.forName(H2_DRIVER_FILE);
        Connection conn = DriverManager.getConnection(H2_URL_FILE, "sa", "");
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

    //h2内存连接代码
    public static void main1(String[] args) throws Exception {
        Class.forName(H2_DRIVER_MEMORY);
        Connection conn = DriverManager.getConnection(H2_URL_MEMORY, "sa", "");
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

    //h2远程连接代码
    public static void main2(String[] args) throws Exception {
        Class.forName(H2_DRIVER_REMOTE);
        Connection conn = DriverManager.getConnection(H2_URL_REMOTE, "sa", "");
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

    //h2本地连接代码
    public static void main3(String[] args) throws Exception {
        Class.forName(H2_DRIVER);
        Connection conn = DriverManager.getConnection(H2_URL, "sa", "");
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

    //h2 druid连接代码
    public static void main4(String[] args) throws Exception {
        Class.forName("com.alibaba.druid.pool.DruidDataSource");
        Connection conn = DriverManager.getConnection("jdbc:druid:h2:~/test", "sa", "");
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

//    //h2交给spring管理
//    public static void main5(String[] args) throws Exception {
//        Class.forName("org.springframework.jdbc.datasource.DriverManagerDataSource");
//        Connection conn = DriverManager.getConnection("jdbc:druid:h2:~/test", "sa", "");
//        Statement stmt = conn.createStatement();
//        stmt.execute("create table test(id int primary key, name varchar(255))");
//        stmt.execute("insert into test values(1, 'Hello')");
//        stmt.execute("insert into test values(2, 'World')");
//        ResultSet rs = stmt.executeQuery("select * from test");
//        while (rs.next()) {
//            System.out.println(rs.getInt(1) + " " + rs.getString(2));
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
//    }

}
