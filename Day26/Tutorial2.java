/* Egg Dropping Puzzle
You are given N identical eggs and you have access to a K-floored building from 1 to K.
There exists a floor f where 0 <= f <= K such that any egg dropped at a floor higher than f will break, and any egg
dropped at or below floor f will not break. There are few rules given below. 
    An egg that survives a fall can be used again.
    A broken egg must be discarded.
    The effect of a fall is the same for all eggs.
    If the egg doesn't break at a certain floor, it will not break at any floor below.
    If the eggs breaks at a certain floor, it will break at any floor above.
Return the minimum number of moves that you need to determine with certainty what the value of f is.


Example 1:
Input:  N = 1, K = 2
Output: 2
Explanation: 1. Drop the egg from floor 1. If it breaks, we know that f = 0.
    2. Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
    3. If it does not break, then we know f = 2.
    4. Hence, we need at minimum 2 moves to determine with certainty what the value of f is.

Example 2:
Input:  N = 2, K = 10
Output: 4  */


// Bruteforce Approach
class Solution {
    // Time: O(2^k)     Space: O(1)
    static int eggDrop(int n, int k) {
        return recursion(n, k);
	}
    private static int recursion(int n, int k) {
        if (n == 1 || k <= 1) return k;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            // If the egg breaks from the ith floor
            int chance1 = recursion(n - 1, i - 1);

            // If the egg does not break from the ith floor
            int chance2 = recursion(n, k - i);

            int worstCase = 1 + Integer.max(chance1, chance2);
            ans = Integer.min(ans, worstCase);
        }

        return ans;
    }
}
---------------------------------------------------------------------------
// DP Approach: Memoization
class Solution {
    // Time: O(n * k)       Space: O(n * k)
    private static int[][] dp;
    static int eggDrop(int n, int k) {
        dp = new int[n + 1][k + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        return recursion(n, k);
	}
    private static int recursion(int n, int k) {
        if (n == 1 || k <= 1) return dp[n][k] = k;

        if (dp[n][k] != -1) return dp[n][k];

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            // If the egg breaks from the ith floor
            int chance1 = recursion(n - 1, i - 1);

            // If the egg does not break from the ith floor
            int chance2 = recursion(n, k - i);

            int worstCase = 1 + Integer.max(chance1, chance2);
            ans = Integer.min(ans, worstCase);
        }

        return dp[n][k] = ans;
    }
}
