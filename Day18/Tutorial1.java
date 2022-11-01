/* Height of Binary Tree
Given a binary tree, find its height.

Example 1:
Input:
     1
    /  \
   2    3
Output: 2

Example 2:
Input:
  2
   \
    1
   /
 3
Output: 3  */


class Solution {
    // Time: O(N)       Space: O(N) [worst case: if Tree is skewed]
    int height(Node node) {
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }
}

