/* Check if subtree
Given two binary trees with head reference as T and S having at most N nodes. The task is to check if S is present as subtree in T.
A subtree of a tree T1 is a tree T2 consisting of a node in T1 and all of its descendants in T1.

Example 1:
Input:
	T:      1          S:   3
	      /   \            /
	     2     3          4
	   /  \    /
	  N    N  4
Output: 1 
Explanation: S is present in T

Example 2:
Input:
	T:      26         S:   26
	       /   \           /  \
	     10     N        10    N
	   /    \           /  \
	   20    30        20  30
	  /  \            /  \
	 40   60         40  60
Output: 1 
Explanation: S and T are both same. Hence, it can be said that S is a subtree of T.  */


class Solution {
	// Time: O(n)		Space: O(n)
    public static boolean isSubtree(Node T, Node S) {
        if (S == null)
            return true;
        if (T == null)
            return false;
        
        if (isIdentical(T, S))
            return true;
        return isSubtree(T.left, S) || isSubtree(T.right, S);
    }
    private static boolean isIdentical(Node A, Node B) {
        if (A == null && B == null)
            return true;
        if (A == null || B == null)
            return false;
        
        if (A.data == B.data) {
            return isIdentical(A.left, B.left) && isIdentical(A.right, B.right);
        }
        return false;
    }
}

