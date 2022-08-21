/* Check is strings are Anagram
Given two strings A and B consisting of lowercase characters. The task is to check whether two given strings are
an anagram of each other or not. An anagram of a string is another string that contains the same characters, only
the order of characters can be different. For example, act and tac are an anagram of each other.

Example 1:	Input:a = geeksforgeeks, b = forgeeksgeeks
Output: YES
Explanation: Both the string have samecharacters with same frequency. So, both are anagrams. */

class Solution {    
    //Function is to check whether two strings are anagram of each other or not.
    // Time: O(n)		Space: O(1) [Only a constant space of 256 is used, i.e. not dependent on size(string)]
    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] freq = new int[256];
        for (char ch : a.toCharArray())
            freq[ch]++;
        for (char ch : b.toCharArray())
            freq[ch]--;
        for (int i : freq)
            if (i != 0) return false;
        return true;
    }
}
