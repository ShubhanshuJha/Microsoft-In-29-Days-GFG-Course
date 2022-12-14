/* Find whether path exist
Given a grid of size n*n filled with 0, 1, 2, 3. Check whether there is a path possible from the source to destination. You can traverse up, down, right and left.
The description of cells is as follows:
    A value of cell 1 means Source.
    A value of cell 2 means Destination.
    A value of cell 3 means Blank cell.
    A value of cell 0 means Wall.
Note: There are only a single source and a single destination.

Example 1:
Input: grid = {{3,0,3,0,0},{3,0,0,0,3},{3,3,3,3,3},{0,2,3,0,0},{3,0,0,1,3}}
Output: 0
Explanation: The grid is-
    3 0 3 0 0
    3 0 0 0 3
    3 3 3 3 3
    0 2 3 0 0
    3 0 0 1 3
    There is no path to reach at (3,1) i.e. at destination from (4,3) i,e source.

Example 2:
Input: grid = {{1,3},{3,2}}
Output: 1
Explanation: The grid is-
    1 3
    3 2
    There is a path from (0,0) i,e source to (1,1) i.e. destination.  */


// BFS approach
class Solution {
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    // Time: O(n^2)     Space: O(n^2)
    public boolean is_Possible(int[][] grid) {
        int n = grid.length;
        boolean[][] vis = new boolean[n][n];
        Queue<Pair> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new Pair(i, j));
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            if (grid[curr.r][curr.c] == 2)
                return true;
            vis[curr.r][curr.c] = true;
            for (int d = 0; d < 4; d++) {
                int x = curr.r + dx[d];
                int y = curr.c + dy[d];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] != 0 && !vis[x][y]) {
                    q.offer(new Pair(x, y));
                }
            }
        }
        return false;
    }
}

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
