/* Distribute candies in a binary tree
Given a binary tree with N nodes, in which each node value represents number of candies present at that node. In one move, one may choose two adjacent nodes
and move one candy from one node to another (the move may be from parent to child, or from child to parent.) 
The task is to find the number of moves required such that every node have exactly one candy.

Example 1:
Input:       3
           /   \
          0     0 
Output: 2
Explanation: From the root of the tree, we move one candy to its left child, and one candy to its right child.

Example 2:
Input:       0
           /   \
          3     0  
Output: 3
Explanation: From the left child of the root, we move two candies to the root [taking two moves]. Then, we move one candy
            from the root of the tree to the right child.  */

class Solution {
    // Time: O(n)       Space: O(h)
    private static int stepCount;
    public static int distributeCandy(Node root) {
        stepCount = 0;
        distribute(root);
        return stepCount;
    }
    private static int distribute(Node root) {
        if (root == null)
            return 0;
        int left = distribute(root.left);
        int right = distribute(root.right);
        stepCount += Math.abs(left) + Math.abs(right);
        return left + right + root.data - 1;
    }
}
