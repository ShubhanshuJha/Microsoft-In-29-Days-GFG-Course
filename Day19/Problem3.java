/* Unit Area of largest region of 1's
Given a grid of dimension nxm containing 0s and 1s. Find the unit area of the largest region of 1s.
Region of 1's is a group of 1's connected 8-directionally (horizontally, vertically, diagonally).

Example 1:	Input: grid = {{1,1,1,0},{0,0,1,0},{0,0,0,1}}
Output: 5
Explanation: The grid is-
			1 1 1 0
			0 0 1 0
			0 0 0 1
		The largest region of 1's is colored in orange.

Example 2:	Input: grid = {{0,1}}
Output: 1
Explanation: The grid is-	0 1
		The largest region of 1's is colored in orange.  */


// DFS Approach
class Solution {
    public int findMaxArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        
        int res = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    res = Integer.max(res, dfs(grid, m, n, i, j, vis));
                }
            }
        }
        return res;
    }
    private int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    private int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    
    private int dfs(int[][] grid, int m, int n, int r, int c, boolean[][] vis) {
        if (r < 0 || r >= m || c < 0 || c >= n || vis[r][c] || grid[r][c] == 0)
            return 0;
        
        vis[r][c] = true;
        int res = 1;
        
        for (int d = 0; d < dx.length; d++) {
            int x = r + dx[d];
            int y = c + dy[d];
            res += dfs(grid, m, n, x, y, vis);
        }
        
        return res;
    }
}