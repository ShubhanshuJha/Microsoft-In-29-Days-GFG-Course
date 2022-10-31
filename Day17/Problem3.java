/* Connect Nodes at Same Level
Given a binary tree, connect the nodes that are at same level. You'll be given an addition nextRight pointer for the same.

Initially, all the nextRight pointers point to garbage values. Your function should set these pointers to point next right for each node.
       10                          10 ------> NULL
      / \                       /      \
     3   5       =>     	  3 ------> 5 --------> NULL
    / \     \                /  \        \
   4   1   2          		4 --> 1 -----> 2 -------> NULL


Example 1:
Input:  3
	   /  \
	  1    2
Output:	3 1 2
		1 3 2
Explanation: The connected tree is     3 ------> NULL
								     /    \
								    1-----> 2 ------ NULL

Example 2:
Input:	 10
	    /   \
	   20   30
	  /  \
	 40  60
Output:	10 20 30 40 60
		40 20 60 10 30
Explanation: The connected tree is   10 ----------> NULL
							       /     \
							     20 ------> 30 -------> NULL
							  /    \
							 40 ----> 60 ----------> NULL  */


class Solution {
	// Time: O(n)		Space: O(n)
    public void connect(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node prev = null;
            for (int i = 1; i <= size; i++) {
                Node curr = q.poll();
                if (prev == null) {
                    prev = curr;
                    prev.nextRight = null;
                } else {
                    prev.nextRight = curr;
                    prev = curr;
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
        }
    }
}
