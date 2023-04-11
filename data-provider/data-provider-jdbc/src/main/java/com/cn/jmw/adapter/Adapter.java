package com.cn.jmw.adapter;

import com.cn.jmw.entity.DataSource;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月11日 16:20
 * @Version 1.0
 */
public interface Adapter {

    public boolean test(DataSource dataSource);

    public boolean streamingRead(DataSource dataSource);
}
