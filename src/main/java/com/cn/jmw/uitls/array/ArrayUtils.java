package com.cn.jmw.uitls.array;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月06日 13:52
 * @Version 1.0
 */
public class ArrayUtils {


    /**
     * @Param [arr, start, end]
     * @return void
     * @exception
     * @Date 2023/2/6 13:56
     * 原地翻转
     */
    public void reverse(int[] arr,int start,int end){
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
