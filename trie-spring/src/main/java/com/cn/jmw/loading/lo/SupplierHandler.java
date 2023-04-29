package com.cn.jmw.loading.lo;

@FunctionalInterface/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 17:07
 * @Version 1.0
 */
public interface SupplierHandler<R> extends Handler {

    R handle();

}
