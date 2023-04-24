package com.cn.jmw.loading.lo;

import com.cn.jmw.JdbcProvider;
import com.cn.jmw.entity.DataSource;
import com.cn.jmw.entity.LoadOn;
import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.entity.SqlCode;
import com.cn.jmw.provider.AbstractFactoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public class LoadTireServiceImpl<T,R> implements LoadTireService{

    private ProviderEntity providerEntity;

    public LoadTireServiceImpl(ProviderEntity providerEntity){
        this.providerEntity = providerEntity;
    }

    @Override
    public Object load(final Consumer handler) {
        boolean execute;
        try (AbstractFactoryProvider abstractFactoryProvider = new JdbcProvider();) {
            execute = abstractFactoryProvider.execute(providerEntity);
        }catch (Exception e){

        }
        return null;
    }


}
