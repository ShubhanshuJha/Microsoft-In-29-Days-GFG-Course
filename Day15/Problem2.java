/* Decode the string
An encoded string (s) is given, and the task is to decode it. The encoding pattern is that the occurrence of the string is given
at the starting of the string and each string is enclosed by square brackets.

Example 1:	Input: s = 1[b]
Output: b
Explaination: 'b' is present only one time.

Example 2:	Input: s = 3[b2[ca]]
Output: bcacabcacabcaca
Explaination: 2[ca] means 'ca' is repeated twice which is 'caca' which concatenated with 'b' becomes 'bcaca'.
		This string repeated thrice becomes the output.  */

class Solution {
	// Time: O(N)		Space: O(N)
    public static String decodedString(String s) {
        int n = s.length();
        Stack<String> stack = new Stack<>();
        String str = "";
        String freq = "0";
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                freq += s.charAt(i);
            } else if (s.charAt(i) == '[') {
                stack.push(str);
                stack.push(freq);
                str = "";
                freq = "0";
            } else if (s.charAt(i) == ']') {
                int currFreq = Integer.parseInt(stack.pop());
                String prev = stack.pop();
                String repeat = "";
                for (int f = 1; f <= currFreq; f++)
                    repeat += str;
                str = prev + repeat;
            } else {
                str += s.charAt(i);
            }
        }
        return str;
    }
}
