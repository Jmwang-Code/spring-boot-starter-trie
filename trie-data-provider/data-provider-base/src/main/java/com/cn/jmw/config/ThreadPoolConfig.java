package com.cn.jmw.config;

import com.cn.jmw.color.ColorEnum8;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * @author jmw
 * @Description 线程池bean
 * @date 2023年04月11日 16:30
 * @Version 1.0
 */
@Slf4j
public class ThreadPoolConfig<T> implements AutoCloseable{

    private ExecutorService configurationCheckThreadPool;

    private ExecutorCompletionService executorCompletionService;

    public ExecutorService getConfigurationCheckThreadPool() {
        return configurationCheckThreadPool;
    }

    public ExecutorCompletionService<T> getExecutorCompletionService() {
        return executorCompletionService;
    }

    private int runnableThreadNum;

    private String threadName;

    public ThreadPoolConfig(int runnableThreadNum){
        this.runnableThreadNum = runnableThreadNum;
        configurationCheckThreadPool = newInstance(runnableThreadNum);
    }

    public ThreadPoolConfig(String threadName){
        this.threadName = threadName;
        executorCompletionService = newCachedThreadPool(threadName);
    }

    private synchronized ExecutorService newInstance(final int runnableThreadNum) {
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

    private synchronized <T> ExecutorCompletionService<T> newCachedThreadPool(String threadName) {
        configurationCheckThreadPool = Executors.newCachedThreadPool(new CustomizableThreadFactory(threadName));
        //ExecutorCompletionService是一个优秀的异步执行阻塞等待所有返回结果的队列
        final ExecutorCompletionService<T> completionService = new ExecutorCompletionService<>(
                configurationCheckThreadPool);
        return completionService;
    }

    @Override
    public void close() throws Exception {
        if (!configurationCheckThreadPool.isShutdown() || configurationCheckThreadPool!=null){
            configurationCheckThreadPool.shutdown();
        }
        if (configurationCheckThreadPool!=null && !configurationCheckThreadPool.isShutdown()){
            configurationCheckThreadPool.shutdownNow();
        }
    }
}
