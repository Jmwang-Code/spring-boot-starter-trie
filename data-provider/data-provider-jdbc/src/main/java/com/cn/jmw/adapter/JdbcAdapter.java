package com.cn.jmw.adapter;

import com.cn.jmw.base.JdbcDataSource;
import com.cn.jmw.base.JdbcSqlQueryRunner;
import com.cn.jmw.color.ThreadColor;
import com.cn.jmw.entity.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static cn.hutool.poi.excel.sax.ElementName.row;

/**
 * @author jmw
 * @Description 使用次类以及子类  可以通过try 自动关闭资源
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
@Slf4j
public class JdbcAdapter implements Adapter, Closeable {

    final String DUAL = "select 1 from dual";

    protected DataSource dataSource;

    protected Connection connection;

    public final void init(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            connection = JdbcDataSource.getConnection(dataSource.getDriverClassName(), dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        } catch (Exception e) {
            log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"初始化数据源失败"), e);
        }
    }

    //JDCB连接测试
    public boolean test() {//DataSource dataSource
        Connection connection = null;
        try {
            //加载驱动类
            Class.forName(dataSource.getDriverClassName());
            //返回连接对象
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
            log.info(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——测试连接成功"));
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            //如果发生异常，打印错误信息并返回null
            log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"驱动类没有找到或者连接失败:")+ dataSource.getDriverClassName(), e);
            return false;
        } finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"关闭连接失败:")+ dataSource.getDriverClassName(), e);
                }
            }
        }
    }

    @Override
    public boolean streamingRead() {
        QueryRunner jdbcSqlQueryRunner = new JdbcSqlQueryRunner();
        Connection connection = JdbcDataSource.getConnection(dataSource.getDriverClassName(), dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());

        dataSource.getSqlCode().parallelStream().forEach(sqlCode -> {
            try {
                jdbcSqlQueryRunner.query(connection, sqlCode.getSql(), resultSet -> {
                    while (resultSet.next()) {
                        //插入前缀树
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            log.info(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——"+resultSet.getString(i)));
                        }
                    }
                    return true;
                });
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"数据源流接入失败:")+ dataSource.getDriverClassName(), e);
            }
        });

        return true;
    }

    @Override
    public void close() {
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"关闭连接失败:")+ dataSource.getDriverClassName(), e);
            }
        }
    }
}
