package com.cn.jmw;

import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.service.AutoCloseBean;

import java.io.Closeable;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月05日 18:07
 * @Version 1.0
 */
public abstract class AbstractProvider extends AutoCloseBean {

    /**
     * 执行
     *
     * @param providerEntity
     * @return
     */
    public abstract boolean execute(ProviderEntity providerEntity);

    @Override
    public int timeoutMillis() {
        return Integer.MAX_VALUE;
    }
}
