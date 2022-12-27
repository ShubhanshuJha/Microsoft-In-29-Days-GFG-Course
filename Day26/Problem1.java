/* 0 - 1 Knapsack Problem
You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in
the knapsack. Note that we have only one quantity of each item.
In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items
respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum
of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item
or dont pick it (0-1 property).

Example 1:
Input:  N = 3,  W = 4
        values[] = {1,2,3}
        weight[] = {4,5,1}
Output: 3

Example 2:
Input:  N = 3,  W = 3
        values[] = {1,2,3}
        weight[] = {4,5,6}
Output: 0  */

// Bruteforce Approach
class Solution {
    // Time: O(2 ^ n)        Space: O(n)
    static int knapSack(int W, int[] wt, int[] val, int n) {
        return recursion(W, wt, n, val, 0);
    }
    private static int recursion(int W, int[] wts, int N, int[] vals, int idx) {
        if (W <= 0 || idx >= N) return 0;
        
        // If current item weight is greater than the Capacity
        if (wts[idx] > W)
            return recursion(W, wts, N, vals, idx + 1);
        
        // If current item chosen
        int currProfit1 = vals[idx] + recursion(W - wts[idx], wts, N, vals, idx + 1);
        // If current item not chosen
        int currProfit2 = recursion(W, wts, N, vals, idx + 1);

        return Integer.max(currProfit1, currProfit2);
    }
}
----------------------------------------------------------------------------
// Memoization Approach
class Solution {
    // Time: O(n * w)        Space: O(n * w)
    private static int[][] dp;
    static int knapSack(int W, int[] wt, int[] val, int n) {
        dp = new int[n + 1][W + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        return recursion(W, wt, n, val, 0);
    }
    private static int recursion(int W, int[] wts, int N, int[] vals, int idx) {
        if (W <= 0 || idx >= N) return 0;
        
        if (dp[idx][W] != -1) return dp[idx][W];

        // If current item weight is greater than the Capacity
        if (wts[idx] > W)
            return dp[idx][W] = recursion(W, wts, N, vals, idx + 1);
        
        // If current item chosen
        int currProfit1 = vals[idx] + recursion(W - wts[idx], wts, N, vals, idx + 1);
        // If current item not chosen
        int currProfit2 = recursion(W, wts, N, vals, idx + 1);

        return dp[idx][W] = Integer.max(currProfit1, currProfit2);
    }
}
----------------------------------------------------------------------------
// Tabular Approach
class Solution {
    // Time: O(n * w)        Space: O(n * w)
    static int knapSack(int W, int[] wts, int[] vals, int n) {
        // If the capacity is 0
        if (W == 0) return 0;
        // If number of items is 1
        if (n == 1) return W >= wts[0] ? vals[0] : 0;
        
        int[][] dp = new int[n + 1][W + 1];
        
        // For 0 amt. of any item, the profit is 0
        for (int i = 0; i <= W; i++)
            dp[0][i] = 0;
        // If the capacity of bag is 0 
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                // If current item has more weight than the current capacity
                if (w < wts[i - 1]) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // otherwise, choose the optimal profitable option
                    // dp[i - 1][w] -> if not included current item
                    // vals[i - 1] + dp[i - 1][w - wts[i - 1]] -> if included current item
                    dp[i][w] = Integer.max(dp[i - 1][w], vals[i - 1] + dp[i - 1][w - wts[i - 1]]);
                }
            }
        }
        
        return dp[n][W];
    }
}
----------------------------------------------------------------------------
class Solution {
    // Time: O(n * w)        Space: O(w)
    static int knapSack(int W, int[] wts, int[] vals, int n) {
        // If the capacity is 0
        if (W == 0) return 0;
        // If number of items is 1
        if (n == 1) return W >= wts[0] ? vals[0] : 0;
        
        int[] dp = new int[W + 1];
        
        // For 0 amt. of any item, the profit is 0
        for (int i = 0; i <= W; i++)
            dp[i] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int w = W; w >= wts[i - 1]; w--) {
                // Taking max of both the cases i.e to take that item or to ignore it.
                dp[w] = Integer.max(dp[w], vals[i - 1] + dp[w - wts[i - 1]]);
            }
        }
        
        return dp[W];
    }
}

