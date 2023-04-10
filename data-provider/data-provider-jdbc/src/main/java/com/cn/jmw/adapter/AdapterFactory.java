package com.cn.jmw.adapter;

import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.provider.AbstractFactoryProvider;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月10日 18:07
 * @Version 1.0
 */
public class AdapterFactory {
    public static JdbcAdapter createDataAdapter(ProviderEntity providerEntity) {
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        return jdbcAdapter;
    }
}
