/* Knight Walk
Given a square chessboard, the initial position of Knight and position of a target. Find out the minimum steps a Knight will take to reach the target position.
If it cannot reach the target position return -1.

Note: The initial and the target position co-ordinates of Knight have been given accoring to 1-base indexing.

Example 1:
Input:	N=6
		knightPos[] = {4, 5}
		targetPos[] = {1, 1}
Output: 3
Explanation: Knight takes 3 step to reach from (4, 5) to (1, 1): (4, 5) -> (5, 3) -> (3, 2) -> (1, 1). 

Example 2:
Input:	N=8
		knightPos[] = {7, 7}
		targetPos[] = {1, 5}
Output: 4
Explanation: Knight takes 4 steps to reach from (7, 7) to (1, 5): (4, 5) -> (6, 5) -> (5, 3) -> (7, 2) -> (1, 5).  */


class Solution {
	// Time: O(N^2)		Space: O(N^2)
    private int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    private int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    public int minStepToReachTarget(int[] KnightPos, int[] TargetPos, int N) {
        int x1 = KnightPos[0], x2 = TargetPos[0];
        int y1 = KnightPos[1], y2 = TargetPos[1];
        if (x1 == x2 && y1 == y2)
            return 0;
        
        int[][] mat = new int[N][N];
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x1 - 1, y1 - 1));
        
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int d = 0; d < 8; d++) {
                int x = curr.r + dy[d];
                int y = curr.c + dx[d];
                if (isValid(x, y, N) && mat[x][y] == 0) {
                    mat[x][y] = mat[curr.r][curr.c] + 1;
                    q.offer(new Pair(x, y));
                }
            }
        }
        if (mat[x2 - 1][y2 - 1] == 0)
            return -1;
        return mat[x2 - 1][y2 - 1];
    }
    private boolean isValid(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

class Pair {
    int r, c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

