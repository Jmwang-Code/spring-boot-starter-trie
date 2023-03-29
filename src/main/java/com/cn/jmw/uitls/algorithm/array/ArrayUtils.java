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
                    result[i - k + 1] = (double) maxHeap.peek();
                }
            }
        }
        return result;
    }

    public int threeSumClosest(int[] nums, int target) {
        //排序 o(NlogN)
        Arrays.sort(nums);
        //如果最小组合小于目标，直接返回
        int min = nums[0] + nums[1] + nums[2];
        if(target < min){
            return min;
        }
        //如果目标大于最大组合，直接返回
        int max = nums[nums.length-3] + nums[nums.length-2] + nums[nums.length-1];
        if( target > max) {
            return max;
        }
        //返回值 赋值最大组合
        int better_sum = max;

        for (int i = 0; i <nums.length-2 ; i++) {
            //三个节点  i ， low ，high
            int low = i+1, high = nums.length-1;
            int sum = 0;
            //low<high只要成立，low就可以像右移，high可以向左移
            while (low < high){
                min = nums[i] + nums[low] + nums[low+1];
                //当前最小组合 大于 目标,则满足条件就赋值
                if(target < min){
                    better_sum = Math.abs(target - min ) > Math.abs(target - better_sum ) ? better_sum : min;
                    break;
                }
                max = nums[i] + nums[high-1] + nums[high];
                //目标 大于 当前最大组合,则满足条件就赋值
                if(target > max){
                    better_sum = Math.abs(target - max ) > Math.abs(target - better_sum ) ? better_sum : max;
                    break;
                }
                sum = nums[i] + nums[low] + nums[high];
                //否则就进行轮询，判断当前差值是否小于返回差值
                if( Math.abs(target - sum ) < Math.abs(target - better_sum )){
                    //赋值给返回差值
                    better_sum = sum;
                }
                //low和high的移动
                if( sum == target){
                    return sum;
                }else if( sum > target){
                    high --;
                }else {
                    low++;
                }
            }
        }
        return better_sum;
    }

    public void nextPermutation(int[] nums) {
        //寻找到较小的，指针A
        int A = nums.length - 2;
        //如果保持递增 或者平值,则移动A
        while (A >= 0 && nums[A] >= nums[A + 1]) {
            A--;
        }

        if (A >= 0) {
            int B = nums.length - 1;
            //如果较小值 大于或者平值 当前值，则需要B左移，否则就确定了较大值
            while (B >= 0 && nums[A] >= nums[B]) {
                B--;
            }
            swap(nums, A, B);
        }
        //最巧妙的地方就是这里，前期的铺垫已经注定了A之后的数据都是递减，这时候排序就变得简单的多o(n)足够
        reverse(nums,A + 1);
    }

    public void swap(int[] nums, int A, int B) {
        nums[A] ^= nums[B];
        nums[B] ^= nums[A];
        nums[A] ^= nums[B];
    }

    public void reverse(int[] nums, int left) {
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 1.纵向计算
     */
    public int trap(int[] height) {
        int sum = 0;
        //第一个和最后一个的位置不论有没有墙 都不会接住雨水
        for (int i = 1; i < height.length-1; i++) {
            int left_max = 0,right_max = 0;
            for (int j = i-1; j >= 0; j--) {
                if (height[j]>left_max){
                    left_max = height[j];
                }
            }

            for (int j = i+1; j < height.length; j++) {
                if (height[j]>right_max){
                    right_max = height[j];
                }
            }

            //找出两端较小的
            int min = Math.min(left_max, right_max);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum += min - height[i];
            }
        }
        return sum;
    }

    /**
     * 2.动态规划
     */
    public int trap1(int[] height) {
        int sum = 0;

        //获取左边最大数组 和右边最大数组,并且要去掉计算 0和length-1的索引值
        int[] left = new int[height.length],right = new int[height.length];
        for (int i = 1; i < height.length-1; i++) {
            left[i] = Math.max(left[i-1],height[i-1]);
        }

        for (int i = height.length-2; i > 0 ; i--) {
            right[i] = Math.max(right[i+1],height[i+1]);
        }

        for (int i = 1; i < height.length-1; i++) {
            int min = Math.min(right[i],left[i]);
            if (min>height[i]){
                sum += min-height[i];
            }
        }
        return sum;
    }

    /**
     * 3.双指针
     */
    public int trap2(int[] height) {
        int sum = 0,left = 0,min = 10000;
        //获取左边最大数组 和右边最大数组,并且要去掉计算 0和length-1的索引值
        int[] right = new int[height.length];
        for (int i = height.length-2; i > 0 ; i--) {
            right[i] = Math.max(right[i+1],height[i+1]);
        }

        //由于left数组与 正常遍历流程方向一致，所以不需要数组存储，只需要用一个值冬天变化，固定指针就好
        for (int i = 1; i < height.length-1; i++) {
            left = Math.max(left,height[i-1]);
            min = Math.min(left,right[i]);
            if (min > height[i]) {
                sum += min - height[i];
            }
        }
        return sum;
    }

    /**
     * 4.动态规划的极致，不需要额外的数组存储，双指针的极致
     */
    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;
        //将指针绑定左右
        int left = 0, right = n - 1;
        //获取左右最大值
        int leftMax = height[0], rightMax = height[n - 1];
        int ans = 0;
        //使勇while 去掉方向，这样就不需要关心leftMax是增 rightMax是减的问题
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            //leftMax和rightMax 动态的获取当前最小值，并且移动指针
            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
//        int[] arr = {1, 0, 1, 0, 1, 0, 100};
//        int repeatNumber = findRepeatNumber(arr);
//        System.out.println(repeatNumber);
//
//        System.out.println(new ArrayUtils<>().firstUniqChar("asdakukand"));
//
//        System.out.println(new ArrayUtils<>().singleNumber(arr));
////        System.out.println(Arrays.toString(ArrayUtils.medianSlidingWindow(new int[]{2147483647,1,2,3,4,5,6,7,2147483647}, 2)));
////        System.out.println(Arrays.toString(new ArrayUtils().medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
//        System.out.println(Arrays.toString(new ArrayUtils().medianSlidingWindow(new int[]{9, 7, 0, 3, 9, 8, 6, 5, 7, 6}, 2)));
//        System.out.println(new ArrayUtils<>().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
//        new ArrayUtils<>().nextPermutation(new int[]{3,2,1});
//        System.out.println();
        System.out.println(new ArrayUtils<>().trap2(new int[]{4,2,0,3,2,5}));
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