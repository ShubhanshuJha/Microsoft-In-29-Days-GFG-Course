/* Multiply two strings
Given two numbers as strings s1 and s2. Calculate their Product.
Note: The numbers can be negative.

Example 1:	Input:	s1 = "33", s2 = "2"
Output: 66

Example 2:	Input:	s1 = "11", s2 = "23"
Output: 253 */

class Solution {
	// Simple Solution in Java using java.math.BigInteger class
    public String multiplyStrings(String s1, String s2) {
        return new BigInteger(s1).multiply(new BigInteger(s2)).toString();
    }
}
