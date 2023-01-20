package com.cn.jmw.trie;

import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.Result;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jmw
 * @Description 前缀树节点
 * @date 2022年12月05日 17:35
 * @Version 1.0
 * @see #add(int[], MultiCodeMode, int, int)
 * @see #remove(int[], int, int)
 */
public class TrieNode implements Comparable<TrieNode>, Serializable {

    @Serial
    private static final long serialVersionUID = 5727284941846160588L;

    private final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private final static Lock r = rwl.readLock();

    private final static Lock w = rwl.writeLock();

//    private static MultiCodeLookupTable multiCodeLookupTable = new MultiCodeLookupTable();

    /**
     * 树节点字符
     */
    private int c;

    /**
     * 节点上绑定的Code值
     */
    private int code;

    /**
     * 状态 (1:继续 2:是个词语但是还可以继续 3:确定)
     */
    private byte status = 1;

    /**
     * Code类型，用于扩展
     */
    private byte type;

    /**
     * 分支信息
     */
    public TrieNode[] branches = null;

    /**
     * 无参构造器
     */
    public TrieNode() {
    }

    /**
     * @see #c
     * c
     */
    private TrieNode(int c) {
        this.c = c;
    }

    /**
     * @see #c
     * c
     * @see #status
     * status
     */
    private TrieNode(int c, int status) {
        this.c = c;
        this.status = (byte) status;
    }

    /**
     * @see #c
     * c
     * @see #status
     * status
     * @see #code
     * code
     */
    public TrieNode(int c, int status, int code) {
        this.c = c;
        this.status = (byte) status;
        this.code = code;
    }

    /**
     * @see #c
     * c
     * @see #status
     * status
     * @see #code
     * code
     * @see #type
     * type
     */
    public TrieNode(int c, int status, int code, int type) {
        this.c = c;
        this.status = (byte) status;
        this.code = code;
        this.type = (byte) type;
    }

    /**
     * @return int
     * @throws
     * @Param [trieNode]
     * @Date 2022/12/5 18:51
     */
    @Override
    public int compareTo(TrieNode trieNode) {
        int tc = this.c, oc = trieNode.c;
        return tc > oc ? 1 : (tc == oc ? 0 : -1);
    }

    @Override
    public int hashCode() {
        return this.c;
    }

    public byte getStatus() {
        return this.status;
    }

    public int getC() {
        return this.c;
    }

    /**
     * 获取TrieNode,节点通过前缀方式
     * @return com.cn.jmw.trie.TrieNode
     * @throws
     * @Param [c]
     * @Date 2023/1/16 15:52
     */
    public TrieNode get(int c) {
        r.lock();
        try {
            return getBranch(c);
        } finally {
            r.unlock();
        }
    }

    /**
     * 通过传入字符查询当前数组索引，获取分支节点
     * @Param [c]
     * @return com.cn.jmw.trie.TrieNode
     * @exception
     * @Date 2023/1/16 19:38
     */
    public TrieNode getBranch(int c) {
        r.lock();
        try {
            int index = getIndex(c);
            if (index<0){
                return null;
            }else {
                return this.branches[index];
            }
//            return index < 0 ? null : this.branches[index];
        } finally {
            r.unlock();
        }
    }

    /**
     * 获取数组索引
     * @Param [c]
     * @return int
     * @exception
     * @Date 2023/1/16 19:39
     */
    public int getIndex(int c) {
        r.lock();
        try {
            if (branches == null) {
                return -1;
            }
            int i = Arrays.binarySearch(this.branches, new TrieNode(c));
            return i;
//            return branches==null?-1:Arrays.binarySearch(this.branches, new TrieNode(c));
        } finally {
            r.unlock();
        }
    }




    /**
     * @return com.cn.jmw.trie.TrieNode
     * @throws
     * @Param [newBranch]
     * @Date 2022/12/6 20:59
     * @description 添加新词
     */
    public boolean add(int[] word, MultiCodeMode mode, int code, int type) {
        w.lock();
        try {
            TrieNode tempBranch = this;
            Result<Boolean> added = new Result<Boolean>();
            added.setValue(false);
            for (int i = 0; i < word.length; i++) {
                if (word.length == i + 1) {
                    tempBranch = tempBranch.add(new TrieNode(word[i], 3, code,type), mode, added);
                } else {
                    tempBranch = tempBranch.add(new TrieNode(word[i], 1), mode, added);
                }
            }
            return added.getValue();
        } finally {
            w.unlock();
        }
    }

