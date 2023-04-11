package com.cn.jmw;


import com.cn.jmw.adapter.AdapterFactory;
import com.cn.jmw.config.ThreadPoolConfig;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.provider.AbstractFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

/**
 * @author jmw
 * @Description 提供一个可超时自动关闭的计时器，超时时间通过 timeoutMillis() 方法设置。 通过refresh()方法可刷新超时时间 超时后Timer自动调用 close方法
 * @date 2023年04月06日 9:44
 * @Version 1.0
 */
public class JdbcProvider extends AbstractFactoryProvider {

    @Autowired
    @Qualifier(value = "configurationCheckThreadPool")
    private ThreadPoolConfig threadPoolConfig;

    //配置名称
    public final String CONFIG = "JDBC";

    //执行
    @Override
    public boolean execute(ProviderEntity providerEntity) {

        return false;
    }

    //测试连接
    @Override
    public boolean test(ProviderEntity providerEntity) throws Exception {
        //使用线程池，循环创建异步线程，测试连接
        for (int i = 0; i < providerEntity.getDataSources().size(); i++) {
            return AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(i)).test(providerEntity.getDataSources().get(i));
        }
        return true;
    }

    @Override
    public String getConfigJsonFileName() {
        return CONFIG;
    }

    @Override
    public void close() throws IOException {
        System.out.println("close:"+getConfigJsonFileName());
    }

}
