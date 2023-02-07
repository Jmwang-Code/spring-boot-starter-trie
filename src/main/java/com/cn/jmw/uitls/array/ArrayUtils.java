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
     * 指定字符后移（char）
     */
    public void moveZeroes(char[] arr,char a) {
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
     * 指定数据后移
     */
    public void moveZeroes(T a) {
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

}
