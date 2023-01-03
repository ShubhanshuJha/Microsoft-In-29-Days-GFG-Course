/* Interleaved Strings
Given strings A, B, and C, find whether C is formed by an interleaving of A and B.
An interleaving of two strings S and T is a configuration such that it creates a new string Y from the concatenation substrings
of A and B and |Y| = |A + B| = |C|.

For example: A = "XYZ"  B = "ABC",	so we can make multiple interleaving string Y like, XYZABC, XAYBCZ, AXBYZC, XYAZBC and
many more so here your task is to check whether you can create a string Y which can be equal to C. Specifically, you just need to
create substrings of string A and create substrings B and concatenate them and check whether it is equal to C or not.

Note: a + b is the concatenation of strings a and b.
Return true if C is formed by an interleaving of A and B, else return false.


Example 1:
Input:	A = YX, B = X, C = XXY
Output:	0
Explanation: XXY is not interleaving of YX and X

Example 2:
Input:	A = XY, B = X, C = XXY
Output:	1
Explanation: XXY is interleaving of XY and X.  */


// Bruteforce Approach
class Solution {
    // Time: O(2 ^ max(M, N))        Space: O(M + N)
	public boolean isInterLeave(String a, String b, String c) {
	    int m = a.length(),
	        n = b.length();
	    if (m + n != c.length()) return false;
	    return recursion(a.toCharArray(), b.toCharArray(), c, m, n, 0, 0, 0);
	}
	private boolean recursion(char[] a, char[] b, String c, int m, int n, int i, int j, int k) {
	    if (i == m && j == n) return true;
	    if (i == m) {
	        while (j < n) {
	            if (b[j] != c.charAt(k)) return false;
	            j++;
	            k++;
	        }
	        return true;
	    }
	    if (j == n) {
	        while (i < m) {
	            if (a[i] != c.charAt(k)) return false;
	            i++;
	            k++;
	        }
	        return true;
	    }
	    if (i + j != k) return false;
	    
	    if (a[i] != b[j]) {
	        if (a[i] == c.charAt(k))
	            return recursion(a, b, c, m, n, i + 1, j, k + 1);
	        if (b[j] == c.charAt(k))
	            return recursion(a, b, c, m, n, i, j + 1, k + 1);
	        return false;
	    }
	    
	    if (a[i] == c.charAt(k)) {
	        return recursion(a, b, c, m, n, i + 1, j, k + 1) ||
	                recursion(a, b, c, m, n, i, j + 1, k + 1);
	    }
	    return false;
	}
}
// --------------------------------------------------------------------------------------
// Memoization Approach
class Solution {
	// Time: O(m * n)		Space: O(m * n)
    private byte[][] dp;
	public boolean isInterLeave(String a, String b, String c) {
	    int m = a.length(),
	        n = b.length();
	    if (m + n != c.length()) return false;
	    dp = new byte[m][n];
	    
	    return recursion(a.toCharArray(), b.toCharArray(), c, m, n, 0, 0, 0);
	}
	private boolean recursion(char[] a, char[] b, String c, int m, int n, int i, int j, int k) {
	    if (i == m && j == n) return true;
	    if (i == m) {
	        while (j < n) {
	            if (b[j] != c.charAt(k)) return false;
	            j++;
	            k++;
	        }
	        return true;
	    }
	    if (j == n) {
	        while (i < m) {
	            if (a[i] != c.charAt(k)) return false;
	            i++;
	            k++;
	        }
	        return true;
	    }
	    
	    if (dp[i][j] != (byte)0) return dp[i][j] == (byte)1;
	    
	    boolean isPoss;
	    
	    if (a[i] != b[j]) {
	        if (a[i] == c.charAt(k))
	            isPoss = recursion(a, b, c, m, n, i + 1, j, k + 1);
	        else if (b[j] == c.charAt(k))
	            isPoss = recursion(a, b, c, m, n, i, j + 1, k + 1);
	        else
	            isPoss = false;
	    } else {
	        if (a[i] == c.charAt(k))
	            isPoss = recursion(a, b, c, m, n, i + 1, j, k + 1) ||
	                    recursion(a, b, c, m, n, i, j + 1, k + 1);
	        else
	            isPoss = false;
	    }
	    dp[i][j] = isPoss ? (byte)1 : (byte)2;
	    return isPoss;
	}
}


