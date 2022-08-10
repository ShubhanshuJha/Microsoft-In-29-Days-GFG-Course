// Smallest window in a string containing all the characters of another string
/* Given two strings S and P. Find the smallest window in the string S consisting of all the characters(including duplicates) of the string P.  Return "-1" in case there is no such window present. In case there are multiple such windows of same length, return the one with the least starting index. 

Example 1: Input: S = "timetopractice"	P = "toc"
Output: toprac
Explanation: "toprac" is the smallest substring in which "toc" can be found.

Example 2: Input: S = "zoomlazapzo"	P = "oza"
Output: apzo
Explanation: "apzo" is the smallest substring in which "oza" can be found. */

// Solution:
class Solution {
    //Function to find the smallest window in the string s consisting
    //of all the characters of string p.
  // Time: O(n)  Space: only 256 = O(1)
    public static String smallestWindow(String s, String p) {
        int[] map = new int[256];
        int count = 0;
        for (char ch : p.toCharArray()) {
            map[ch]++;
            if (map[ch] == 1)
                count++;
        }
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            char ch = s.charAt(j);
            map[ch]--;
            if (map[ch] == 0)
                count--;
            if (count == 0) {
                while (count == 0) {
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        start = i;
                    }
                    map[s.charAt(i)]++;
                    if (map[s.charAt(i)] == 1)
                        count++;
                    i++;
                }
            }
            j++;
        }
        return minLen != Integer.MAX_VALUE ? s.substring(start, start + minLen) : "-1";
    }
}