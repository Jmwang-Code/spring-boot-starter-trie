package com.cn.jmw;


import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.provider.AbstractFactoryProvider;

import java.io.IOException;

/**
 * @author jmw
 * @Description 提供一个可超时自动关闭的计时器，超时时间通过 timeoutMillis() 方法设置。 通过refresh()方法可刷新超时时间 超时后Timer自动调用 close方法
 * @date 2023年04月06日 9:44
 * @Version 1.0
 */
public class JdbcProvider extends AbstractFactoryProvider {

    public final String CONFIG = "JDBC-CONFIG.json";

    @Override
    public boolean execute(ProviderEntity providerEntity) {

        return false;
    }

    @Override
    public boolean test(ProviderEntity providerEntity) throws Exception {
        return false;
    }

    @Override
    public String getConfigJsonFileName() {
        return CONFIG;
    }

    @Override
    public void close() throws IOException {
        System.out.println("close:"+this.getClass().getName());
    }

}
