package com.cn.jmw.loading.lo;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public class LoadTireServiceImpl<T,R> implements LoadTireService{

    @Override
    public Object load(final Handler handler) {
        ExecutorService executorService = Executors.newCachedThreadPool(new CustomizableThreadFactory("trie-cached-runner-"));
        //ExecutorCompletionService是一个优秀的异步执行阻塞等待所有返回结果的队列
        final ExecutorCompletionService<Long> completionService = new ExecutorCompletionService<Long>(
                executorService);

        long taskCount = 0;

        //本地加载

        //远程加载（第一次初始化项目）

        //混合加载

        return null;
    }

    public static void main(String[] args) {
        LoadTireService<String,String> loadTireService = new LoadTireServiceImpl();
        String load = loadTireService.load(new FunctionHandler<String, String>() {

            @Override
            public String handle(String s) {
                return null;
            }
        });

        String load1 = loadTireService.load(new SupplierHandler<String>() {

            @Override
            public String handler() {
                return null;
            }
        });

        String load2 = loadTireService.load(new ConsumerHandler<String>() {

            @Override
            public void handle(String o) {

            }
        });

        String load3 = loadTireService.load(new PredicateHandler<String>() {


            @Override
            public boolean handle(String o) {
                return false;
            }
        });

    }


}
