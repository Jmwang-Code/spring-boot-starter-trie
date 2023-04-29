package com.cn.jmw.provider;

import com.cn.jmw.entity.ProviderEntity;
import com.cn.jmw.service.AutoCloseBean;


/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月05日 18:07
 * @Version 1.0
 */
public abstract class AbstractFactoryProvider extends AutoCloseBean {

    /**
     * 执行-Loading
     */
    public abstract boolean execute(ProviderEntity providerEntity) throws Exception;

    /**
     * 连接测试
     */
    public abstract boolean test(ProviderEntity providerEntity) throws Exception;

    /**
     * 自动关闭
     * AutoCloseBean
     */

    /**
     * 名字
     */
    public abstract String getConfigJsonFileName();

    /**
     * 超时时间
     */
    @Override
    public int timeoutMillis() {
        return Integer.MAX_VALUE;
    }
}
