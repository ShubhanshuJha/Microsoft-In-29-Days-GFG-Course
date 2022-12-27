/* Subset Sum Problem
Given an array of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum. 

Example 1:
Input:  N = 6,  arr[] = {3, 34, 4, 12, 5, 2},   sum = 9
Output: 1
Explanation: Here there exists a subset with sum = 9, 4+3+2 = 9.

Example 2:
Input:  N = 6,  arr[] = {3, 34, 4, 12, 5, 2},   sum = 30
Output: 0
Explanation: There is no subset with sum 30.  */


// Bruteforce Approach
class Solution {
    // Time: O(2 ^ N)       Space: O(N)
    static Boolean isSubsetSum(int N, int[] arr, int sum) {
        return recursion(N, arr, sum, 0);
    }
    private static boolean recursion(int n, int[] arr, int sum, int idx) {
        if (sum < 0) return false;
        if (sum == 0) return true;
        if (n - idx == 1) return arr[idx] == sum;
        
        boolean poss1 = recursion(n, arr, sum - arr[idx], idx + 1);
        boolean poss2 = recursion(n, arr, sum, idx + 1);
        
        return poss1 | poss2;
    }
}
------------------------------------------------------------------------------
// Memoization Approach
class Solution {
// Time: O(N * sum)         Space: O(N * sum)
    private static int[][] dp;
    static Boolean isSubsetSum(int N, int[] arr, int sum) {
        dp = new int[N][sum + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        return recursion(N, arr, sum, 0);
    }
    private static boolean recursion(int n, int[] arr, int sum, int idx) {
        if (sum < 0) return false;
        if (idx == n - 1) {
            if (arr[idx] == sum) {
                dp[idx][sum] = 1;
                return true;
            } else {
                dp[idx][sum] = 0;
                return false;
            }
        }
        if (sum == 0) {
            dp[idx][sum] = 1;
            return true;
        }
        if (dp[idx][sum] != -1)
            return dp[idx][sum] == 1;
        
        boolean poss1 = recursion(n, arr, sum - arr[idx], idx + 1);
        boolean poss2 = recursion(n, arr, sum, idx + 1);
        
        if (poss1 | poss2) {
            dp[idx][sum] = 1;
            return true;
        } else {
            dp[idx][sum] = 0;
            return false;
        }
    }
}
------------------------------------------------------------------------------
// Tabular Approach
class Solution {
    // Time: O(N * sum)         Space: O(N * sum)
    static Boolean isSubsetSum(int N, int[] arr, int sum) {
        boolean[][] dp = new boolean[N + 1][sum + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= N; i++)
            dp[i][0] = true;
        // If sum is not 0 and set is empty, then answer is false
        for (int s = 1; s <= sum; s++)
            dp[0][s] = false;
        
        // Fill the subset table in bottom-up manner
        for (int s = 1; s <= sum; s++) {
            for (int i = 1; i <= N; i++) {
                dp[i][s] = dp[i - 1][s];
                if (s >= arr[i - 1]) {
                    dp[i][s] |= dp[i - 1][s - arr[i - 1]];
                }
            }
        }

        return dp[N][sum];
    }
}
