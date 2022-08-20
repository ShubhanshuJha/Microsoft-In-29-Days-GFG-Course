/* Implement Atoi
Your task  is to implement the function atoi. The function takes a string(str) as argument and converts it to an
integer and returns it.
Note: You are not allowed to use inbuilt function.

Example 1:	Input: str = 123
Output: 123

Example 2:	Input: str = 21a
Output: -1
Explanation: Output is -1 as all characters are not digit only.

Example 3:	Input: str = -12
Output: -12

Example 4:	Input: str = +99
Output: -1 */

class Solution {
	// Time: O(n) 		Space: O(1)
    int atoi(String str) {
        int res = 0;
        boolean isNeg = str.charAt(0) == '-';
        if (isNeg)
            str = str.substring(1, str.length());
        for (char ch : str.toCharArray()) {
            if (ch < '0' || ch > '9') return -1;
            res = res * 10 + (ch - '0');
        }
        if (isNeg)
            res *= -1;
        return res;
    }
}

