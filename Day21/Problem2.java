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


// BFS Approach
class Solution {
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    // Time: O(m*n)      Space: O(m*n)
    public int[][] nearest(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // initializing the res[][] with -1
                res[i][j] = -1;
                
                if (grid[i][j] == 1) {
                    res[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int r = q.peek()[0],
                    c = q.peek()[1];
                q.poll();
                
                for (int d = 0; d < 4; d++) {
                    int x = r + dx[d];
                    int y = c + dy[d];
                    
                    if (x < 0 || x >= m || y < 0 || y >= n || res[x][y] != -1) continue;
                    q.offer(new int[]{x, y});
                    res[x][y] = res[r][c] + 1;
                }
            }
        }
        
        return res;
    }
}
