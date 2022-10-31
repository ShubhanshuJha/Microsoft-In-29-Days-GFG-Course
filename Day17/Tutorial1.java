/* Largest BST
Given a binary tree. Find the size of its largest subtree that is a Binary Search Tree.
Note: Here Size is equal to the number of nodes in the subtree.

Example 1:
Input:
        1
      /   \
     4     4
   /   \
  6     8
Output: 1
Explanation: There's no sub-tree with size greater than 1 which forms a BST. All the leaf Nodes are the BSTs with size equal to 1.

Example 2:
Input: 6 6 3 N 2 9 3 N 8 8 2
            6
        /       \
       6         3
        \      /   \
         2    9     3
          \  /  \
          8 8    2 
Output: 2
Explanation: The following sub-tree is a BST of size 2:   2
                                                        /    \ 
                                                       N      8  */


class Solution {
    // Return the size of the largest sub-tree which is also a BST
    private static int res;
    // Time: O(n)       Space: O(h)
    static int largestBst(Node root) {
        res = 0;
        find(root);
        return res;
    }
    // return: Pair(count, isBST, max_val, min_val)
    private static Pair find(Node root) {
        if (root == null) {
            return new Pair();
        }
        
        Pair left = find(root.left);
        Pair right = find(root.right);
        
        Pair p = new Pair();
        p.max = Math.max(root.data, right.max);
        p.min = Math.min(root.data, left.min);
        
        if (left.isBST && right.isBST && left.max < root.data && right.min > root.data)
            p.isBST = true;
        else
            p.isBST = false;
        
        p.count = left.count + right.count + 1;
        
        if (p.isBST) {
            res = Math.max(res, p.count);
        }
        return p;
    }
}

class Pair {
    int count;
    boolean isBST;
    int max, min;
    Pair() {
        this.count = 0;
        this.isBST = true;
        this.max = Integer.MIN_VALUE;
        this.min = Integer.MAX_VALUE;
    }
}
