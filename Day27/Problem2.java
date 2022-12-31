/* Wildcard Pattern Matching
Given two strings 'str' and a wildcard pattern 'pattern' of length N and M respectively,  You have to print '1',
if the wildcard pattern is matched with str else print '0'.

The wildcard pattern can include the characters '?' and '*'.
	'?' – matches any single character
	'*' – Matches any sequence of characters (including the empty sequence)
Note: The matching should cover the entire str (not partial str).


Example 1:
Input:	pattern = "ba*a?"	str = "baaabab"
Output:	1
Explanation: replace '*' with "aab" and '?' with 'b'. 

Example 2:
Input:	pattern = "a*ab"	str = "baaabab"
Output:	0
Explanation: Because of'a' at first position, pattern and str can't be matched.   */


// Bruteforce Approach
class Solution {
	// Time: O(2 ^ min(m, n))		Space: O(m * n)
    public int wildCard(String pattern, String str) {
        return match(str.toCharArray(), pattern.toCharArray(), str.length() - 1, pattern.length() - 1) ? 1 : 0;
    }
    private boolean match(char[] str, char[] pattern, int i, int j) {
        if (i == -1 && j == -1) return true;
        if (j == -1) return false;
        if (i == -1) {
            while (j >= 0) {
                if (pattern[j] != '*')
                    return false;
                j--;
            }
            return true;
        }
        
        if (str[i] == pattern[j] || pattern[j] == '?')
            return match(str, pattern, i - 1, j - 1);
        else if (pattern[j] == '*')
            return match(str, pattern, i - 1, j) | match(str, pattern, i, j - 1);
        else
            return false;
    }
}
--------------------------------------------------------------------------------------------
// Memoization Approach
class Solution {
	// Time: O(m * n)			Space: O(m * n)
    private int[][] dp;
    public int wildCard(String pattern, String str) {
        dp = new int[str.length()][pattern.length()];
        return match(str.toCharArray(), pattern.toCharArray(), str.length() - 1, pattern.length() - 1) ? 1 : 0;
    }
    private boolean match(char[] str, char[] pattern, int i, int j) {
        if (i == -1 && j == -1) return true;
        if (j == -1) return false;
        if (i == -1) {
            while (j >= 0) {
                if (pattern[j] != '*')
                    return false;
                j--;
            }
            return true;
        }
        
        if (dp[i][j] != 0) return dp[i][j] == 1;
        
        if (str[i] == pattern[j] || pattern[j] == '?') {
            if (match(str, pattern, i - 1, j - 1))
                dp[i][j] = 1;
            else
                dp[i][j] = 2;
        } else if (pattern[j] == '*') {
            if (match(str, pattern, i - 1, j) | match(str, pattern, i, j - 1))
                dp[i][j] = 1;
            else
                dp[i][j] = 2;
        } else {
            dp[i][j] = 2;
        }
        
        return dp[i][j] == 1;
    }
}
--------------------------------------------------------------------------------------------
// Tabular Approach
class Solution {
    // Time: O(m * n)           Space: O(m * n)
    int wildCard(String pattern, String str) {
        boolean[][] dp = new boolean[pattern.length() + 1][str.length() + 1];
        
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if(i == 0 && j == 0)
                    dp[i][j] = true;
                else if(i == 0)
                    dp[i][j] = false;
                else if(j == 0) {
                    char pc = pattern.charAt(i - 1);
                    if(pc=='*')
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = false;
                } else {
                    char pc = pattern.charAt(i - 1);
                    char sc = str.charAt(j - 1);
                    
                    if(pc == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }else if (pc == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }else if (pc == sc) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        
        if(dp[dp.length - 1][dp[0].length - 1])
            return 1;
        else
            return 0;
    }
}
