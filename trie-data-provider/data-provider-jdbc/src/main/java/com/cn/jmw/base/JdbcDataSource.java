package com.cn.jmw.base;

import com.cn.jmw.color.ThreadColor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Slf4j
/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月05日 18:13
 * @Version 1.0
 */
public class JdbcDataSource {

    //未关闭
    public static Connection getConnection(String driver, String url, String username, String password)  {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            log.info(ThreadColor.getColor256(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——获取连接"));
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
