/* Count Number of SubTrees having given Sum
Given a binary tree and an integer X. Your task is to complete the function countSubtreesWithSumX() that returns the
count of the number of subtress having total node’s data sum equal to the value X.

Example: For the tree given below:
              5
            /    \
        -10     3
        /    \    /  \
      9     8  -4 7

Subtree with sum 7:
              -10
            /      \
           9        8
and one node 7.

Example 1:
Input:	X = 7
       5
    /    \
  -10     3
 /   \   /  \
 9   8 -4    7

Output: 2
Explanation: Subtrees with sum 7 are [9, 8, -10] and [7] (refer the example in the problem description).

Example 2:
Input:	X = 5
    1
  /  \
 2    3

Output: 0
Explanation: No subtree has sum equal to 5. */


class Tree {
	// Time: O(n)		Space: O(height of tree i.e. log n)
    private int count;
    public int countSubtreesWithSumX(Node root, int X) {
        count = 0;
        traverse(root, X);
        return count;
    }
    private int traverse(Node root, int X) {
        if (root == null) {
            return 0;
        }
        int lSum = traverse(root.left, X);
        int rSum = traverse(root.right, X);
        int sum = lSum + root.data + rSum;
        
        if (sum == X)
            count++;
        return sum;
    }
}

