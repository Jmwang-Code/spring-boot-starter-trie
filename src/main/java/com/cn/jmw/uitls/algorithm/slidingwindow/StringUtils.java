package com.cn.jmw.uitls.algorithm.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月15日 14:36
 * @Version 1.0
 */
public class StringUtils {

    /**
     * @return int
     * @throws
     * @Param [s]
     * @Date 2023/2/15 15:09
     * 滑动窗口
     */
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();
        int res = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StringUtils stringUtils = new StringUtils();
        boolean b = stringUtils.checkInclusion("abc", "adacsdbca");
        System.out.println(b);
    }
}
