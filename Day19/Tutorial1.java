/* Flood fill Algorithm
An image is represented by a 2-D array of integers, each integer representing the pixel value of the image.
Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting
pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of
the aforementioned pixels with the newColor.

Example 1:
Input: image = {{1,1,1},{1,1,0},{1,0,1}},
        sr = 1, sc = 1, newColor = 2.
Output: {{2,2,2},{2,2,0},{2,0,1}}
Explanation: From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected by a path of the same color as the starting pixel
        are colored with the new color. Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.  */


// Recursive Approach (DFS)
class Solution {
    // Time: O(mn)      Space: O(mn)[stack space]
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        flood(image, image.length, image[0].length, sr, sc, image[sr][sc], newColor);
        return image;
    }
    private void flood(int[][] image, int m, int n, int r, int c, int oldColor, int newColor) {
        if (r < 0 || r == m || c < 0 || c == n)
            return;
        if (image[r][c] == newColor || image[r][c] != oldColor)
            return;
        
        image[r][c] = newColor;
        
        // left
        flood(image, m, n, r, c - 1, oldColor, newColor);
        // right
        flood(image, m, n, r, c + 1, oldColor, newColor);
        // up
        flood(image, m, n, r - 1, c, oldColor, newColor);
        // down
        flood(image, m, n, r + 1, c, oldColor, newColor);
    }
}
--------------------------------------------------------------------------------------------------
