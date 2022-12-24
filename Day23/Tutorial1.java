/* Rearrange characters
Given a string S such that it may contain repeated lowercase alphabets. Rearrange the characters in the string such that no two
adjacent characters are same.

Example 1:
Input:  S = geeksforgeeks
Output: 1
Explanation: egeskerskegof can be one way of rearranging the letters.

Example 2:
Input:  S = bbbabaaacd
Output: 1
Explanation: abababdcab can be one way of rearranging the letters.  */


class Solution {
    // Time: O(n log n)        Space: O(26)
    static String rearrangeCharacters(String str) {
        int n = str.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            char curr = str.charAt(i);
            freq[curr - 'a']++;
            if (freq[curr - 'a'] > (n + 1) >> 1)
                return "-1";
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> freq[b - 'a'] - freq[a - 'a']);
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (freq[ch - 'a'] != 0)
                pq.offer(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 1) {
            char ch1 = pq.poll();
            freq[ch1 - 'a']--;
            char ch2 = pq.poll();
            freq[ch2 - 'a']--;
            
            sb.append(ch1).append(ch2);
            if (freq[ch1 - 'a'] > 0)
                pq.offer(ch1);
            if (freq[ch2 - 'a'] > 0)
                pq.offer(ch2);
        }
        if (!pq.isEmpty())
            sb.append(pq.poll());
        
        return sb.toString();
    }
}

