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

    static final Map<String,ColorEnum> map = new ConcurrentHashMap();

    public synchronized static ColorEnum getColor(String threadName){
        if (map.containsKey(threadName)){
            return map.get(threadName);
        }else {
            ColorEnum colorEnum = ColorEnum.getRandomColor();
            map.put(threadName,colorEnum);
            return colorEnum;
        }
    }
}
