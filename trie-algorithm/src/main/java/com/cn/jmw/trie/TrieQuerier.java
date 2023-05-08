package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.TrieQueryResult;
import com.cn.jmw.trie.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jmw
 * @Description TrieQuerier
 * @date 2023年01月25日 17:15
 * @Version 1.0
 */
public class TrieQuerier extends Querier {

    private TrieNode sourceRoot;


    /**
     * 已探测到的字符串位置
     */
    private int root;

    /**
     * 当前探测到的词，在查询字符串中的开始位置
     */
    private int offset;

    /**
     * 下一个探测的字符串位置
     */
    private int i;

    /**
     * 根节点是否回退
     */
    private boolean isRootReset;

    /**
     * 标记是否可能需要回退
     */
    private boolean isBack;

    /**
     * 临时的位置用于回退的场合
     */
    private int iTemp;

    /**
     * 临时的节点用于回退的场合
     */
    private TrieNode nodeTemp;

    /**
     * 是否获取所有结果
     */
    private boolean enableTrieAllSearch;

    public TrieQuerier(TrieNode trieRootNode, Tokenizer content, boolean enableTrieAllSearch) {
        super.content = content;
        super.trieRootNode = trieRootNode;
        this.sourceRoot = trieRootNode;
        this.enableTrieAllSearch = enableTrieAllSearch;
    }

    /**
     * 查询操作
     */
    public TrieQueryResult query() {
        return next();
    }

    private TrieQueryResult next() {
        //当前查询字符长度
        int length = this.content.length();
        //根节点探测
        TrieNode trieNode = this.trieRootNode;
        //返回结果
        TrieQueryResult trieQueryResult = null;

        for (; this.i < length + 1; this.i++) {
            /**
             * 1.如果到达终点，则将当前节点置空。
             * 2.如果未到达终点，则将将当前子树的引用指向
             */
            if (i == length) {
                //如果探测位置以及等于字符长度，说明到达终点
                trieNode = null;
            } else {
                //获取当前位置字符
                int c = this.content.charAt(this.i);
                //当前前缀子树返回
                trieNode = trieNode.getBranch(c);
            }
            /**
             * 1.当前节点等于null,说明已经到底，可以考虑回退的问题了。
             * 2.如果当前节点不为null，说明可以继续探查。
             */
            if (trieNode == null) {
                //根节点是否重置
                if (this.isRootReset) {
                    this.trieRootNode = this.sourceRoot;
                    this.isRootReset = false;
                }
                // 找不到，则从头开始探测
                trieNode = this.trieRootNode;

                //是否回退
                if (this.isBack) {
                    this.offset = this.root;
                    String str = new String(this.content.toIntArray(),
                            this.root, this.iTemp - this.root + 1);

                    //如果字符长度大于0
                    if (str.length() > 0) {
                        if (enableTrieAllSearch) {
                            this.i = this.root + 1;
                        } else {
                            //原有会从匹配的尾部的下一个位置开始扫描
                            this.i = this.iTemp + 1;
                        }
                        this.root = this.i;
                        trieQueryResult = new TrieQueryResult(str, this.offset,
                                nodeTemp.getCodes());
                    }
                    //不需要回退
                    this.isBack = false;
                    this.nodeTemp = null;
                    return trieQueryResult;
                }
                this.i = this.root;
                // 向前移动已探测到的字符串位置
                this.root += 1;
            } else {
                switch (trieNode.getStatus()) {
                    //是词段，但是还可以继续探查，如果没有属性则可以回退。
                    case 2: {
                        this.iTemp = this.i;
                        this.nodeTemp = trieNode;
                        //如果允许全查询
                        if (enableTrieAllSearch) {
                            //返回
                            String str1 = new String(this.content.toIntArray(),
                                    this.root, this.iTemp - this.root + 1);
                            if (str1.length() > 0) {
                                trieQueryResult = new TrieQueryResult(str1, this.root,
                                        nodeTemp.getCodes());
                                this.i += 1;
                                this.trieRootNode = trieNode;
                                this.isRootReset = true;
                                return trieQueryResult;
                            }
                        } else {
                            this.isBack = true;
                        }
                        break;
                    }
                    case 3:
                        this.offset = this.root;
                        String str = new String(this.content.toIntArray(),
                                this.root, this.i - this.root + 1);
                        this.isBack = false;
                        if (str.length() > 0) {
                            if (enableTrieAllSearch) {
                                this.i = this.offset + 1; // 移动
                                this.isRootReset = false;
                            } else {
                                this.i = this.i + 1; // 移动已探测到的字符串位置
                            }
                            this.root = this.i;
                            trieQueryResult = new TrieQueryResult(str, this.offset,
                                    trieNode.getCodes());
                            this.trieRootNode = this.sourceRoot;
                        }
                        return trieQueryResult;
                }
            }
        }
        return null;
    }

