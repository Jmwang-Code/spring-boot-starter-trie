package com.cn.jmw.uitls.algorithm.quickSort;

import java.util.Arrays;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月25日 16:13
 * @Version 1.0
 */
public class QuickSort {

    public String[] sortPeople(String[] names, int[] heights) {
        quickSort(names, heights,0,heights.length-1);
        return names;
    }
    public void quickSort(String[] names, int[] heights, int low, int hight){
        if(low >= hight){
            return;
        }
        int tmp = partition(names,heights,low,hight);
        quickSort(names,heights,low,tmp-1);
        quickSort(names,heights,tmp+1,hight);
    }

    public int partition(String[] names, int[] heights, int low, int hight){
        int i = low;
        int j = hight;
        while(i<j){
            while(i<j && heights[j]<=heights[low])j--;
            while(i<j && heights[i]>=heights[low])i++;
            swap(names,heights,i,j);
        }
        swap(names,heights,i,low);
        return i;
    }

    public void swap(String[] names, int[] heights, int low, int hight){
        String str_tmp = names[low];
        int num_tmp = heights[low];
        names[low] = names[hight];
        heights[low] = heights[hight];
        names[hight] = str_tmp;
        heights[hight] = num_tmp;
    }

    public static void main(String[] args) {
        String[] strings = new QuickSort().sortPeople(new String[]{"a", "c", "b"}, new int[]{180, 170, 165});
        System.out.println(Arrays.toString(strings));
    }
}
