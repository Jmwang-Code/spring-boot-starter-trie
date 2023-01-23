package com.cn.jmw.trie.entity;

/**
 * @author jmw
 * @Description 节点代码处理器
 * @date 2023年01月16日 20:15
 * @Version 1.0
 */
public enum MultiCodeMode {

    /**
     * 追加模式 (通过codetype的扩展，从而进行一词多码的支持)
     */
    Append,

    /**
     * 替换模式（后加的码替换先加的码）
     */
    Replace,

    /**
     * 丢弃模式（后加的码被丢弃）
     */
    Drop,

    /**
     * 抛异常
     */
    ThrowException
}
