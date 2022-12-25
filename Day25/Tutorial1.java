/* First non-repeating character in a stream
Given an input stream of A of n characters consisting only of lower case alphabets. The task is to find the first non repeating character,
each time a character is inserted to the stream. If there is no such character then append '#' to the answer.

Example 1:
Input: A = "aabc"
Output: "a#bb"
Explanation: For every character first non-repeating character is as follow-
    "a" - first non-repeating character is 'a'
    "aa" - no non-repeating character so '#'
    "aab" - first non-repeating character is 'b'
    "aabc" - first non-repeating character is 'b'

Example 2:
Input: A = "zz"
Output: "z#"
Explanation: For every character first non-repeating character is as follow-
    "z" - first non-repeating character is 'z'
    "zz" - no non-repeating character so '#'  */

// Queue Approach
class Solution {
    // Time: O(26 * n)       Space: O(26)
    public String FirstNonRepeating(String A) {
        int[] freq = new int[26];
        int n = A.length();
        char[] res = new char[n];
        
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = A.charAt(i);
            if (freq[ch - 'a']++ == 0)
                q.offer(ch);
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) q.poll();
            if (q.isEmpty())
                res[i] = '#';
            else
                res[i] = q.peek();
        }
        return String.valueOf(res);
    }
}
