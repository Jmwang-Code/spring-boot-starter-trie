package com.cn.jmw.loading.lo;

/**
 * @author jmw
 * @Description
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public interface LoadTireService<T,R> {

    /**
     * 加载数据
     *
     * @param handler 数据回调处理器
     */
    T load(Handler<T,R> handler);
}
