package com.cn.jmw.uitls.algorithm.array;

import java.util.BitSet;
import java.util.Map;

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
     * 原地-翻转
     */
    public void reverse(int start,int end){
        while (start < end) {
            T temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

    /**
     * @Param [arr]
     * @return void
     * @exception
     * @Date 2023/2/7 13:59
     * 原地-指定数据后移
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
     * 原地-int零移动
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

    public static int findRepeatNumber(int[] nums) {
        BitSet bitSet = new BitSet(100000);
        for (int i = 0; i < nums.length; i++) {
            if (bitSet.get(nums[i])){
                return nums[i];
            }
            bitSet.set(nums[i]);
        }
        return -1;
    }

    /**
     * @Param [s]
     * @return char
     * @exception
     * @Date 2023/3/9 10:08
     * 字符串中第一个不重复的
     */
    public char firstUniqChar(String s) {
        //判断字母a-z是否在s中只出现了一次，在判断哪一个是最先的
        int ans=50001;//s长度小于5000
        int first,last;
        for(int i=0;i<26;i++){
            char ch=(char)('a'+i);
            first=s.indexOf(ch);
            if(first!=-1){
                //说明s中包含有该字母
                last=s.lastIndexOf(ch);
                if(first==last){
                    //说明该字母只出现了一次，记录出现位置
                    ans=ans>first?first:ans;
                }
            }
        }
        return ans==50001?' ':s.charAt(ans);
    }


    //只出现一次的数字，使用 int只有32位来计算每一个位置次数/只有当前次数余3等于1的时候说明当前位只出现了一次
    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((cnt[i] % 3 & 1) == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {1,0,1,0,1,0,100};
        int repeatNumber = findRepeatNumber(arr);
        System.out.println(repeatNumber);

        System.out.println(new ArrayUtils<>().firstUniqChar("asdakukand"));

        System.out.println(new ArrayUtils<>().singleNumber(arr));
    }

}
