package com.cn.jmw.utils;

import com.cn.jmw.base.JdbcSqlQueryRunner;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.pojo.NodeTable;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.sql.*;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月21日 17:04
 * @Version 1.0
 */
public class SqliteCacheUtils implements AutoCloseable{

    //SQLite本地连接存储文件
    protected static final String SQLITE_DRIVER_FILE = "org.sqlite.JDBC";
    protected static final String SQLITE_URL_FILE = "jdbc:sqlite:";

    protected static final String DATA_TABLE =
            "        CREATE TABLE IF NOT EXISTS DATA_SQLITE (\n" +
            "                ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "                NAME VARCHAR(200) NOT NULL,\n" +
            "                CODE INTEGER NOT NULL,\n" +
            "                TYPE INTEGER NOT NULL,\n" +
            "                DELETED INT NOT NULL DEFAULT 0\n" +
            "        );";

    protected static final String CHECK_EXISTS_SQL =
            "SELECT EXISTS(SELECT name from sqlite_master where type = 'table' and name = 'DATA_SQLITE')";

    protected static final String QUERY_SQL =
            "    SELECT NAME, CODE, TYPE FROM DATA_SQLITE WHERE DELETED = 0";

    protected static final String INSERT_SQL =
            "        INSERT INTO DATA_SQLITE ( NAME, CODE, TYPE) VALUES ( ?, ?, ?)";

    protected static final String UPDATE_SQL =
            "        UPDATE DATA_SQLITE SET NAME = ?, CODE = ?, TYPE = ? WHERE ID = ?";

    protected static final String DELETE_SQL =
            "        UPDATE DATA_SQLITE SET DELETED = 1 WHERE NAME = ? AND CODE = ? AND TYPE = ?";

    //表存在否
    public static boolean dbExist;

    //连接
    protected Connection connection;

    protected QueryRunner queryRunner;

    public SqliteCacheUtils(String path){
        init(path);
    }

    public void init(String path){
        File file = new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        connection = getConnection(path);
        queryRunner = new QueryRunner();
        tableExist();
        if(!dbExist){
            createTable();
        }
    }

    public Connection getConnection(String sqliteFilePath) {
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

    public boolean tableExist(){
        Integer query;
        try {
            query = queryRunner.query(connection, CHECK_EXISTS_SQL, new ScalarHandler<Integer>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbExist = query==1;
        return query==1;
    }

    public boolean createTable(){
        int update;
        try {
            update = queryRunner.update(connection, DATA_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update==0?false:true;
    }

    public boolean insert(String name, int code, int type){
        int update;
        try {
            update = queryRunner.update(connection, INSERT_SQL,name, code,type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update==0?false:true;
    }

    public boolean update(int id,String name, int code, int type){
        int update;
        try {
            update = queryRunner.update(connection, UPDATE_SQL, name, code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update==0?false:true;
    }

    public boolean delete(String name, int code, int type){
        int update;
        try {
            update = queryRunner.update(connection, DELETE_SQL, name, code,type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update==0?false:true;
    }

    public List<NodeTable> query(String name, int code, int type){
        List<NodeTable> query = null;
        try {
            query = queryRunner.query(connection, QUERY_SQL, new BeanListHandler<NodeTable>(NodeTable.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return query;
    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
