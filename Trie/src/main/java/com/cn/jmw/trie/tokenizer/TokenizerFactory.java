package com.cn.jmw.trie.tokenizer;

import java.util.Arrays;

/**
 * @author jmw
 * @Description Tokenizer分词工具
 * @date 2023年01月17日 16:19
 * @Version 1.0
 */
public class TokenizerFactory {

    public static Tokenizer create(String str){
        return new TokenizerObject(str);
    }

    public static void main(String[] args) {
        int[] 南京 = TokenizerFactory.create("南京").toIntArray();
        System.out.println(Arrays.toString(南京));
    }
}
