/* Edit Distance
Given two strings s and t. Return the minimum number of operations required to convert s to t.
The possible operations are permitted:
    Insert a character at any position of the string.
    Remove any character from the string.
    Replace any character from the string with any other character.

Example 1:
Input:  s = "geek", t = "gesek"
Output: 1
Explanation: One operation is required inserting 's' between two 'e's of str1.

Example 2:
Input:  s = "gfg", t = "gfg"
Output: 0
Explanation: Both strings are same.  */


// Bruteforce Approach
class Solution {
    // Time: O(3 ^ min(m, n))        Space: O(m + n) [stack space]
    public int editDistance(String s, String t) {
        return recursion(s, t, s.length() - 1, t.length() - 1);
    }
    private int recursion(String s, String t, int i, int j) {
        // Base case
        if (i < 0 && j < 0) return 0;
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        if (s.charAt(i) == t.charAt(j))
            return recursion(s, t, i - 1, j - 1);
        
        int oper1 = recursion(s, t, i, j - 1); // operation cost, if inserting a new character
        int oper2 = recursion(s, t, i - 1, j); // operation cost, if removing a character
        int oper3 = recursion(s, t, i - 1, j - 1); // operation cost, if replacing a character

        return 1 + Math.min(oper1, Math.min(oper2, oper3));
    }
}
------------------------------------------------------------------------------------
// Memoization Approach
class Solution {
    // Time: O(m * n)       Space: O(m * n) + O(m + n)[stack space]
    private int[][] dp;
    public int editDistance(String s, String t) {
        dp = new int[s.length()][t.length()];
        for (int[] r : dp)
            Arrays.fill(r, -1);
        return recursion(s, t, s.length() - 1, t.length() - 1);
    }
    private int recursion(String s, String t, int i, int j) {
        // Base case
        if (i < 0 && j < 0) return 0;
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j))
            return dp[i][j] = recursion(s, t, i - 1, j - 1);
        
        int oper1 = recursion(s, t, i, j - 1); // operation cost, if inserting a new character
        int oper2 = recursion(s, t, i - 1, j); // operation cost, if removing a character
        int oper3 = recursion(s, t, i - 1, j - 1); // operation cost, if replacing a character

        return dp[i][j] = 1 + Math.min(oper1, Math.min(oper2, oper3));
    }
}
------------------------------------------------------------------------------------
// Tabular Approach
class Solution {
    // Time: O(m * n)        Space: O(m * n)
    public int editDistance(String s, String t) {
        if (s.equals(t)) return 0;
        
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = i ^ j;
                else if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }
}
