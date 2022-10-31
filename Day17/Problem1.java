/* Vertical Traversal of Binary Tree
Given a Binary Tree, find the vertical traversal of it starting from the leftmost level to the rightmost level.
If there are multiple nodes passing through a vertical line, then they should be printed as they appear in level order traversal of the tree.

Example 1:
Input:     1
         /   \
       2       3
     /   \   /   \
   4      5 6      7
              \      \
               8      9
Output: 4 2 1 5 6 3 8 7 9

Example 2:
Input:    1
        /   \
       2      3
     /  \      \
    4     5      6
Output: 4 2 1 5 3 6  */


class Solution {
    // Time: O(n)        Space: O(n)
    static ArrayList<Integer> verticalOrder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        
        TreeMap<Integer, ArrayList<Integer>> tmap = new TreeMap<>();
        Deque<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        
        while (!q.isEmpty()) {
            Pair curr = q.pollFirst();
            if (!tmap.containsKey(curr.diam))
                tmap.put(curr.diam, new ArrayList<>());
            tmap.get(curr.diam).add(curr.node.data);
            
            if (curr.node.left != null)
                q.offer(new Pair(curr.node.left, curr.diam - 1));
            if (curr.node.right != null)
                q.offer(new Pair(curr.node.right, curr.diam + 1));
        }
        for (ArrayList<Integer> vertOrdered : tmap.values())
            res.addAll(vertOrdered);
        return res;
    }
}

class Pair {
    Node node;
    int diam;
    Pair(Node node, int diam) {
        this.node = node;
        this.diam = diam;
    }
}

