/* Maximum path sum in matrix
Given a NxN matrix of positive integers. There are only three possible moves from a cell Matrix[r][c].
    Matrix[r+1][c]
    Matrix[r+1][c-1]
    Matrix[r+1][c+1]
Starting from any column in row 0 return the largest sum of any of the paths up to row N-1.
NOTE: We can start from any column in zeroth row and can end at any column in (N-1)th row.


Example 1:
Input:  N = 2,  Matrix = {{348, 391}, {618, 193}}
Output: 1009
Explaination: The best path is 391 -> 618. It gives the sum = 1009.

Example 2:
Input:  N = 2,  Matrix = {{2, 2}, {2, 2}}
Output: 4
Explaination: No matter which path is chosen, the output is 4.  */


// Bruteforce Approach: Recursion
class Solution {
    // Time: O(3 ^ N)        Space: O(N * N)
    private static int[] dx = {1, 1, 1},
                         dy = {0, -1, 1};
    static int maximumPath(int N, int[][] Matrix) {
        int res = 0;
        
        for (int i = 0; i < N; i++) {
            int currRes = recursion(Matrix, N, 0, i);
            res = Integer.max(res, currRes);
        }
        
        return res;
    }
    private static int recursion(int[][] mat, int n, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n)
            return 0;
        int res = Integer.MIN_VALUE;
        for (int d = 0; d < 3; d++) {
            int r = x + dx[d],
                c = y + dy[d];
            res = Integer.max(res, mat[x][y] + recursion(mat, n, r, c));
        }
        return res;
    }
}
----------------------------------------------------------------------------
// DP Approach: Memoization
class Solution {
    // Time: O(N * N)        Space: O(N * N)
    private static int[] dx = {1, 1, 1},
                         dy = {0, -1, 1};
    static int maximumPath(int N, int[][] Matrix) {
        int res = 0;
        int[][] dp = new int[N][N];
        Arrays.stream(dp).forEach(r-> Arrays.fill(r, -1));

        for (int i = 0; i < N; i++) {
            int currRes = recursion(Matrix, N, dp, 0, i);
            res = Integer.max(res, currRes);
        }
        
        return res;
    }
    private static int recursion(int[][] mat, int n, int[][] dp, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n)
            return 0;
        if (dp[x][y] != -1) return dp[x][y];

        int res = Integer.MIN_VALUE;
        for (int d = 0; d < 3; d++) {
            int r = x + dx[d],
                c = y + dy[d];
            res = Integer.max(res, mat[x][y] + recursion(mat, n, dp, r, c));
        }
        return dp[x][y] = res;
    }
}
----------------------------------------------------------------------------
// DP Approach: Tabular
class Solution {
    // Time: O(N * N)        Space: O(N * N)
    static int maximumPath(int N, int[][] Matrix) {
        int[][] dp = new int[N][N];
        int[] dx = {-1, -1, -1};
        int[] dy = {0, -1, 1};
        
        for (int i = 0; i < N; i++)
            dp[0][i] = Matrix[0][i];
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int maxVal = Integer.MIN_VALUE;
                for (int d = 0; d < 3; d++) {
                    int r = i + dx[d],
                        c = j + dy[d];
                    if (r >= 0 && c >= 0 && c < N)
                        maxVal = Integer.max(maxVal, dp[r][c]);
                }
                dp[i][j] = Matrix[i][j] + maxVal;
            }
        }
        
        return Arrays.stream(dp[N - 1]).max().getAsInt();
    }
}
