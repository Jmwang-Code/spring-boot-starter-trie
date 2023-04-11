package com.cn.jmw.adapter;

import com.cn.jmw.entity.DataSource;
import com.cn.jmw.entity.ProviderEntity;
import lombok.extern.slf4j.Slf4j;

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

    //JDCB连接测试
    public boolean test(DataSource dataSource) {
        Connection connection = null;
        try {
            //加载驱动类
            Class.forName(dataSource.getDriverClassName());
            //返回连接对象
            connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            //如果发生异常，打印错误信息并返回null
            log.error("Driver class not found or connection failed: " + dataSource.getDriverClassName(), e);
            return false;
        } finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("connection close failed: " + dataSource.getDriverClassName(), e);
                }
            }
        }
    }

    @Override
    public boolean streamingRead(DataSource dataSource) {
        return false;
    }

}
