/* Longest valid Parentheses
Given a string S consisting of opening and closing parenthesis '(' and ')'. Find length of the longest valid parenthesis substring.
A parenthesis string is valid if:
    For every opening parenthesis, there is a closing parenthesis.
    Opening parenthesis must be closed in the correct order.

Example 1:	Input: S = ((()
Output:	2
Explaination: The longest valid parenthesis substring is "()".

Example 2:	Input: S = )()())
Output:	4
Explaination: The longest valid parenthesis substring is "()()".  */


// Using Auxiliary Space
class Solution {
	// Time: O(N)		Space: O(N)
    static int maxLength(String S) {
        Stack<Integer> stack = new Stack<>();
        int maxCount = 0;
        int n = S.length();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxCount = Integer.max(maxCount, i - stack.peek());
                }
            }
        }
        return maxCount;
    }
}
-----------------------------------------------------------------------------------
// Efficient Approach
class Solution {
    // Time: O(N)       Space: O(1)
    static int maxLength(String S) {
        int n = S.length();
        int openCount = 0, closeCount = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == '(') {
                openCount++;
            } else {
                closeCount++;
            }
            if (openCount == closeCount)
                maxCount = Integer.max(maxCount, (openCount << 1));
            else if (openCount < closeCount) {
                openCount = 0;
                closeCount = 0;
            }
        }
        openCount = 0;
        closeCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (S.charAt(i) == '(') {
                openCount++;
            } else {
                closeCount++;
            }
            if (openCount == closeCount)
                maxCount = Integer.max(maxCount, (openCount << 1));
            else if (openCount > closeCount) {
                openCount = 0;
                closeCount = 0;
            }
        }
        return maxCount;
    }
}
