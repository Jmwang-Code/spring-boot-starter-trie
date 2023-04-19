package com.cn.jmw.config;

import com.cn.jmw.color.ColorEnum8;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author jmw
 * @Description 线程池bean
 * @date 2023年04月11日 16:30
 * @Version 1.0
 */
@Slf4j
public class ThreadPoolConfig {

    private static ExecutorService configurationCheckThreadPool;

    public static synchronized ExecutorService newInstance(final int runnableThreadNum) {
        if (configurationCheckThreadPool==null) {
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("ConfigurationCheck-pool-%d").build();
            log.info(ColorEnum8.BLUE.getColoredString(Thread.currentThread().getName()+"——可用运行线程为" + runnableThreadNum));
            configurationCheckThreadPool = new ThreadPoolExecutor(
                    runnableThreadNum,
                    10,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024),
                    namedThreadFactory,
                    new ThreadPoolExecutor.AbortPolicy());
        }
        return configurationCheckThreadPool;
    }

}
