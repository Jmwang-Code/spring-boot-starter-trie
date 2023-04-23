package com.cn.jmw.loading.lo;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 17:05
 * @Version 1.0
 */
public interface PredicateHandler<T> extends Handler{

    abstract boolean handle(T t);

}
