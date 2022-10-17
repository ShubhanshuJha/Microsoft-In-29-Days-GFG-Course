/* Cousins of a given node
Given a binary tree and a node, print all cousins of given node in order of their appearance. Note that siblings should not be printed.

Example 1:
Input:  Given node: 5 
             1
           /   \
          2     3
        /   \  /  \
       4    5  6   7

Output: 6 7
Explanation: Nodes 6 and 7 are on the same level as 5 and have different parents.

Example 2:

Input:  Given node: 5
         9
        /
       5
Output: -1
Explanation: There no other nodes than 5 in the same level.  */



class Solution {
    // Time: O(n)       Space: O(n)
    public static ArrayList<Integer> printCousins(Node root, Node node_to_find) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            res.add(-1);
            return res;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        boolean flagged = false;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node curr = q.poll();
                if (curr.left == node_to_find || curr.right == node_to_find)
                    flagged = true;
                else {
                    if (curr.left != null)
                        q.offer(curr.left);
                    if (curr.right != null)
                        q.offer(curr.right);
                }
            }
            if (flagged)
                break;
        }
        if (q.isEmpty()) {
            res.add(-1);
        } else {
            while (!q.isEmpty()) {
                res.add(q.poll().data);
            }
        }
        return res;
    }
}

