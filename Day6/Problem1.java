/* Check if two strings are k-anagrams or not
Given two strings of lowercase alphabets and a value K, your task is to complete the given function which tells if
two strings are K-anagrams of each other or not.
Two strings are called K-anagrams if both of the below conditions are true.
1. Both have same number of characters.
2. Two strings can become anagram by changing at most K characters in a string.

Example:	Input:	str1 = "fodr", str2="gork",	k = 2
Output:	1
Explanation: Can change fd to gk */

class Solution {
	// Time: O(n)		Space: O(n)
    boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        
        for (char ch : s1.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        int count = 0;
        for (char ch : s2.toCharArray()) {
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                count++;
            } else {
                map.put(ch, map.get(ch) - 1);
            }
        }
        return count <= k;
    }
}

class Solution {
	// Time: O(n)		Space: O(256) [i.e. constant space]
    boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length()) return false;
        int[] map = new int[256];
        
        for (char ch : s1.toCharArray())
            map[ch]++;
        
        int count = 0;
        for (char ch : s2.toCharArray()) {
            if (map[ch] == 0) {
                count++;
            } else {
                map[ch]--;
            }
        }
        return count <= k;
    }
}


