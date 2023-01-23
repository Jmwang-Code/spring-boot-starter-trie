package com.cn.jmw.trie;

import com.cn.jmw.trie.tokenizer.TokenizerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年01月20日 17:05
 * @Version 1.0
 */
public class ToIn {

    /**
     * 将字符串转成unicode
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static String convert(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); //取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }

    /**
     * 字符串转换unicode
     */
    public static String convert2(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append(String.format("\\u%04x",Integer.valueOf(c)));
        }

        return unicode.toString();
    }

    /**
     * unicode转字符串
     * @param unicodeStr unicode
     * @return 字符串
     */
    public static String unicodeToString(String unicodeStr) {
        // XDigit是POSIX字符类，表示十六进制数字，\p{XDigit}等价于[a-fA-F0-9]
        // pattern用于匹配形如\\u6211的字符串
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicodeStr);
        char ch;
        while (matcher.find()) {
            // 捕获组按开括号'('从左到右编号（从1开始），以(A(B(C)))为例，group(1)表示(A(B(C))，group(2)表示(B(C))，group(3)表示(C)
            // group(2)表示第二个捕获组，即(\p{XDigit}{4})
            // Integer.parseInt(str, 16)把16进制的数字字符串转化为10进制，比如Integer.parseInt("16", 16) = 22
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            // 把第一个捕获组，即形如\\u6211这样的字符串替换成中文
            unicodeStr = unicodeStr.replace(matcher.group(1), ch + "");
        }
        return unicodeStr;
    }


    /**
     * 字符串转unicode
     * @param str 字符串
     * @return unicode
     */
    public static String stringToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            // Integer.toHexString把字符串转16进制
            sb.append("\\u" + Integer.toHexString(c[i]));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            list.add("南");
        }
        System.out.println(list.size());
        long l = System.currentTimeMillis();
        list.stream().forEach((a
        )->{
            convert(String.valueOf(a));
        });
        System.out.println(System.currentTimeMillis()-l);

        System.out.println(TokenizerUtil.toString(21335));

        long l2 = System.currentTimeMillis();
        list.stream().forEach((a
        )->{
            convert2(String.valueOf(a));
        });
        System.out.println(System.currentTimeMillis()-l2);

    }
}
