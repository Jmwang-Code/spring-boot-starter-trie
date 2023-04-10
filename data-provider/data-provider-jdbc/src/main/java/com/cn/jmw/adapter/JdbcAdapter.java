package com.cn.jmw.adapter;

import com.cn.jmw.entity.DataSource;
import com.cn.jmw.entity.ProviderEntity;
import lombok.extern.slf4j.Slf4j;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
@Slf4j
public class JdbcAdapter {

    //JDCB连接测试
    public boolean test(ProviderEntity providerEntity) {
        List<DataSource> dataSources = providerEntity.getDataSources();
        //使用stream流过滤出有效的数据源
        dataSources.stream()
                //使用map操作将数据源转换为连接对象
                .map(dataSource -> {
                    try {
                        //加载驱动类
                        Class.forName(dataSource.getDriverClassName());
                        //返回连接对象
                        return DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
                    } catch (ClassNotFoundException | SQLException e) {
                        //如果发生异常，打印错误信息并返回null
                        log.error("Driver class not found or connection failed: " + dataSource.getDriverClassName(), e);
                        return false;
                    }
                });
        return true;
    }
}
