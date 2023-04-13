package com.cn.jmw.color;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jmw
 * @Description
 * @date 2023年04月13日 16:58
 * @Version 1.0
 */
@Slf4j
public enum ColorEnum {

    RED(31,4),
    GREEN(32,4),
    YELLOW(33,4),
    BLUE(34,4),
    PURPLE(35,4),
    CYAN(36,4),
    GREY(37,4),
    BLACK(30,4),

    BACK_RED(41,3),
    BACK_GREEN(42,3),
    BACK_YELLOW(43,3),
    BACK_BLUE(44,3),
    BACK_PURPLE(45,3),
    BACK_CYAN(46,3),
    BACK_GREY(47,3);

    ColorEnum(){}

    private int color;
    private int fontType;

    ColorEnum(int color, int fontType) {
        this.color = color;
        this.fontType = fontType;
    }

    public int getColor() {
        return color;
    }

    public int getFontType() {
        return fontType;
    }

    public void getColoredLog(String content) {
        log.info(String.format("\033[%d;%dm%s\033[0m",this.color, this.fontType, content));
    }

    public String getColoredString(String content) {
        return String.format("\033[%d;%dm%s\033[0m",this.color, this.fontType, content);
    }
}
