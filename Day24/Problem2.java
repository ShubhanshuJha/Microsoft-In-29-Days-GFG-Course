/* Longest Consecutive 1's
Given a number N. Find the length of the longest consecutive 1s in its binary representation.

Example 1:
Input: N = 14
Output: 3
Explanation: Binary representation of 14 is 1110, in which 111 is the longest consecutive set bits of length is 3.

Example 2:
Input: N = 222
Output: 4
Explanation: Binary representation of 222 is 11011110, in which 1111 is the longest consecutive set bits of length 4.  */


// Kadanes + Bit Manipulation Approach
class Solution {
    // Time: O(1)       Space: O(1)
    public static int maxConsecutiveOnes(int N) {
        int res = 0;
        int curr = 0;
        for (int i = 31; i >= 0; i--) {
            // int bit = (N & (1 << i)) >> i;
            // or,
            // int bit = (N & (1 << i)) != 0 ? 1 : 0;
            // or,
            int bit = (N >> i) & 1;
            if (bit == 1) {
                curr++;
                res = Integer.max(res, curr);
            } else {
                curr = 0;
            }
        }
        return res;
    }
}


