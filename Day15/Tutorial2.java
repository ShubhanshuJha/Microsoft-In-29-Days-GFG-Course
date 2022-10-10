/* Restrictive Candy Crush
Given a string s and an integer k, the task is to reduce the string by applying the following operation:
Choose a group of k consecutive identical characters and remove them.
The operation can be performed any number of times until it is no longer possible.

Example 1:
Input:	k = 2
		s = "geeksforgeeks"
Output:	gksforgks
Explanation: Modified String after each step: "geeksforgeeks" -> "gksforgks"

Example 2:
Input:	k = 2
		s = "geegsforgeeeks" 
Output:	sforgeks
Explanation: Modified String after each step: "geegsforgeeeks" -> "ggsforgeks" -> "sforgeks"  */


// Stack Approach
class Solution {
	// Time: O(n)		Space: O(n)
    public static String reduced_String(int k, String s) {
        if (k == 1) return "";
        
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(new Pair(curr, 1));
            } else {
                Pair top = stack.peek();
                if (top.ch == curr) {
                    int freq = top.freq + 1;
                    if (freq == k) {
                        stack.pop();
                    } else {
                        stack.pop();
                        top.freq++;
                        stack.push(top);
                    }
                } else {
                    stack.push(new Pair(curr, 1));
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair top = stack.pop();
            for (int f = 1; f <= top.freq; f++) {
                res.insert(0, top.ch);
            }
        }
        return res.toString();
    }
}
class Pair {
    char ch;
    int freq;
    Pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}
