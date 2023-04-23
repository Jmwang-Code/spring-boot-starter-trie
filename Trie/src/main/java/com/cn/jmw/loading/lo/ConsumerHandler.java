package com.cn.jmw.loading.lo;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 17:06
 * @Version 1.0
 */
public interface ConsumerHandler<T> extends Handler {

    abstract void handle(T t);
}
