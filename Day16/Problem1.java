/* Burning Tree
Given a binary tree and a node called target. Find the minimum time required to burn the complete binary tree if the target is set on fire.
It is known that in 1 second all nodes connected to a given node get burned. That is its left child, right child, and parent.

Example 1:
Input:	Target Node = 8
          1
        /   \
      2      3
    /  \      \
   4    5      6
       / \      \
      7   8      9
                   \
                   10
Output: 7
Explanation: If leaf with the value 8 is set on fire. 
		After 1 sec: 5 is set on fire.
		After 2 sec: 2, 7 are set to fire.
		After 3 sec: 4, 1 are set to fire.
		After 4 sec: 3 is set to fire.
		After 5 sec: 6 is set to fire.
		After 6 sec: 9 is set to fire.
		After 7 sec: 10 is set to fire.
		It takes 7s to burn the complete tree.

Example 2:
Input:	Target Node = 10
          1
        /   \
      2      3
    /  \      \
   4    5      7
  /    / 
 8    10

Output: 5  */

// Recursive BFS Approach
class Solution {
    /*class Node {
    	int data;
    	Node left;
    	Node right;
    
    	Node(int data) {
    		this.data = data;
    		left = null;
    		right = null;
    	}
    }*/

    // Time: O(no. of nodes)		Space: O(height of tree)
    public static int minTime(Node root, int target) {
        if (root == null)
            return 0;
        int[] res = {0};
        burn(root, target, 0, res, false);
        return res[0];
    }
    private static int burn(Node root, int target, int level, int[] res, boolean flag) {
        if (root == null)
            return -1;
        if (flag)
            res[0] = Math.max(res[0], level);
        
        if (root.data == target) {
            burn(root.left, target, 1, res, true);
            burn(root.right, target, 1, res, true);
            return 1;
        }
        
        int left = burn(root.left, target, level + 1, res, flag);
        int right = burn(root.right, target, level + 1, res, flag);
        
        if (left != -1) {
            burn(root.right, target, left + 1, res, true);
            return left + 1;
        }
        if (right != -1) {
            burn(root.left, target, right + 1, res, true);
            return right + 1;
        }
        return -1;
    }
}
-----------------------------------------------------------------------------
// Iterative BFS Approach
class Solution {
    /*class Node {
        int data;
        Node left;
        Node right;
    
        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }*/
    // Time: O(N)       Space: O(N)
    public static int minTime(Node root, int target) {
        if (root == null)
            return 0;
        HashMap<Node, Node> parent = new HashMap<>();
        Deque<Node> deq = new LinkedList<>();
        deq.offer(root);
        parent.put(root, null);
        Node targetNode = null;
        while (!deq.isEmpty()) {
            Node curr = deq.pollFirst();
            if (curr.data == target) {
                targetNode = curr;
            }
            if (curr.left != null) {
                deq.offer(curr.left);
                parent.put(curr.left, curr);
            }
            if (curr.right != null) {
                deq.offer(curr.right);
                parent.put(curr.right, curr);
            }
        }
        deq.clear();
        int count = 0;
        HashSet<Node> vis = new HashSet<>();
        deq.offer(targetNode);
        vis.add(targetNode);
        
        while (!deq.isEmpty()) {
            int size = deq.size();
            boolean burnt = false;
            for (int i = 1; i <= size; i++) {
                Node curr = deq.pollFirst();
                if (curr == null) continue;
                if (parent.get(curr) != null && vis.add(parent.get(curr))) {
                    burnt = true;
                    deq.offer(parent.get(curr));
                }
                if (curr.left != null && vis.add(curr.left)) {
                    burnt = true;
                    deq.offer(curr.left);
                }
                if (curr.right != null && vis.add(curr.right)) {
                    burnt = true;
                    deq.offer(curr.right);
                }
            }
            if (burnt) {
                count++;
            }
        }
        return count;
    }
}

