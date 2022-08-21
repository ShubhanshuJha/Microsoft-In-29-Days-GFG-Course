/* Longest Palindrome in a String
Given a string S, find the longest palindromic substring in S. Substring of string S: S[ i . . . . j ] where
0 ≤ i ≤ j < len(S). Palindrome string: A string which reads the same backwards. More formally, S is palindrome
if reverse(S) = S. Incase of conflict, return the substring which occurs first ( with the least starting index).

Example 1:  Input: S = "aaaabbaa"
Output: aabbaa
Explanation: The longest Palindromic substring is "aabbaa".

Example 2:  Input: S = "abc"
Output: a
Explanation: "a", "b" and "c" are the longest palindromes with same length. The result is the
        one with the least starting index. */

class Solution {
    // Time: O(n ^ 3)       Space: O(n ^ 2) [but can be done in O(1)]
    static String longestPalin(String S) {
        int n = S.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPalindrome(S.substring(i, j + 1))) {
                    dp[i][j] = j - i + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int maxLen = 0, l = 0, r = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    l = i;
                    r = j + 1;
                }
            }
        }
        return S.substring(l, r);
    }
    private static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
-------------------------------
class Solution{
    static String longestPalin(String S){
        if (isPalin(S)) return S;
        String result = "";
        StringBuilder currStr = null;
        for (int i = 0; i < S.length(); i++) {
            currStr = new StringBuilder();
            for (int j = i; j < S.length(); j++) {
                currStr.append(S.charAt(j));
                if (isPalin(currStr.toString()) && currStr.length() > result.length())
                    result = currStr.toString();
            }
        }
        return result;
    }
    static boolean isPalin(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
-------------------------------
class Solution {
    // Time: O(n ^ 2)       Space: O(n ^ 2)
    static String longestPalin(String S) {
        int n = S.length();
        boolean[][] dp = new boolean[n][n];

        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        // check for sub-string of length 2
        int start  = 0;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = S.charAt(i) == S.charAt(i + 1);
            if (dp[i][i + 1] && maxLength == 1) {
                maxLength = 2;
                start = i;
            }
        }

        // Check for lengths greater than 2
        // For a String to be Palindrome, two conditions will be there, for n >= 3:
        // 1. str[i] == str[j]
        // 2. dp[i + 1][j - 1] = true
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < n - i + 1; j++) {
                int k = i + j - 1;
                if (dp[j + 1][k - 1] && S.charAt(j) == S.charAt(k)) {
                    dp[j][k] = true;
                    if (i > maxLength) {
                        start = j;
                        maxLength = i;
                    }
                }
            }
        }
        return S.substring(start, start + maxLength);
    }
}