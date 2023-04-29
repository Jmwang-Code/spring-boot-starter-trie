package com.cn.jmw.uitls.algorithm.data;

import java.util.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月29日 18:21
 * @Version 1.0
 */
public class RandomizedCollection {

    // val : [val_index1, val_index2 ... ]
    private Map<Integer, Set<Integer>> keyToIndexMap;

    // 真实存储 val 的数组
    private int[] arr;

    // 数组游标：[0 - size) 为存入的 val（左闭右开区间）
    private int size;

    // 该开关控制当调用 remove 时，是清除所有对应的 val，还是只清除一个 val
    // e.g. 原集合为 [1,2,1]，调用 remove(1) 时：
    // removeOne = True， 结果为 [1,2]
    // removeOne = False，结果为 [2]
    private boolean removeOne;

    // 数组的起始容量
    private final int DEFAULT_CAPACITY = 64;

    // 对数组进行扩容时的最小倍数（至少发生双倍扩容）
    private final int MIN_RESIZE_SCALE = 2;

    // 负载因子，用于扩缩容
    private final float LOAD_FACTOR = 0.75f;

    // 是否开启缩容
    private boolean isReduceCapacity;

    // 扩容次数
    private int resizeCount;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        keyToIndexMap = new HashMap<Integer, Set<Integer>>(DEFAULT_CAPACITY);
        arr = new int[DEFAULT_CAPACITY];
        size = 0;
        resizeCount = 0;
        removeOne = true;
        isReduceCapacity = false;
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        //尝试预设大小
        tryPresize();
        arr[size] = val;
        Set<Integer> indexs = keyToIndexMap.getOrDefault(val, new HashSet<Integer>());
        boolean result = indexs.isEmpty();
        indexs.add(size++);
        keyToIndexMap.put(val, indexs);
        return result;
    }

    private void tryPresize() {
        if (size + 1 >= arr.length) {
            // 跳跃性扩容
            int scale = Math.max(2, Integer.highestOneBit((resizeCount - 1) * 2));
            int[] nt = new int[arr.length * scale];
            System.arraycopy(arr, 0, nt, 0, arr.length);
            arr = nt;
            resizeCount++;
        } else if (isReduceCapacity && (size < arr.length * (1 - LOAD_FACTOR))) {
            int newCapacity = (int)(arr.length * 0.5);
            int[] nt = new int[newCapacity];
            System.arraycopy(arr, 0, nt, 0, size);
            arr = nt;
            // 假如经历过缩容，下一次还是从双倍扩容开始
            resizeCount = 0;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> indexs = keyToIndexMap.getOrDefault(val, new HashSet<Integer>());
        boolean result = !indexs.isEmpty();
        if (!indexs.isEmpty()) {
            int cnt = indexs.size();
            Iterator<Integer> iterator = indexs.iterator();
            int index = -1;
            for (int i = 0; i < (removeOne ? 1 : cnt); i++) { // remove all & remove one
                index = iterator.next();
                int old = arr[--size];
                arr[index] = old;
                Set<Integer> oldIndexs = keyToIndexMap.get(old);
                oldIndexs.remove(Integer.valueOf(size));
                if (size != index) {
                    oldIndexs.add(Integer.valueOf(index));
                }
                keyToIndexMap.put(old, oldIndexs);
            }

            if (cnt == 1 || !removeOne) {
                keyToIndexMap.remove(val);
            } else {
                Set<Integer> latest = keyToIndexMap.getOrDefault(val, new HashSet<Integer>());
                if (latest.size() == cnt) {
                    latest.remove(Integer.valueOf(index));
                    keyToIndexMap.put(val, latest);
                }
            }
        }
        tryPresize();
        return result;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int random = (int)(Math.random() * size);
        return arr[random];
    }


    public static void main(String[] args) {
        RandomizedCollection randomizedSet = new RandomizedCollection();
        randomizedSet.insert(1);
        randomizedSet.remove(2);
        randomizedSet.insert(2);
        randomizedSet.getRandom();
        randomizedSet.remove(1);
        randomizedSet.insert(2);
        randomizedSet.getRandom();
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */