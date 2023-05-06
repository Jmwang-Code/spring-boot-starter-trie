package com.cn.jmw.uitls.algorithm.array;

import java.util.*;

/**
 * @author 一只小小狗
 * @version 1.0.0
 * @ClassName Array2D.java
 * @Description TODO
 * @createTime 2023年02月21日 01:30:00
 * 面积问题
 * DFS
 * BFS
 * 迷宫问题
 * 递归问题
 * 剪枝问题
 */
public class Array2D {

    /**
     * 油漆涂层，油漆渲染
     */
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    public int[][] paintCoating(int[][] image, int sr, int sc, int color) {
        int currColor = image[sr][sc];
        if (currColor != color) {
            dfsPaintCoating(image, sr, sc, currColor, color);
        }
        return image;
    }

    public void dfsPaintCoating(int[][] image, int x, int y, int currColor, int color) {
        if (image[x][y] == currColor) {
            image[x][y] = color;
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < image.length && my >= 0 && my < image[0].length) {
                    dfsPaintCoating(image, mx, my, currColor, color);
                }
            }
        }
    }

    /**
     * 矩阵找最大面积，（沉岛问题）（递归回溯剪枝）
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int t = dfsMaxAreaOfIsland(grid, i, j);
                    max = Math.max(max, t);
                }
            }
        }
        return max;
    }

    public int dfsMaxAreaOfIsland(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) return 0;
        int num = 1;
        grid[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            num += dfsMaxAreaOfIsland(grid, x + dx[i], y + dy[i]);
        }
        return num;
    }

    static int[][] arr =
//            {
//                    {1, 4, 7, 11, 15},
//                    {2, 5, 8, 12, 19},
//                    {3, 6, 9, 16, 22},
//                    {10, 13, 14, 17, 24},
//                    {18, 21, 23, 26, 30}
//            };
//            {
//                    {1, 2, 3, 4},
//                    {5, 6, 7, 8},
//                    {9, 10, 11, 12}
//            };
            {
                    {1, 2},
                    {2, 4},
                    {3, 6},
                    {4, 8},
                    {5, 10},
                    {6, 12},
                    {7, 14},
                    {8, 16},
                    {9, 18},
                    {10, 20}
            };
    //{};

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int x = 0, y = matrix.length - 1;
        while (y >= 0 && x < matrix[0].length) {
            if (matrix[y][x] > target) y--;
            else if (matrix[y][x] < target) x++;
            else return true;
        }
        return false;
    }

    public static int[] spiralOrder(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1, index = 0;
        int[] arr = new int[matrix.length * matrix[0].length];
        while (true) {
            for (int i = left; i <= right; i++)
                arr[index++] = matrix[top][i];
            if (++top > bottom) break;
            for (int i = top; i <= bottom; i++)
                arr[index++] = matrix[i][right];
            if (left > --right) break;
            for (int i = right; i >= left; i--)
                arr[index++] = matrix[bottom][i];
            if (top > --bottom) break;
            for (int i = bottom; i >= top; i--)
                arr[index++] = matrix[i][left];
            if (++left > right) break;
        }
        return arr;
    }

    public int[] spiralOrder1(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++)
                res[x++] = matrix[t][i]; // left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++)
                res[x++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--)
                res[x++] = matrix[b][i]; // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--)
                res[x++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permutedfs(nums, list, new ArrayList<Integer>());
        return list;
    }

    public static void permutedfs(int[] nums, List<List<Integer>> list, List<Integer> integers) {
        if (nums.length == integers.size()) {
            list.add(new ArrayList<>(integers));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (integers.contains(nums[i])) continue;
            integers.add(nums[i]);
            permutedfs(nums, list, integers);
            integers.remove(integers.size() - 1);
        }
    }


    public static List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        permuteUniquedfs(nums, len, 0, used, path, res);
        return res;
    }

    private static void permuteUniquedfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; ++i) {
            if (used[i]) {
                continue;
            }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            permuteUniquedfs(nums, len, depth + 1, used, path, res);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 2D数组 反转问题
     */
    public static void rotate(int[][] matrix) {
        //上下翻转
        for (int i = 0; i < matrix.length / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        //左右翻转
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            int[] abc = new int[26];
            char[] chars = strs[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                abc[chars[j] - 'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < abc.length; j++) {
                if (abc[j] != 0) {
                    for (int k = 0; k < abc[j]; k++) {
                        stringBuilder.append((char) ('a' + j));
                    }
                }
            }
            List<String> list = map.getOrDefault(stringBuilder.toString(), new ArrayList<String>());
            list.add(strs[i]);
            map.put(stringBuilder.toString(), list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     *  两种可能性
     *  1 2 3 4    → ↓ ← ↑
     *  3     5                 4 5  → ↓ ←
     *  2     6                 7 6
     *  1 9 8 7
     *
     *  1 2 3    → ↓ ← ↑
     *  8   4                 9   →
     *  7 6 5
     *
     *  时间复杂度为O(n) :由于题目要求，所需遍历所有节点。
     *  空间复杂度也为O(n) :由于题目要求，需要返回轮旋矩阵模型所有数据
     *
     *  需要四点定位让上下左右都能动态变化，left right top bottom
     *
     */
    public static List<Integer> spiralOrderDME(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(spiralOrder(arr)));

        System.out.println(permuteUnique(new int[]{1, 1, 2}));

        int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(groupAnagrams(new String[]{"ddddddddddg", "dgggggggggg"}));

        System.out.println(spiralOrderDME(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        }));
    }
}
