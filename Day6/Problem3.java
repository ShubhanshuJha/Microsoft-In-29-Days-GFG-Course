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

----------------------------------------------------------------------

class Solution {
    // Based on reference from GFG
    // Time: O(n1 * n2)        Space: O(n1 + n2)
    public String multiplyStrings(String s1, String s2) {
        boolean isS1Neg = s1.charAt(0) == '-',
            isS2Neg = s2.charAt(0) == '-';
        if (isS1Neg) s1 = s1.substring(1, s1.length());
        if (isS2Neg) s2 = s2.substring(1, s2.length());
        
        int n1 = s1.length(),
            n2 = s2.length();
        if ("0".equals(s1) || "0".equals(s2)) return "0";
        if ("1".equals(s1)) return s2;
        if ("1".equals(s2)) return s1;
        
        int[] result = new int[n1 + n2];
        
        int i_n1 = 0,
            i_n2 = 0;
        
        for (int i = n1 - 1; i >= 0; i--) {
            int carry = 0;
            int val1 = s1.charAt(i) - '0';
            i_n2 = 0;
            
            for (int j = n2 - 1; j >= 0; j--) {
                int val2 = s2.charAt(j) - '0';
                int prod = val1 * val2;
                int sum = prod + result[i_n1 + i_n2] + carry;
                
                carry = sum / 10;
                result[i_n1 + i_n2] = sum % 10;
                i_n2++;
            }
            if (carry > 0)
                result[i_n1 + i_n2] += carry;
            i_n1++;
        }
        
        int i = n1 + n2 - 1;
        while (i >= 0 && result[i] == 0) i--;
        
        StringBuilder num = new StringBuilder();
        while (i >= 0) num.append(result[i--]);
        
        if (isS1Neg ^ isS2Neg)
            num.insert(0, '-');
        
        return num.toString();
    }
}