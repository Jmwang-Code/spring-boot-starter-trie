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

    static final Map<String, ColorEnum8> map = new ConcurrentHashMap();

    public synchronized static ColorEnum8 getColor(String threadName){
        if (map.containsKey(threadName)){
            return map.get(threadName);
        }else {
            ColorEnum8 colorEnum8 = ColorEnum8.getRandomColor();
            map.put(threadName, colorEnum8);
            return colorEnum8;
        }
    }
}
