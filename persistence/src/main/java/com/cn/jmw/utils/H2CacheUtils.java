package com.cn.jmw.utils;

import com.cn.jmw.pojo.NodeTable;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月27日 11:21
 * @Version 1.0
 */
public class H2CacheUtils implements AutoCloseable{

    protected static final String H2_DRIVER_FILE = "org.h2.Driver";

    protected static final String H2_URL_FILE = "jdbc:h2:file:";

    protected static final String DATA_TABLE =
            "        CREATE TABLE IF NOT EXISTS DATA_H2 (" +
            "                ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "                NAME VARCHAR NOT NULL," +
            "                CODE INTEGER NOT NULL," +
            "                TYPE INTEGER NOT NULL," +
            "                DELETED INT NOT NULL DEFAULT 0" +
            "        );";


    protected static final String CHECK_EXISTS_SQL =
             "SELECT EXISTS(SELECT table_name from information_schema.tables where table_name = 'DATA_H2')";

    protected static final String QUERY_SQL =
            "    SELECT ID, NAME, CODE, TYPE FROM DATA_H2 WHERE DELETED = 0";

    protected static final String INSERT_SQL =
            "        INSERT INTO DATA_H2 (NAME, CODE, TYPE) VALUES ( ?, ?, ?)";

    protected static final String UPDATE_SQL =
            "        UPDATE DATA_H2 SET NAME = ?, CODE = ?, TYPE = ? WHERE ID = ?";

    protected static final String DELETE_SQL =
            "        UPDATE DATA_H2 SET DELETED = 1 WHERE NAME = ? AND CODE = ? AND TYPE = ?";

    public static boolean dbExist;

    protected Connection connection;

    protected QueryRunner queryRunner;

    public H2CacheUtils(String path){
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
            Class.forName(H2_DRIVER_FILE);
            conn = DriverManager.getConnection(H2_URL_FILE + sqliteFilePath);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public boolean tableExist(){
        boolean query;
        try {
            query = queryRunner.query(connection, CHECK_EXISTS_SQL, new ScalarHandler<Boolean>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbExist = query;
        return query;
    }

    public void createTable(){
        try {
            queryRunner.update(connection, DATA_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(String name, int code, int type){
        int update;
        try {
            update = queryRunner.update(connection, INSERT_SQL, name, code, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    public boolean delete(String name, int code, int type){
        int update;
        try {
            update = queryRunner.update(connection,DELETE_SQL , name, code,type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update==0?false:true;
    }

    public List<NodeTable> query(String name, int code, int type){
        List<NodeTable> query = null;

        try {
            query = queryRunner.query(connection, QUERY_SQL ,new BeanListHandler<NodeTable>(NodeTable.class));
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
