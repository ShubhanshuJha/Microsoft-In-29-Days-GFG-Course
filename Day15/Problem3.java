/* Count the Reversals
Given a string S consisting of only opening and closing curly brackets '{' and '}', find out the minimum number of reversals
required to convert the string into a balanced expression.
A reversal means changing '{' to '}' or vice-versa.

Example 1:	Input:	S = "}{{}}{{{"
Output:	3
Explanation: One way to balance is: "{{{}}{}}". There is no balanced sequence that can be formed in lesser reversals.

Example 2:	Input:	S = "{{}{{{}{{}}{{"
Output:	-1
Explanation: There's no way we can balance this sequence of braces. */


// Stack Approach
class Sol {
	// Time: O(n)		Space: O(n)
    int countRev(String s) {
        int n = s.length();
        if ((n & 1) == 1) return -1;
        Stack<Character> stack = new Stack<>();
        int openCount = 0, closeCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '{') {
                stack.push('{');
                openCount++;
            } else {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    openCount--;
                    stack.pop();
                } else {
                    closeCount++;
                }
            }
        }
        if ((openCount & 1) == 0)
            openCount >>= 1;
        else
            openCount = (openCount >> 1) + 1;
        if ((closeCount & 1) == 0)
            closeCount >>= 1;
        else
            closeCount = (closeCount >> 1) + 1;
        return openCount + closeCount;
    }
}
-----------------------------------------------------------------------------------
// Efficient Approach
class Sol {
	// Time: O(n)		Space: O(1)
    int countRev(String s) {
        int n = s.length();
        if ((n & 1) == 1) return -1;
        int openCount = 0, closeCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '{') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--;
                } else {
                    closeCount++;
                }
            }
        }
        if ((openCount & 1) == 0)
            openCount >>= 1;
        else
            openCount = (openCount >> 1) + 1;
        if ((closeCount & 1) == 0)
            closeCount >>= 1;
        else
            closeCount = (closeCount >> 1) + 1;
        return openCount + closeCount;
    }
}

