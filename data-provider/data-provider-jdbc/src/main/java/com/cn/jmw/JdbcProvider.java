package com.cn.jmw;


import com.cn.jmw.adapter.AdapterFactory;
import com.cn.jmw.config.ThreadPoolConfig;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.provider.AbstractFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * @author jmw
 * @Description 提供一个可超时自动关闭的计时器，超时时间通过 timeoutMillis() 方法设置。 通过refresh()方法可刷新超时时间 超时后Timer自动调用 close方法
 * @date 2023年04月06日 9:44
 * @Version 1.0
 */
public class JdbcProvider extends AbstractFactoryProvider {

    @Autowired
    @Qualifier(value = "configurationCheckThreadPool")
    private ExecutorService executorService;

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

        CopyOnWriteArrayList<FutureTask<Boolean>> integers = new CopyOnWriteArrayList<>();
        for (int i = 0; i < providerEntity.getDataSources().size(); i++) {
            int finalI = i;
            FutureTask<Boolean> integerFutureTask = new FutureTask<>(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return AdapterFactory.createDataAdapter(providerEntity.getDataSources().get(finalI)).test(providerEntity.getDataSources().get(finalI));
                }
            });
            executorService.submit(integerFutureTask);
            integers.add(integerFutureTask);
        }
        for (FutureTask<Boolean> integerFutureTask : integers) {
            if (!integerFutureTask.get()) {
                return false;
            }
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
