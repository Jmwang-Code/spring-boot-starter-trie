package com.cn.jmw.loading.lo;

/**
 * @author jmw
 * @Description
 * @date 2023年04月23日 16:56
 * @Version 1.0
 */
public interface FunctionHandler<T,R> extends Handler{

    R handle(T t);

}
