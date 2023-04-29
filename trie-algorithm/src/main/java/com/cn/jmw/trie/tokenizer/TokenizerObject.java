package com.cn.jmw.trie.tokenizer;

import java.util.Arrays;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年01月20日 16:26
 * @Version 1.0
 */
public class TokenizerObject implements Tokenizer{

    private final int[] characters;

    public TokenizerObject(String str){
        if (str == null) {
            this.characters = new int[0];
        } else {
            this.characters = TokenizerUtil.codePoints(str);
        }
    }

    @Override
    public int charAt(int index) {
        return characters[index];
    }

    @Override
    public int length() {
        return characters.length;
    }

    @Override
    public int[] toIntArray() {
        return Arrays.copyOf(characters, characters.length);
    }

    @Override
    public int toCharLocal() {
        return 0;
    }

    @Override
    public String toString() {
        return TokenizerUtil.toString(characters);
    }
}
