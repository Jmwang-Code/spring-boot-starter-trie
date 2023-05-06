package com.cn.jmw.base;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jmw
 * @Description 计数器
 * @date 2023年04月28日 15:36
 * @Version 1.0
 */
public final class Counter {

    private final AtomicLong atomicInteger = new AtomicLong(0);

    public long inc() {
        return atomicInteger.incrementAndGet();
    }

    public long dec() {
        return atomicInteger.decrementAndGet();
    }

    public long value() {
        return atomicInteger.get();
    }

}
