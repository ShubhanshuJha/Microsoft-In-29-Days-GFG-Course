/* Rotten Oranges
Given a grid of dimension nxm where each cell in the grid can have values 0, 1 or 2 which has the following meaning:
0 : Empty cell
1 : Cells have fresh oranges
2 : Cells have rotten oranges

We have to determine what is the minimum time required to rot all oranges. A rotten orange at index [i,j] can rot other fresh orange at
indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time. 

Example 1:	Input: grid = {{0,1,2},{0,1,2},{2,1,1}}
Output: 1
Explanation: The grid is-	0 1 2
							0 1 2
							2 1 1
		Oranges at positions (0,2), (1,2), (2,0) will rot oranges at (0,1), (1,1), (2,2) and (2,1) in unit time.

Example 2:	Input: grid = {{2,2,0,1}}
Output: -1
Explanation: The grid is-	2 2 0 1
		Oranges at (0,0) and (0,1) can't rot orange at (0,3).  */


// Iterative Approach (BFS)
class Solution {
    // Time: O(mn)		Space: O(n) [using deq to maintain rotten oranges index]
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<Pair> deq = new LinkedList<>();
        
        int freshOrangeCount = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1)
                    freshOrangeCount++;
                else if (grid[r][c] == 2)
                    deq.offer(new Pair(r, c));
            }
        }
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        int timeCounter = 0;
        while (!deq.isEmpty() && freshOrangeCount > 0) {
            timeCounter++;
            int size = deq.size();
            for (int i = 1; i <= size; i++) {
                Pair curr = deq.pollFirst();
                for (int d = 0; d < 4; d++) {
                    int r = curr.r + dx[d];
                    int c = curr.c + dy[d];
                    if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        freshOrangeCount--;
                        deq.offer(new Pair(r, c));
                    }
                }
            }
        }
        return timeCounter == 0 || freshOrangeCount > 0 ? -1 : timeCounter;
    }
}

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

