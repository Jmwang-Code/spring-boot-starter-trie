package com.cn.jmw.loading.lo;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月23日 16:26
 * @Version 1.0
 */
public class LoadTireServiceImpl<T,R> implements LoadTireService{

    @Override
    public Object load(final Handler handler) {

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
