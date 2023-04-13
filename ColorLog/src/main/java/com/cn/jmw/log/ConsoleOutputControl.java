package com.cn.jmw.log;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月13日 15:27
 * @Version 1.0
 */
@Slf4j
public class ConsoleOutputControl {


    private static final HashMap<Integer, String> colorMap = new HashMap<>() {
        {
            put(31, "红色字体");
            put(32, "绿色字体");
            put(33, "黄色字体");
            put(34, "蓝色字体");
            put(35, "紫色字体");
            put(36, "青色字体");
            put(37, "灰色字体");
            put(40, "黑色背景");
            put(41, "红色背景");
            put(42, "绿色背景");
            put(43, "黄色背景");
            put(44, "蓝色背景");
            put(45, "紫色背景");
            put(46, "青色背景");
            put(47, "灰色背景");
        }
    };

    public static String getColoredString(int color, int fontType, String content) {
        return String.format("\033[%d;%dm%s\033[0m", color, fontType, content);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            log.info(getColoredString(31 + i, 4, "颜色控制 -> " + colorMap.get(31 + i)));
        }
        for (int i = 0; i < 8; i++) {
            log.info(getColoredString(40 + i, 3, "背景控制 -> " + colorMap.get(40 + i)));
        }
        System.out.println(String.format("\033[%d;%d;%dm%s\033[0m", 1, 97, 40, "文 字 背 景 "));
    }
}
