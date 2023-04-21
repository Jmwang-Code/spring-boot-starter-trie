package com.cn.jmw.adapter;

import com.cn.jmw.base.JdbcDataSource;
import com.cn.jmw.base.JdbcSqlQueryRunner;
import com.cn.jmw.color.ThreadColor;
import com.cn.jmw.entity.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;

import java.io.Closeable;
import java.sql.*;


/**
 * @author jmw
 * @Description 使用次类以及子类  可以通过try 自动关闭资源
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
@Slf4j
public class JdbcAdapter implements Adapter<Boolean> {

    protected final static String DUAL = "select 1 from dual";

    protected DataSource dataSource;

    protected Connection connection;

    protected QueryRunner queryRunner;


    public final void init(DataSource dataSource) {
        try {
            Class.forName(dataSource.getDriverClassName());
            this.dataSource = dataSource;
            this.queryRunner = new JdbcSqlQueryRunner();
            this.connection = JdbcDataSource.getConnection(dataSource.getDriverClassName(), dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        } catch (Exception e) {
            log.error(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"初始化数据源失败"), e);
        }
    }

    //JDCB连接测试
    @Override
    public boolean test() {
//        Statement stmt = null;
//        try {
//            stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(DUAL);
//            // 关闭资源
//            rs.close();
//            stmt.close();
//            connection.close();
//            log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——测试连接成功"));
//
//        } catch (SQLException e) {
//            // 处理JDBC错误
//            e.printStackTrace();
//            log.error(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"驱动类没有找到或者连接失败:")+ dataSource.getDriverClassName(), e);
//            return false;
//        } catch (Exception e) {
//            // 处理Class.forName错误
//            e.printStackTrace();
//            log.error(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"驱动类没有找到或者连接失败:")+ dataSource.getDriverClassName(), e);
//            return false;
//        } finally {
//            // 关闭资源
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (connection != null) connection.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
        return connection!=null?true:false;
    }

    /**
     * 流式读取 dataSource实体类中的多种数据
     */
    @Override
    public Boolean streamingRead() {
        dataSource.getSqlCode().parallelStream().forEach(sqlCode -> {
            try {
                queryRunner.query(connection, sqlCode.getSql(), resultSet -> {
                    while (resultSet.next()) {
                        //插入前缀树
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——"+resultSet.getString(i)));
                        }
                    }
                    return true;
                });
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"数据源流接入失败:")+ dataSource.getDriverClassName(), e);
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
                log.error(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"关闭连接失败:")+ dataSource.getDriverClassName(), e);
            }
        }
    }
}
