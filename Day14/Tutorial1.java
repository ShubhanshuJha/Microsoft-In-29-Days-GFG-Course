/* The Celebrity Problem
A celebrity is a person who is known to all but does not know anyone at a party. If you go to a party of N people, find if there is a
celebrity in the party or not. A square NxN matrix M[][] is used to represent people at the party such that if an element of row i and
column j  is set to 1 it means ith person knows jth person. Here M[i][i] will always be 0.
Note: Follow 0 based indexing.
Follow Up: Can you optimize it to O(N)

Example 1:
Input:  N = 3,  M[][] = {{0 1 0},
                        {0 0 0}, 
                        {0 1 0}}
Output: 1
Explanation: 0th and 2nd person both know 1. Therefore, 1 is the celebrity. 

Example 2:
Input:  N = 2,  M[][] = {{0 1},
                        {1 0}}
Output: -1
Explanation: The two people at the party both know each other. None of them is a celebrity.  */

// Naive Approach
class Solution {
    // Time: O(n ^ 2)       Space: O(n)
    int celebrity(int[][] M, int n) {
        int[] knowing = new int[n];
        for (int i = 0; i < n; i++) {
            knowing[i] = Arrays.stream(M[i]).sum();
        }
        for (int i = 0; i < n; i++) {
            if (knowing[i] == 0) {
                boolean isKnownToAll = true;
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (M[j][i] == 0) {
                        isKnownToAll = false;
                        break;
                    }
                }
                if (isKnownToAll)
                    return i;
            }
        }
        return -1;
    }
}
---------------------------------------------------------------
// Optimized Naive Approach
class Solution {
    // Time: O(n ^ 2)       Space: O(1)
    int celebrity(int[][] M, int n) {
        int[] knowing = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = Arrays.stream(M[i]).sum();
            if (sum == 0) {
                boolean isKnownToAll = true;
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (M[j][i] == 0) {
                        isKnownToAll = false;
                        break;
                    }
                }
                if (isKnownToAll)
                    return i;
            }
        }
        return -1;
    }
}

---------------------------------------------------------------
class Solution {
    // Time: O(n)       Space: O(n)
    int celebrity(int[][] M, int n) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++)
            st.push(i);
        while (st.size() > 1) {
            int a = st.pop();
            int b = !st.isEmpty() ? st.pop() : a;
            if (M[a][b] == 1)
                st.push(b);
            else
                st.push(a);
        }
        int res = st.pop();
        for (int i = 0; i < n; i++) {
            if (i == res) continue;
            if (M[res][i] == 1 || M[i][res] == 0)
                return -1;
        }
        return res;
    }
}
---------------------------------------------------------------

