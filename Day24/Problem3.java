/* Division without using multiplication, division and mod operator
Given two integers dividend and divisor. Find the quotient after dividing dividend by divisor without using multiplication,
division and mod operator.

Example 1:
Input:	a = 10, b= 3
Output: 3
Exaplanation: 10/3 gives quotient as 3 and remainder as 1.

Example 2:
Input:	a = 43, b = -8
Output:	-5
Explanation: 43/-8 gives quotient as -5 and remainder as 3.  */


// Bruteforce Approach
class Solution {
	// Time: O(a / b)		Space: O(1)
    public static long divide(long a, long b) {
        if (a == 0 || b == 0) return 0l;
        boolean negA = a < 0l,
                negB = b < 0l;
        a = Math.abs(a);
        b = Math.abs(b);
        
        long quot = 0;
        while (a >= b) {
            a -= b;
            quot++;
        }
        if ((negA ^ negB)) {
            return -quot;
        } else {
            return quot;
        }
    }
}
----------------------------------------------------
// Efficient Approach
class Solution {
	// Time: O(1)		Space: O(1)
    public static long divide(long a, long b) {
        if (a == 0 || b == 0) return 0l;
        boolean negA = a < 0l,
                negB = b < 0l;
        a = Math.abs(a);
        b = Math.abs(b);
        
        long quot = 0l;
        long temp = 0l;
        
        for (int i = 31; i >= 0; i--) {
            if (temp + (b << i) <= a) {
                temp += (b << i);
                quot += (1l << i);
            }
        }
        
        if ((negA ^ negB)) {
            return -quot;
        } else {
            return quot;
        }
    }
}
