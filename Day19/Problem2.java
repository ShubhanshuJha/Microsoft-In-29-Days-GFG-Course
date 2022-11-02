/* Find the number of islands
Given a grid of size n*m (n is the number of rows and m is the number of columns in the grid) consisting of '0's (Water) and '1's(Land).
Find the number of islands.
Note: An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.

Example 1:
Input:	grid = {{0,1},{1,0},{1,1},{1,0}}
Output:	1
Explanation: The grid is-
		0 1
		1 0
		1 1
		1 0
	All lands are connected.

Example 2:
Input:	grid = {{0,1,1,1,0,0,0},{0,0,1,1,0,1,0}}
Output:	2
Expanation: The grid is-
		0 1 1 1 0 0 0
		0 0 1 1 0 1 0 
	There are two islands :- one is colored in blue other in orange.  */


// DFS Approach
class Solution {
    private final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    private final int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    // Time: O(mn)		Space: O(mn)
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] vis = new boolean[m][n];
        int islandCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    islandCount++;
                    vis[i][j] = true;
                    look(grid, m, n, i, j, vis);
                }
            }
        }
        return islandCount;
    }
    private void look(char[][] grid, int m, int n, int i, int j, boolean[][] vis) {
        for (int d = 0; d < 8; d++) {
            int x = i + dx[d];
            int y = j + dy[d];
            if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && grid[x][y] == '1') {
                vis[x][y] = true;
                look(grid, m, n, x, y, vis);
            }
        }
    }
    
}


