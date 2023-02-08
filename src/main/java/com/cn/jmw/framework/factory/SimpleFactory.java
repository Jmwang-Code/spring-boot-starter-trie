package com.cn.jmw.framework.factory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月07日 21:01
 * @Version 1.0
 */
public class SimpleFactory {

    public Interface create(Class<? extends Interface> clazz){
        try {
            if (clazz!=null){
                return clazz.getConstructor().newInstance();
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
