package com.cn.jmw.adapter;

import com.cn.jmw.base.JdbcSqlQueryRunner;
import com.cn.jmw.color.ColorEnum;
import com.cn.jmw.color.ThreadColor;
import com.cn.jmw.entity.DataSource;
import com.cn.jmw.entity.ProviderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
@Slf4j
public class JdbcAdapter implements Adapter {

    final String DUAL = "select 1 from dual";

    //JDCB连接测试
    public boolean test(DataSource dataSource) {
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
    public boolean streamingRead(DataSource dataSource) {
        QueryRunner jdbcSqlQueryRunner = new JdbcSqlQueryRunner();
//        jdbcSqlQueryRunner.batch();
        return false;
    }

}
