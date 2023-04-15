package com.cn.jmw.adapter;

import com.cn.jmw.color.ColorEnum;
import com.cn.jmw.color.ThreadColor;
import com.cn.jmw.entity.DataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
@Slf4j
public class MysqlAdapter extends JdbcAdapter {
    @Override
    public boolean test(DataSource dataSource) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册驱动
            Class.forName(dataSource.getDriverClassName());

            conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());

            stmt = conn.createStatement();
            String sql;
            sql = DUAL;
            ResultSet rs = stmt.executeQuery(sql);
            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();
            log.info(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"——测试连接成功"));

        } catch (SQLException e) {
            // 处理JDBC错误
            e.printStackTrace();
            log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"驱动类没有找到或者连接失败:")+ dataSource.getDriverClassName(), e);
            return false;
        } catch (Exception e) {
            // 处理Class.forName错误
            e.printStackTrace();
            log.error(ThreadColor.getColor(Thread.currentThread().getName()).getColoredString(Thread.currentThread().getName()+"驱动类没有找到或者连接失败:")+ dataSource.getDriverClassName(), e);
            return false;
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean streamingRead(DataSource dataSource) {
        return false;
    }
}
