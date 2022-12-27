/* Maximize The Cut Segments
Given an integer N denoting the Length of a line segment. You need to cut the line segment in such a way that the cut length of a line
segment each time is either x , y or z. Here x, y, and z are integers.
After performing all the cut operations, your total number of cut segments must be maximum.

Example 1:
Input:  N = 4,  x = 2, y = 1, z = 1
Output: 4
Explanation: Total length is 4, and the cut lengths are 2, 1 and 1.  We can make maximum 4 segments each of length 1.

Example 2:
Input:  N = 5,  x = 5, y = 3, z = 2
Output: 2
Explanation: Here total length is 5, and the cut lengths are 5, 3 and 2. We can make two segments of lengths 3 and 2.  */


// Bruteforce Approach
class Solution {
    // Time: O(3 ^ n)       Space: O(n)
    public int maximizeCuts(int n, int x, int y, int z) {
        int res = recursion(n, x, y, z);
        return res < 0 ? 0 : res;
    }
    private int recursion(int n, int x, int y, int z) {
        if (n < 0)
            return Integer.MIN_VALUE;
        if (n == 0)
            return 0;
        
        int cutCount1 = recursion(n - x, x, y, z);
        int cutCount2 = recursion(n - y, x, y, z);
        int cutCount3 = recursion(n - z, x, y, z);

        return 1 + Integer.max(cutCount1, Integer.max(cutCount2, cutCount3));
    }
}
------------------------------------------------------------------------
// Memoization Approach
class Solution {
    // Time: O(n)       Space: O(n)
    private int[] dp;
    public int maximizeCuts(int n, int x, int y, int z) {
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        recursion(n, x, y, z);
        return dp[n] < 0 ? 0 : dp[n];
    }
    private int recursion(int n, int x, int y, int z) {
        if (n < 0)
            return Integer.MIN_VALUE;
        if (n == 0)
            return 0;
        if (dp[n] != -1) return dp[n];
        
        int cutCount1 = recursion(n - x, x, y, z);
        int cutCount2 = recursion(n - y, x, y, z);
        int cutCount3 = recursion(n - z, x, y, z);

        return dp[n] = 1 + Integer.max(cutCount1, Integer.max(cutCount2, cutCount3));
    }
}
------------------------------------------------------------------------
// Tabular Approach
class Solution {
    // Time: O(n)       Space: O(4 * n)
    public int maximizeCuts(int n, int x, int y, int z) {
        int[][] dp = new int[4][n + 1];
        int[] arr = {x, y, z};
        
        for (int i = 0; i <= n; i++)
            dp[0][i] = 0;
        for (int i = 0; i < 4; i++)
            dp[i][0] = 0;
        
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < arr[i - 1])
                    dp[i][j] = dp[i - 1][j];
                else {
                    if (j == arr[i - 1] || dp[i][j - arr[i - 1]] > 0)
                        dp[i][j] = Integer.max(dp[i - 1][j], 1 + dp[i][j - arr[i - 1]]);
                    else
                        dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[3][n];
    }
}
