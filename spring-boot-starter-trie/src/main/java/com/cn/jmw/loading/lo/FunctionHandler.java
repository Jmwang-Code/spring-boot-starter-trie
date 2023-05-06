package com.cn.jmw.loading.lo;

import com.cn.jmw.provider.AbstractFactoryProvider;

@FunctionalInterface
/**
 * @author jmw
 * @Description
 * @date 2023年04月23日 16:56
 * @Version 1.0
 */
public interface FunctionHandler<T extends AbstractFactoryProvider,R extends Object> extends Handler{

    R handle(T t);

}
