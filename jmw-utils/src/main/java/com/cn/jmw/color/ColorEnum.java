package com.cn.jmw.color;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jmw
 * @Description
 * @date 2023年04月13日 16:58
 * @Version 1.0
 */
public enum ColorEnum {

    RED(31,4),
    GREEN(32,4),
    YELLOW(33,4),
    BLUE(34,4),
    PURPLE(35,4),
    CYAN(36,4),
    GREY(37,4),
    BLACK(30,4);

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

    public String getColoredString(String content) {
        return String.format("\033[%d;%dm%s\033[0m",this.color, this.fontType, content);
    }

    public static ColorEnum getRandomColor(){
        int i = (int) (Math.random() * 8);
        return ColorEnum.values()[i];
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 15; j++) {
                int number = i * 15 + j;
                String str = String.format("%3d", number);

                System.out.print("\u001b[48;5;" + number + "m " + str);
                System.out.print("\u001b[0m");
            }
            System.out.println();
        }
    }
}
