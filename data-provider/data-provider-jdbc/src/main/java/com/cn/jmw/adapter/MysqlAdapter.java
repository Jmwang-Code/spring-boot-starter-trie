package com.cn.jmw.adapter;

import com.cn.jmw.entity.DataSource;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月10日 18:01
 * @Version 1.0
 */
public class MysqlAdapter extends JdbcAdapter{
//    @Override
//    public boolean test(DataSource dataSource) {
//        return super.test(dataSource);
//    }

    @Override
    public boolean streamingRead(DataSource dataSource) {
        return false;
    }
}