    /**
     * @Param []
     * @return com.cn.jmw.trie.entity.TrieQueryResult
     * @Date 2023/5/8 11:19
     * 查询allCode
     */
    public List<TrieQueryResult> queryAll() {
        //当前查询字符长度
        int length = this.content.length();
        //根节点探测
        TrieNode trieNode = this.trieRootNode;
        //返回结果
        List<TrieQueryResult> trieQueryResults = new ArrayList<>();
        TrieQueryResult trieQueryResult = null;

        for (; this.i < length + 1; this.i++) {
            /**
             * 1.如果到达终点，则将当前节点置空。
             * 2.如果未到达终点，则将将当前子树的引用指向
             */
            if (i == length) {
                //如果探测位置以及等于字符长度，说明到达终点
                trieNode = null;
            } else {
                //获取当前位置字符
                int c = this.content.charAt(this.i);
                //当前前缀子树返回
                trieNode = trieNode.getBranch(c);
            }
            /**
             * 1.当前节点等于null,说明已经到底，可以考虑回退的问题了。
             * 2.如果当前节点不为null，说明可以继续探查。
             */
            if (trieNode == null) {
                //根节点是否重置
                if (this.isRootReset) {
                    this.trieRootNode = this.sourceRoot;
                    this.isRootReset = false;
                }
                // 找不到，则从头开始探测
                trieNode = this.trieRootNode;

                //是否回退
                if (this.isBack) {
                    this.offset = this.root;
                    String str = new String(this.content.toIntArray(),
                            this.root, this.iTemp - this.root + 1);

                    //如果字符长度大于0
                    if (str.length() > 0) {
                        if (enableTrieAllSearch) {
                            this.i = this.root + 1;
                        } else {
                            //原有会从匹配的尾部的下一个位置开始扫描
                            this.i = this.iTemp + 1;
                        }
                        this.root = this.i;
                        trieQueryResult = new TrieQueryResult(str, this.offset,
                                nodeTemp.getCodes());
                    }
                    //不需要回退
                    this.isBack = false;
                    this.nodeTemp = null;
                    trieQueryResults.add(trieQueryResult);
                }
                this.i = this.root;
                // 向前移动已探测到的字符串位置
                this.root += 1;
            } else {
                switch (trieNode.getStatus()) {
                    //是词段，但是还可以继续探查，如果没有属性则可以回退。
                    case 2: {
                        this.iTemp = this.i;
                        this.nodeTemp = trieNode;
                        //如果允许全查询
                        if (enableTrieAllSearch) {
                            //返回
                            String str1 = new String(this.content.toIntArray(),
                                    this.root, this.iTemp - this.root + 1);
                            if (str1.length() > 0) {
                                trieQueryResult = new TrieQueryResult(str1, this.root,
                                        nodeTemp.getCodes());
                                this.i += 1;
                                this.trieRootNode = trieNode;
                                this.isRootReset = true;
                                trieQueryResults.add(trieQueryResult);
                            }
                        } else {
                            this.isBack = true;
                        }
                        break;
                    }
                    case 3:
                        this.offset = this.root;
                        String str = new String(this.content.toIntArray(),
                                this.root, this.i - this.root + 1);
                        this.isBack = false;
                        if (str.length() > 0) {
                            if (enableTrieAllSearch) {
                                this.i = this.offset + 1; // 移动
                                this.isRootReset = false;
                            } else {
                                this.i = this.i + 1; // 移动已探测到的字符串位置
                            }
                            this.root = this.i;
                            trieQueryResult = new TrieQueryResult(str, this.offset,
                                    trieNode.getCodes());
                            this.trieRootNode = this.sourceRoot;
                        }
                        trieQueryResults.add(trieQueryResult);
                }
            }
        }
        return trieQueryResults;
    }

    private int deep = -1;

    /**
     * @Param []
     * @return int
     * @exception
     * @Date 2023/2/27 17:08
     * -1 ： 没有层数
     * 0 ： 只有根节点
     */
    public int getDeep() {
        if (trieRootNode.hasNext()) {
           dfs(trieRootNode,0);
        }
        return deep;
    }

    public void dfs(TrieNode nextTireNodes,int deep) {
        if (!nextTireNodes.hasNext()) {
            this.deep = Math.max(deep,this.deep);
            return;
        }
        TrieNode[] trieNodes = nextTireNodes.branches;
        for (int j = 0; j < trieNodes.length; j++) {
            TrieNode x = trieNodes[j];
            dfs(x,deep+1);
        }

    }

}
