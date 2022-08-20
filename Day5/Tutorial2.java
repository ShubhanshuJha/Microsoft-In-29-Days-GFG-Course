/* Add Binary Strings
Given two binary strings A and B consisting of only 0s and 1s. Find the resultant string after adding the two Binary Strings.
Note: The input strings may contain leading zeros but the output string should not have any leading zeros.

Example 1:	Input:	A = "1101", B = "111"
Output: 10100
Explanation:	 1101
				+ 111
				10100

Example 2:	Input: A = "10", B = "01"
Output: 11
Explanation:  10
			+ 01
			  11 */

class Solution {
	// Time: O(max(m, n))	Space: O(n)
    String addBinary(String A, String B) {
        Deque<Character> deq = new LinkedList<>();
        int carry = 0;
        int m = A.length(), n = B.length();
        int i = 0;
        while (i < m || i < n || carry > 0) {
            int x = 0, y = 0;
            if (i < m && A.charAt(m - 1 - i) == '1')
                x = 1;
            if (i < n && B.charAt(n - 1 - i) == '1')
                y = 1;
            deq.offer((char)(((x + y + carry) & 1) + 48));
            carry = (x + y + carry) >> 1;
            i++;
        }
        while (!deq.isEmpty() && deq.peekLast() == '0')
            deq.pollLast();
        StringBuilder sb = new StringBuilder();
        while (!deq.isEmpty())
            sb.append(deq.pollLast());
        return sb.toString();
    }
}
