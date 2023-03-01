package com.cn.jmw.uitls.algorithm.array;

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
            {};

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int x = 0, y = matrix.length - 1;
        while (y >= 0 &&x < matrix[0].length) {
            if (matrix[y][x] > target) y--;
            else if (matrix[y][x] < target) x++;
            else return true;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(findNumberIn2DArray(arr,0));
    }
}
