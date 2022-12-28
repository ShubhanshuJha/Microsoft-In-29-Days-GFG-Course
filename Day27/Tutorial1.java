/* Word Wrap
Given an array nums[] of size n, where nums[i] denotes the number of characters in one word. Let K be the limit on the number of
characters that can be put in one line (line width). Put line breaks in the given sequence such that the lines are printed neatly.
Assume that the length of each word is smaller than the line width. When line breaks are inserted there is a possibility that extra
spaces are present in each line. The extra spaces include spaces put at the end of every line except the last one.

You have to minimize the following total cost where total cost = Sum of cost of all lines,
where cost of line is = (Number of extra spaces in the line)^2.


Example 1:
Input:  nums = {3,2,2,5}, k = 6
Output: 10
Explanation: Given a line can have 6 characters,
        Line number 1: From word no. 1 to 1
        Line number 2: From word no. 2 to 3
        Line number 3: From word no. 4 to 4
    So total cost = (6-3)^2 + (6-2-2-1)^2 = 3^2+1^2 = 10.
    As in the first line word length = 3 thus extra spaces = 6 - 3 = 3 and in the second line there are two word of length 2 and
    there already 1 space between two word thus extra spaces = 6 - 2 -2 -1 = 1. As mentioned in the problem description there will
    be no extra spaces in the last line. Placing first and second word in first line and third word on second line would take a
    cost of 0^2 + 4^2 = 16 (zero spaces on first line and 6-2 = 4 spaces on second), which isn't the minimum possible cost.

Example 2:
Input:  nums = {3,2,2}, k = 4
Output: 5
Explanation: Given a line can have 4 characters,
        Line number 1: From word no. 1 to 1
        Line number 2: From word no. 2 to 2
        Line number 3: From word no. 3 to 3
    Same explaination as above total cost = (4 - 3)^2 + (4 - 2)^2 = 5.  */


// Bruteforce Approach
class Solution {
    // Time: O(2 ^ n)       Space: O(n)
    public int solveWordWrap(int[] nums, int k) {
        return recursion(nums, nums.length, k, k, 0);
    }
    private int recursion(int[] arr, int n, int k, int rem, int idx) {
        if (idx == n) return 0;

        if (arr[idx] > rem) {
            return ((rem + 1) * (rem + 1)) + recursion(arr, n, k, k - arr[idx] - 1, idx + 1);
        }
        int sameLine = recursion(arr, n, k, rem - arr[idx] - 1, idx + 1);
        int nextLine = ((rem + 1) * (rem + 1)) + recursion(arr, n, k, k - arr[idx] - 1, idx + 1);
        return Integer.min(sameLine, nextLine);
    }
}
------------------------------------------------------------------------------------
// Memoization Approach
class Solution {
    // Time: O(n * k)           Space: O(n * k)
    private int[][] dp;
    public int solveWordWrap(int[] nums, int k) {
        dp = new int[nums.length + 1][k + 2];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        
        return recursion(nums, nums.length, k, k, 0);
    }
    private int recursion(int[] arr, int n, int k, int rem, int idx) {
        if (idx == n - 1) {
            if (arr[idx] <= rem) return 0;
            else return (rem + 1) * (rem + 1);
        }

        if (dp[idx][rem + 1] != -1) return dp[idx][rem + 1];
        
        if (arr[idx] > rem) {
            return dp[idx][rem + 1] = ((rem + 1) * (rem + 1)) + recursion(arr, n, k, k - arr[idx] - 1, idx + 1);
        }
        int currLine = recursion(arr, n, k, rem - arr[idx] - 1, idx + 1);
        int nextLine = ((rem + 1) * (rem + 1)) + recursion(arr, n, k, k - arr[idx] - 1, idx + 1);
        return dp[idx][rem + 1] = Integer.min(currLine, nextLine);
    }
}
------------------------------------------------------------------------------------
// Tabular Approach
class Solution {
    // Time: O(n ^ 2)           Space: O(n)
    public int solveWordWrap(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int cost = 0;
        dp[n - 1] = 0; // last line space cost = 0
        for (int i = n - 2; i >= 0; i--) {
            int currLen = -1;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                currLen += nums[j] + 1;
                if (currLen > k) {
                    break;
                }
                if (j == n - 1) {
                    cost = 0;
                } else {
                    cost = (int)Math.pow(k - currLen, 2) + dp[j + 1];
                }
                dp[i] = Integer.min(dp[i], cost);
            }
        }
        return dp[0];
    }
}
