package com.cn.jmw.color;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月15日 17:38
 * @Version 1.0
 */
public class ThreadColor {

    static final Map<String, ColorEnum8> map8 = new ConcurrentHashMap(8);

    static final Map<String, ColorEnum256> map256 = new ConcurrentHashMap(256);

    public synchronized static ColorEnum8 getColor8(String threadName){
        if (map8.containsKey(threadName)){
            return map8.get(threadName);
        }else {
            ColorEnum8 colorEnum8 = ColorEnum8.getRandomColor();
            map8.put(threadName, colorEnum8);
            return colorEnum8;
        }
    }

    public synchronized static ColorEnum256 getColor256(String threadName){
        if (map256.containsKey(threadName)){
            return map256.get(threadName);
        }else {
            ColorEnum256 colorEnum256 = ColorEnum256.getRandomColor256();
            map256.put(threadName, colorEnum256);
            return colorEnum256;
        }
    }
}
