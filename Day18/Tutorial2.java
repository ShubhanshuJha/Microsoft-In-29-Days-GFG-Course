/* Maximum Path Sum between 2 Leaf Nodes
Given a binary tree in which each node element contains a number. Find the maximum possible path sum from one leaf node to another leaf node.
Note: Here Leaf node is a node which is connected to exactly one different node.

Example 1:
Input:      
           3                               
         /    \                          
       4       5                     
      /  \      
    -10   4                          
Output: 16
Explanation: Maximum Sum lies between leaf node 4 and 5. 4 + 4 + 3 + 5 = 16.

Example 2:
Input:    
            -15                               
         /      \                          
        5         6                      
      /  \       / \
    -8    1     3   9
   /  \              \
  2   -3              0
                     / \
                    4  -1
                       /
                     10  
Output:  27
Explanation: The maximum possible sum from one leaf node to another is (3 + 6 + 9 + 0 + -1 + 10 = 27).  */


class Solution {
    // Time: O(N)       Space: O(N)
    private int ans;
    int maxPathSum(Node root) {
        ans = Integer.MIN_VALUE;
        int hSum = find(root);
        if (root.left == null || root.right == null)
            ans = Math.max(ans, hSum);
        return ans;
    }
    int find(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.data;
        
        int left = find(root.left);
        int right = find(root.right);
        
        if (root.left != null && root.right != null) {
            ans = Math.max(ans, left + right + root.data);
            return Math.max(left, right) + root.data;
        }
        if (root.left == null)
            return root.data + right;
        else
            return root.data + left;
    }
}

