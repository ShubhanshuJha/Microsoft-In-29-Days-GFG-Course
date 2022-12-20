/* Distance of nearest cell having 1
Given a binary grid of n*m. Find the distance of the nearest 1 in the grid for each cell.
The distance is calculated as |i1  - i2| + |j1 - j2|, where i1, j1 are the row number and column number of the current cell, and i2, j2 are
the row number and column number of the nearest cell having value 1.

Example 1:
Input: 	grid = {{0,1,1,0},{1,1,0,0},{0,0,1,1}}
Output: {{1,0,0,1},{0,0,1,1},{1,1,0,0}}
Explanation: The grid is-
		0 1 1 0
		1 1 0 0
		0 0 1 1
	0's at (0,0), (0,3), (1,2), (1,3), (2,0) and (2,1) are at a distance of 1 from 1's at (0,1),
	(0,2), (0,2), (2,3), (1,0) and (1,1) respectively.

Example 2:
Input: 	grid = {{1,0,1},{1,1,0},{1,0,0}}
Output: {{0,1,0},{0,0,1},{0,1,2}}
Explanation: The grid is-
		1 0 1
		1 1 0
		1 0 0
	0's at (0,1), (1,2), (2,1) and (2,2) are at a distance of 1, 1, 1 and 2 from 1's at (0,0), (0,2), (2,0) and (1,1) respectively. */


class Solution {
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    // Time: O(nm)		Space: O(nm)
    public int[][] nearest(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] res = new int[n][m];
        
        for (int i = 0; i < n; i++)
            Arrays.fill(res[i], Integer.MAX_VALUE);
        
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !vis[i][j]) {
                    calc(grid, res, n, m, vis, i, j, i, j);
                } else if (grid[i][j] == 1) {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }
    private int calc(int[][] grid, int[][] res, int n, int m, boolean[][] vis, int r, int c, int r_, int c_) {
        if (r < 0 || r >= n || c < 0 || c >= m || vis[r][c])
            return -1;
        if (grid[r][c] == 1)
            return Math.abs(r - r_) + Math.abs(c - c_);
        
        int ans = Integer.MAX_VALUE;
        
        for (int d = 0; d < 4; d++) {
            int x = r + dx[d];
            int y = c + dy[d];
            int dist = calc(grid, res, n, m, vis, x, y, r, c);
            if (dist != -1 && ans > dist) {
                res[x][y] = Integer.min(res[x][y], dist);
                ans = dist;
                vis[x][y] = true;
            }
        }
        vis[r][c] = true;
        return res[r][c] = Integer.min(res[r][c], ans);
    }
}
