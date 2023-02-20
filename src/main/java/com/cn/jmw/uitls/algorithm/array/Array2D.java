package com.cn.jmw.uitls.algorithm.array;

/**
 * @author 一只小小狗
 * @version 1.0.0
 * @ClassName DArray.java
 * @Description TODO
 * @createTime 2023年02月21日 01:30:00
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
}
