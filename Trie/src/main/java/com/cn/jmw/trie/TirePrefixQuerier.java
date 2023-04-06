package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.tokenizer.Tokenizer;

import java.util.Map;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月13日 16:40
 * @Version 1.0
 */
public class TirePrefixQuerier extends Querier{

    public TirePrefixQuerier(TrieNode trieRootNode, Tokenizer prefix) {
        this.content = prefix;
        this.trieRootNode = trieRootNode;
    }

    public TriePrefixQueryResult queryAllPrefix(){
        //返回结果集
        TriePrefixQueryResult triePrefixQueryResult = new TriePrefixQueryResult();
        if (content==null || content.length()<=0){
            return triePrefixQueryResult;
        }

        Map<int[], TrieCode[]> map = triePrefixQueryResult.getMap();

        //当前查询字符长度
        Tokenizer prefix = content;
        int length = this.content.length();

        //根节点探测
        TrieNode trieNode = this.trieRootNode;

        //定位树
        TrieNode location = location(trieNode, prefix);

        //TODO 探针的长度 是bug需要修复，比如给主树维护一个最大深度属性，在递归遍历中顺手比较叶子节点的深度
        int[] arr ={location.getC()};
        //钻取集合
        Drilling(map,location,arr,1);

        return triePrefixQueryResult;
    }

    private TrieNode location(TrieNode trieNode,Tokenizer prefix){
        //定位树
        TrieNode trieNodeRe = trieNode;
        int length = prefix.length();
        for (int i = 0; i < prefix.length()+1; i++) {
            /**
             * 1.如果到达终点，则将当前节点置空。
             * 2.如果未到达终点，则将将当前子树的引用指向
             */
            if (i == length) {
                //如果探测位置以及等于字符长度，说明到达终点
                break;
            } else {
                //获取当前位置字符
                int c = prefix.charAt(i);
                //当前前缀子树返回
                trieNodeRe = trieNodeRe.getBranch(c);
            }
            /**
             * 1.当前节点等于null,说明已经到底，可以考虑回退的问题了。
             */
            if (trieNodeRe == null) {
                trieNodeRe = this.trieRootNode;
            }
        }
        return trieNodeRe;
    }

    private void Drilling(Map<int[], TrieCode[]> map,TrieNode trieNode,int[] a,int currentCount){
        int[] arr = a;
        TrieNode[] branches = trieNode.branches;
        if (branches==null || branches.length==0){
            return;
        }
        for (int i = 0; i < branches.length; i++) {
            TrieNode sub = branches[i];
            byte status = sub.getStatus();
            int c = sub.getC();
            TrieCode[] codes = sub.getCodes();
            if (status==3){
                int[] children = copyArray(currentCount, arr, c);
                map.put(children,codes);
            }else if (status==2){
                int[] children = copyArray(currentCount, arr, c);
                map.put(children,codes);
                int[] childrenRu = new int[currentCount+2];
                System.arraycopy(children,0,childrenRu,0,currentCount+1);
                Drilling(map,sub,childrenRu,currentCount+1);
            }else{
                int[] children = copyArray(currentCount, arr, c);
                Drilling(map,sub,children,currentCount+1);
            }
        }
    }

    private int[] copyArray(int currentCount,int[] arr,int c){
        //无需担心 currentCount+1 不定义的问题，由于int类型 -127-128 缓存在IntegerCache常量池
        //前缀树存储字段也有长度限制 考虑效率问题 不会超过128char长度
        int[] children = new int[currentCount+1];
        System.arraycopy(arr,0,children,0,currentCount+1>arr.length?arr.length:currentCount+1);
        children[currentCount] = c;
        return children;
    }

//    private int dfsDepth(){
//
//    }

}
