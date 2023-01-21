package com.cn.jmw.trie.tokenizer;

/**
 * @author jmw
 * @Description 分词
 * @date 2023年01月20日 16:25
 * @Version 1.0
 */
public interface Tokenizer {
    /**
     * @Param [index]
     * @return int
     * @exception
     * @Date 2023/1/20 16:37
     * 字符位置
     */
    int charAt(int index);

    /**
     * @Param []
     * @return int
     * @exception
     * @Date 2023/1/20 16:37
     * 长度
     */
    int length();

    /**
     * @Param []
     * @return int[]
     * @exception
     * @Date 2023/1/20 16:38
     * 转换成Int数组
     */
    int[] toIntArray();

    int toCharLocal();
}
