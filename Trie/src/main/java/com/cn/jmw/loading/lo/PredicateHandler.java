package com.cn.jmw.loading.lo;

import com.cn.jmw.provider.AbstractFactoryProvider;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 17:05
 * @Version 1.0
 */
public interface PredicateHandler<T extends AbstractFactoryProvider> extends Handler{

    boolean handle(T t);

}
