package com.cn.jmw.trie.tokenizer;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年01月20日 16:41
 * @Version 1.0
 */
public class TokenizerUtil {

    public static final int EXT_CHAR_BASE = 0x100000;

    public static int[] codePoints(String string) {
        if (string == null) {
            return null;
        }
        if (string.length() == 0) {
            return new int[0];
        }
        //代码基数器
        int count = string.codePointCount(0, string.length());
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            //codePointAt是Java的一个指令，可以返回指定索引处的字符（Unicode代码点）。
            result[i] = string.codePointAt(i);
        }
        return result;
    }

    public static String toString(int c) {
        return new String(new int[] { c }, 0, 1);
    }

    public static String toString(int[] cs) {
        StringBuilder buf = new StringBuilder();
        for (int c : cs) {
            if (c >= TokenizerUtil.EXT_CHAR_BASE) {
                buf.append("*");
            } else {
                if(c!=-1){
                    buf.append(toString(c));
                }
            }
        }
        return buf.toString();
    }
}
