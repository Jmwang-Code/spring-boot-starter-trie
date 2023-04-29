//package com.cn.jmw.trie;
//
//import com.cn.jmw.trie.tokenizer.TokenizerUtil;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @author jmw
// * @Description TODO
// * @date 2023��01��20�� 17:05
// * @Version 1.0
// */
//public class ToIn {
//
//    /**
//     * ���ַ���ת��unicode
//     * @param str ��ת�ַ���
//     * @return unicode�ַ���
//     */
//    public static String convert(String str) {
//        str = (str == null ? "" : str);
//        String tmp;
//        StringBuffer sb = new StringBuffer(1000);
//        char c;
//        int i, j;
//        sb.setLength(0);
//        for (i = 0; i < str.length(); i++) {
//            c = str.charAt(i);
//            sb.append("\\u");
//            j = (c >>> 8); //ȡ����8λ
//            tmp = Integer.toHexString(j);
//            if (tmp.length() == 1)
//                sb.append("0");
//            sb.append(tmp);
//            j = (c & 0xFF); //ȡ����8λ
//            tmp = Integer.toHexString(j);
//            if (tmp.length() == 1)
//                sb.append("0");
//            sb.append(tmp);
//
//        }
//        return (new String(sb));
//    }
//
//    /**
//     * �ַ���ת��unicode
//     */
//    public static String convert2(String string) {
//
//        StringBuffer unicode = new StringBuffer();
//
//        for (int i = 0; i < string.length(); i++) {
//
//            // ȡ��ÿһ���ַ�
//            char c = string.charAt(i);
//
//            // ת��Ϊunicode
//            unicode.append(String.format("\\u%04x",Integer.valueOf(c)));
//        }
//
//        return unicode.toString();
//    }
//
//    /**
//     * unicodeת�ַ���
//     * @param unicodeStr unicode
//     * @return �ַ���
//     */
//    public static String unicodeToString(String unicodeStr) {
//        // XDigit��POSIX�ַ��࣬��ʾʮ���������֣�\p{XDigit}�ȼ���[a-fA-F0-9]
//        // pattern����ƥ������\\u6211���ַ���
//        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
//        Matcher matcher = pattern.matcher(unicodeStr);
//        char ch;
//        while (matcher.find()) {
//            // �����鰴������'('�����ұ�ţ���1��ʼ������(A(B(C)))Ϊ����group(1)��ʾ(A(B(C))��group(2)��ʾ(B(C))��group(3)��ʾ(C)
//            // group(2)��ʾ�ڶ��������飬��(\p{XDigit}{4})
//            // Integer.parseInt(str, 16)��16���Ƶ������ַ���ת��Ϊ10���ƣ�����Integer.parseInt("16", 16) = 22
//            ch = (char) Integer.parseInt(matcher.group(2), 16);
//            // �ѵ�һ�������飬������\\u6211�������ַ����滻������
//            unicodeStr = unicodeStr.replace(matcher.group(1), ch + "");
//        }
//        return unicodeStr;
//    }
//
//
//    /**
//     * �ַ���תunicode
//     * @param str �ַ���
//     * @return unicode
//     */
//    public static String stringToUnicode(String str) {
//        StringBuffer sb = new StringBuffer();
//        char[] c = str.toCharArray();
//        for (int i = 0; i < c.length; i++) {
//            // Integer.toHexString���ַ���ת16����
//            sb.append("\\u" + Integer.toHexString(c[i]));
//        }
//        return sb.toString();
//    }
//
//
//    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList();
//        for (int i = 0; i < 100000; i++) {
//            list.add("��");
//        }
//        System.out.println(list.size());
//        long l = System.currentTimeMillis();
//        list.stream().forEach((a
//        )->{
//            convert(String.valueOf(a));
//        });
//        System.out.println(System.currentTimeMillis()-l);
//
//        System.out.println(TokenizerUtil.toString(21335));
//
//        long l2 = System.currentTimeMillis();
//        list.stream().forEach((a
//        )->{
//            convert2(String.valueOf(a));
//        });
//        System.out.println(System.currentTimeMillis()-l2);
//
//    }
//}
