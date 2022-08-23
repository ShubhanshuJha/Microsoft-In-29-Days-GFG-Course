/* Longest Prefix Suffix
Given a string of characters, find the length of the longest proper prefix which is also a proper suffix.
NOTE: Prefix and suffix can be overlapping but they should not be equal to the entire string.

Example 1:	Input: s = "abab"
Output: 2
Explanation: "ab" is the longest proper prefix and suffix. 

Example 2:	Input: s = "aaaa"
Output: 3
Explanation: "aaa" is the longest proper prefix and suffix.  */

class Solution {
	// Time: O(n)		Space: O(n)
    int lps(String s) {
        int n = s.length();
        if (n == 1) return 0;
        if (n == 2) return s.charAt(0) == s.charAt(1) ? 1 : 0;
        HashMap<Character, Integer> hmap = new HashMap<Character, Integer>(){
            {
                for (char ch = 'a'; ch <= 'z'; ch++) put(ch, ch - 'a' + 1);
            }
        };
        
        long p = 31L;
        long mod = (long)10e9 + 7;
        long pow = 1L;

        long ph = 0;
        long sh = 0;
        int ans = 0;

        // Hashing of each character selecting last char as index = 0;
        // Hash(ch) = (A*Prime^n) + (B*Prime^n-1) + ... + (X*Prime^0)
        // Here, A, B, C, ..., X are the characters of string, and Prime=31(Prime number)
        for (int i = 0; i < n - 1; i++) {
            ph = ((ph * p) + hmap.get(s.charAt(i))) % mod;
            sh = (sh + (hmap.get(s.charAt(n - 1 - i)) * pow)) % mod;
            pow = (pow * p) % mod;
            if (ph == sh)
                ans = i + 1;
        }
        return ans;
    }
}

