/* Reverse each word in a given string
Given a String. Reverse each word in it where the words are separated by dots.

Example 1:  Input:  S = "i.like.this.program.very.much"
Output: i.ekil.siht.margorp.yrev.hcum
Explanation: The words are reversed as follows:
            "i" -> "i","like"->"ekil",
            "this"->"siht","program" -> "margorp",
            "very" -> "yrev","much" -> "hcum".

Example 2:  Input:  S = "pqr.mno"
Output: rqp.onm
Explanation: The words are reversed asfollows:
            "pqr" -> "rqp" ,
            "mno" -> "onm"  */

// Stack Approach
class Solution {
    // Time: O(n)       Space: O(n)
    String reverseWords(String S) {
        StringBuilder rev = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != '.') {
                stack.push(S.charAt(i));
            } else {
                while (!stack.isEmpty())
                    rev.append(stack.pop());
                rev.append('.');
            }
        }
        while (!stack.isEmpty())
            rev.append(stack.pop());
        return rev.toString();
    }
}

---------------------------------------------------------------
// Constant Space Approach
class Solution {
    // Time: O(n)       Space: O(1)
    String reverseWords(String S) {
        char[] rev = S.toCharArray();
        int n = S.length();
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && S.charAt(j) != '.') j++;
            reverse(rev, i, j - 1);
            i = j;
        }
        return String.valueOf(rev);
    }
    private void reverse(char[] str, int l, int r) {
        while (l < r) {
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;
            r--;
        }
    }
}