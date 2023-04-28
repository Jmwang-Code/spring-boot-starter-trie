package com.cn.jmw.loading.lo;

import com.cn.jmw.provider.AbstractFactoryProvider;

/**
 * @author jmw
 * @Description
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public interface LoadTireService<T extends AbstractFactoryProvider,R extends Object> {

    /**
     * 加载数据
     *
     * @param handler 数据回调处理器
     */
    void loadConsumer(final ConsumerHandler<T> handler);

    /**
     * 加载数据
     *
     * @param handler 数据回调处理器
     */
    <T extends AbstractFactoryProvider> R loadFunction(final FunctionHandler handler);

    /**
     * 加载数据
     *
     * @param handler 数据回调处理器
     */
    boolean loadPredicate(final PredicateHandler<T> handler);

    /**
     * 加载数据
     *
     * @param handler 数据回调处理器
     */
    <T,R> R loadSupplier(final SupplierHandler<R> handler);
}
