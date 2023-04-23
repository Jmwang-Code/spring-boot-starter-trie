package com.cn.jmw.loading.lo;

import java.util.ArrayList;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public class LoadTireServiceImpl<T,R> implements LoadTireService{

    @Override
    public Object load(Handler handler) {
        return null;
    }

    public static void main(String[] args) {
        LoadTireService<String,Void> loadTireService = new LoadTireServiceImpl();
        loadTireService.load(new FunctionHandler<String, String>() {

            @Override
            public String handle(String s) {
                return null;
            }
        });

        loadTireService.load(new SupplierHandler<String>() {

            @Override
            public String handler() {
                return null;
            }
        });

        loadTireService.load(new ConsumerHandler<String>() {

            @Override
            public void handle(String o) {

            }
        });

        loadTireService.load(new PredicateHandler<String>() {


            @Override
            public boolean handle(String o) {
                return false;
            }
        });

    }



}