    /**
     * 增加子页节点
     * 添加一个叶子节点 需要判断当前节点的子节点集合中是否包含该节点
     * 如果包含 则需要更新 不包含则直接进行添加
     * @param newBranch
     * @param mode
     * @return
     */
    private TrieNode add(TrieNode newBranch, MultiCodeMode mode,
                         Result<Boolean> added) {
        added.setValue(false);
        if (branches == null) {
            branches = new TrieNode[0];
        }
        int bs = getIndex(newBranch.getC());
        if (bs > -1) {
            // 更新既存子节点
            if (this.branches[bs] == null) {
                this.branches[bs] = newBranch;
            }
            TrieNode branch = this.branches[bs];
            switch (newBranch.getStatus()) {
                case -1:
                    branch.status = 1;
                    break;
                case 1:
                    if (branch.status == 3) {
                        branch.status = 2;
                    }
                    break;
                case 3:
                    if (branch.status == 2 || branch.status == 3) {
                        // 多Code情况
                        if (MultiCodeMode.Drop == mode) {
                            added.setValue(addBranchOnDropMode(newBranch, branch));
                        } else if (MultiCodeMode.Replace == mode) {
                            added.setValue(addBranchOnReplaceMode(newBranch, branch));
                        } else if (MultiCodeMode.Small == mode) {
                            added.setValue(addBranchOnSmallMode(newBranch, branch));
                        } else if (MultiCodeMode.Big == mode) {
                            added.setValue(addBranchOnBigMode(newBranch, branch));
                        } else if (MultiCodeMode.Append == mode) {
                            added.setValue(addBranchOnAppendMode(newBranch, branch));
                        } else if (MultiCodeMode.ThrowException == mode) {
                            throw new UnsupportedOperationException("加入了不允许重复的词");
                        } else {
                            throw new UnsupportedOperationException("不支持的多码处理模式");
                        }
                    } else {
                        branch.type = newBranch.type;
                        branch.code = newBranch.code;
                        branch.status = 2;
                    }
            }
            return branch;
        }

        if (bs < 0) {
            // 新增子节点
            int l=branches.length;
            TrieNode[] newBranches = new TrieNode[branches.length + 1];
            int insert = -(bs + 1);
            if (insert > 0) {
                System.arraycopy(this.branches, 0, newBranches, 0, insert);
            }
            if (branches.length - insert > 0) {
                System.arraycopy(branches, insert, newBranches, insert + 1,
                        branches.length - insert);
            }
            newBranches[insert] = newBranch;
            added.setValue(true);
            this.branches = newBranches;
        }
        return newBranch;
    }

    /**
     * 分支 追加模式
     * @Param [newBranch, branch]
     * @return java.lang.Boolean
     * @exception
     * @Date 2023/1/16 20:52
     */
    private Boolean addBranchOnAppendMode(TrieNode newBranch, TrieNode branch) {
        return null;
    }

    /**
     * 分支 保留大数码模式
     * @Param [newBranch, branch]
     * @return java.lang.Boolean
     * @exception
     * @Date 2023/1/16 20:52
     */
    private Boolean addBranchOnBigMode(TrieNode newBranch, TrieNode branch) {
        return null;
    }

    /**
     * 分支 保留小数码模式
     * @Param [newBranch, branch]
     * @return java.lang.Boolean
     * @exception
     * @Date 2023/1/16 20:52
     */
    private Boolean addBranchOnSmallMode(TrieNode newBranch, TrieNode branch) {
        return null;
    }

    /**
     * 分支 重置模式
     * @Param [newBranch, branch]
     * @return java.lang.Boolean
     * @exception
     * @Date 2023/1/16 20:52
     */
    private Boolean addBranchOnReplaceMode(TrieNode newBranch, TrieNode branch) {
        return null;
    }

    /**
     * 分支 丢弃模式
     * @Param [newBranch, branch]
     * @return java.lang.Boolean
     * @exception
     * @Date 2023/1/16 20:52
     */
    private Boolean addBranchOnDropMode(TrieNode newBranch, TrieNode branch) {
        return null;
    }

    /**
     * @return boolean
     * @throws
     * @Param [word, code, type]
     * @Date 2022/12/6 21:07
     * description 删除词组
     */
    public boolean remove(int[] word, int code, int type) {
        w.lock();
        try {

            return true;
        } finally {
            w.unlock();
        }
    }

    public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        r.lock();
        try {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + toString());
            if (branches != null) {
                for (int i = 0; i < branches.length - 1; i++) {
                    branches[i].print(prefix + (isTail ? "    " : "│   "),
                            false);
                }
                if (branches.length > 0) {
                    branches[branches.length - 1].print(prefix
                            + (isTail ? "    " : "│   "), true);
                }
            }
        } finally {
            r.unlock();
        }
    }

}
