package com.cn.jmw.trie.tokenizer;

/**
 * @author jmw
 * @Description 开放对外接口
 * @date 2023年01月20日 16:50
 * @Version 1.0
 */
public class TokenizerManager {

    public static <T> int[] getIntArray(T t){
        return TokenizerFactory.create(t.toString()).toIntArray();
    }

    public static <T> int getIntLocal(T t){
        return t.toString().codePointAt(0);
    }

}
