package com.cn.jmw.uitls.array;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月06日 13:52
 * @Version 1.0
 */
public class ArrayUtils<T>{


    private T[] arr;

    private Class type;

    public void setArr(T[] arr) {
        this.arr = arr;
    }
    public Object[] getArr() {
        return arr;
    }

    public ArrayUtils(){}

    public ArrayUtils(T[] t){
        this.arr = t;
        this.type = t.getClass();
    }


    /**
     * @Param [arr, start, end]
     * @return void
     * @exception
     * @Date 2023/2/6 13:56
     * 原地翻转
     */
    public void reverse(int start,int end){
        while (start < end) {
            T temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    /**
     * @Param [arr]
     * @return void
     * @exception
     * @Date 2023/2/7 13:59
     * 指定数据后移
     */
    public void moveZeroes(T a) {
        if(a==null) {
            return;
        }
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]!=a){
                arr[j++]=arr[i];
            }
        }

        for (int i = j; i <arr.length; i++) {
            arr[i] = a;
        }
    }

    /**
     * @Param [arr]
     * @return void
     * @exception
     * @Date 2023/2/7 13:59
     * int零移动
     */
    public void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

}
