// Non Repeating Character
/* Given a string S consisting of lowercase Latin Letters. Return the first non-repeating character in S.
If there is no non-repeating character, return '$'.

Example 1: Input: S = hello
Output: h
Explanation: In the given string, the first character which is non-repeating is h, as it appears first
		and there is no other 'h' in the string.

Example 2: Input: S = zxvczbtxyzvy
Output: c
Explanation: In the given string, 'c' is the character which is non-repeating. */

class Solution {
    //Function to find the first non-repeating character in a string.
    
    // Time: O(n) + O(n)       Space: O(distinct(S)) + O(256)
    // static char nonrepeatingCharacter(String S) {
    //     HashMap<Integer, Character> hmap = new HashMap<>();
    //     int[] f = new int[256];
    //     for (char ch : S.toCharArray()) {
    //         f[ch]++;
    //     }
    //     int st = 0;
    //     for (char ch : S.toCharArray()) {
    //         if (f[ch] == 1) {
    //             hmap.put(st, ch);
    //             st++;
    //         }
    //     }
    //     return hmap.isEmpty() ? '$' : hmap.get(0);
    // }
    
    // Most Optimal Approach: Time: O(n) + O(n)       Space: O(26)
    static char nonrepeatingCharacter(String S) {
        int[] map = new int[26];
        for (char ch : S.toCharArray()) {
            map[(int)ch % 26]++;
        }
        char res = '$';
        for (char ch : S.toCharArray()) {
            if (map[(int)ch % 26] == 1) {
                res = ch;
                break;
            }
        }
        return res;
    }
}