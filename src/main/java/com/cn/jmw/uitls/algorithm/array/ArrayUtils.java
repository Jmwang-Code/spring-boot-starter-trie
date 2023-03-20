package com.cn.jmw.uitls.algorithm.array;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月06日 13:52
 * @Version 1.0
 */
public class ArrayUtils<T> {


    private T[] arr;

    private Class type;

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    public Object[] getArr() {
        return arr;
    }

    public ArrayUtils() {
    }

    public ArrayUtils(T[] t) {
        this.arr = t;
        this.type = t.getClass();
    }


    /**
     * @return void
     * @throws
     * @Param [arr, start, end]
     * @Date 2023/2/6 13:56
     * 原地-翻转
     */
    public void reverse(int start, int end) {
        while (start < end) {
            T temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

    /**
     * @return void
     * @throws
     * @Param [arr]
     * @Date 2023/2/7 13:59
     * 原地-指定数据后移
     */
    public void moveZeroes(T a) {
        if (a == null) {
            return;
        }
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != a) {
                arr[j++] = arr[i];
            }
        }

        for (int i = j; i < arr.length; i++) {
            arr[i] = a;
        }
    }

    /**
     * @return void
     * @throws
     * @Param [arr]
     * @Date 2023/2/7 13:59
     * 原地-int零移动
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    public static int findRepeatNumber(int[] nums) {
        BitSet bitSet = new BitSet(100000);
        for (int i = 0; i < nums.length; i++) {
            if (bitSet.get(nums[i])) {
                return nums[i];
            }
            bitSet.set(nums[i]);
        }
        return -1;
    }

    /**
     * @return char
     * @throws
     * @Param [s]
     * @Date 2023/3/9 10:08
     * 字符串中第一个不重复的
     */
    public char firstUniqChar(String s) {
        //判断字母a-z是否在s中只出现了一次，在判断哪一个是最先的
        int ans = 50001;//s长度小于5000
        int first, last;
        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            first = s.indexOf(ch);
            if (first != -1) {
                //说明s中包含有该字母
                last = s.lastIndexOf(ch);
                if (first == last) {
                    //说明该字母只出现了一次，记录出现位置
                    ans = ans > first ? first : ans;
                }
            }
        }
        return ans == 50001 ? ' ' : s.charAt(ans);
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

    public int[] getLeastNumbers(int[] arr, int k) {
        // 建立一个大根堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> (b - a));
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int[] vec = new int[k];
        for (int i = 0; i < k; i++) {
            vec[i] = queue.poll();
        }
        return vec;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0, j = 1 - k; i < nums.length; i++, j++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            if (j >= 0)
                res[j] = deque.peekFirst();
        }
        return res;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            //延迟删除
            if (i >= k) {
                int removed = nums[i - k];
                if (removed <= maxHeap.peek()) {
                    maxHeap.remove(removed);
                } else {
                    minHeap.remove(removed);
                }
            }
            //元素插入
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }
            //风险对冲，堆顶平衡元素
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            //输出数组
            if (i >= k - 1) {
                if (k % 2 == 0) {
                    result[i - k + 1] = ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
                    System.out.println(result[i - k + 1]);
                } else {
                    result[i - k + 1] = (double)maxHeap.peek();
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        int[] arr = {1, 0, 1, 0, 1, 0, 100};
        int repeatNumber = findRepeatNumber(arr);
        System.out.println(repeatNumber);

        System.out.println(new ArrayUtils<>().firstUniqChar("asdakukand"));

        System.out.println(new ArrayUtils<>().singleNumber(arr));
//        System.out.println(Arrays.toString(ArrayUtils.medianSlidingWindow(new int[]{2147483647,1,2,3,4,5,6,7,2147483647}, 2)));
//        System.out.println(Arrays.toString(new ArrayUtils().medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new ArrayUtils().medianSlidingWindow(new int[]{9,7,0,3,9,8,6,5,7,6}, 2)));
    }

}

class MedianFinder {

    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    public MedianFinder() {
        //小顶堆
        queMin = new PriorityQueue<>();
        //大顶堆
        queMax = new PriorityQueue<>((a, b) -> (b - a));
    }

    public void addNum(int num) {
        if (queMax.peek() == null) {
            queMax.add(num);
        } else {
            Integer peek = queMax.peek();
            if (peek < num) {
                queMin.add(num);
            } else {
                queMax.add(num);
            }
        }

        //交换
        if (Math.abs(queMin.size() - queMax.size()) >= 2) {
            if (queMax.size() > queMin.size()) {
                queMin.add(queMax.poll());
            } else {
                queMax.add(queMin.poll());
            }
        }
    }

    public double findMedian() {
        if (queMax.isEmpty() && queMin.isEmpty()) {
            return 0;
        }

        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        } else if (queMin.size() < queMax.size()) {
            return queMax.peek();
        } else {
            return (queMin.peek() + queMax.peek()) * 1.0 / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}