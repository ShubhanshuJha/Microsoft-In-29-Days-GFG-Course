/* Boundary Traversal of binary tree
Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order: 

Left boundary nodes: defined as the path from the root to the left-most node ie- the leaf node you could reach when you always travel preferring the
    left subtree over the right subtree. 
Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
Reverse right boundary nodes: defined as the path from the right-most node to the root. The right-most node is the leaf node you could reach when you always
    travel preferring the right subtree over the left subtree. Exclude the root from this as it was already included in the traversal of left boundary nodes.

Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary. 

Example 1:
Input:
        1 
      /   \
     2     3  
    / \   / \ 
   4   5 6   7
      / \
     8   9
   
Output: 1 2 4 8 9 6 7 3

Example 2:
Input:
            1
           /
          2
        /  \
       4    9
     /  \    \
    6    5    3
             /  \
            7     8

Output: 1 2 4 6 5 7 8  */


class Solution {
    // Time: O(N)        Space: O(H)
    ArrayList<Integer> boundary(Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        res.add(node.data);
        if (node.left == null && node.right == null) {
            return res;
        }
        
        leftNodes(node.left, res);
        leafNodes(node, res);
        rightNodes(node.right, res);
        return res;
    }

    void leftNodes(Node root, ArrayList<Integer> res) {
        if (root == null)
            return;
        if (root.left != null) {
            res.add(root.data);
            leftNodes(root.left, res);
        } else if (root.right != null) {
            res.add(root.data);
            leftNodes(root.right, res);
        }
    }
    
    void leafNodes(Node root, ArrayList<Integer> res) {
        if (root == null)
            return;
        leafNodes(root.left, res);
        if (root.left == null && root.right == null) {
            res.add(root.data);
        }
        leafNodes(root.right, res);
    }
    
    void rightNodes(Node root, ArrayList<Integer> res) {
        if (root == null)
            return;
        if (root.right != null) {
            rightNodes(root.right, res);
            res.add(root.data);
        } else if (root.left != null) {
            rightNodes(root.left, res);
            res.add(root.data);
        }
    }
}
