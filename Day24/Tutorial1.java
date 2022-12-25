/* Is Binary Number Multiple of 3
Given a binary number, Find, if given binary number is a multiple of 3. The given number can be big upto 2^10000.
It is recommended to finish the task using one traversal of input binary string.

Example 1:
Input:  S = "011"
Output: 1
Explanation: "011" decimal equivalent is 3, which is divisible by 3.

Example 2:
Input:  S = "100"
Output: 0
Explanation: "100" decimal equivalent is 4, which is not divisible by 3.  */


// BigInteger class Approach
class Solution {
    public int isDivisible(String s) {
        return new java.math.BigInteger(s, 2).remainder(new java.math.BigInteger("3")).compareTo(java.math.BigInteger.ZERO) == 0 ? 1 : 0;
    }
}
---------------------------------------------------------
/* Bit Manipulation Approach:
    For any binary represented number N, it is divisible by 3 if the difference of
    (count(set bits) at Even position) - (cout(set bits) at Odd position) is divisible by 3. */
class Solution {
    // Time: O(|S|)         Space: O(1)
    public int isDivisible(String s) {
        int evenBitCount = 0, oddBitCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if ((i & 1) == 0)
                    evenBitCount++;
                else
                    oddBitCount++;
            }
        }
        return Math.abs(evenBitCount - oddBitCount) % 3 == 0 ? 1 : 0;
    }
}
