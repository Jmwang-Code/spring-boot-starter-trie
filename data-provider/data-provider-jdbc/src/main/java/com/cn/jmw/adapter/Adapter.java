package com.cn.jmw.adapter;


import java.io.Closeable;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月11日 16:20
 * @Version 1.0
 */
public interface Adapter<Result> extends Closeable {

    public boolean test();

    public Result streamingRead();
}
