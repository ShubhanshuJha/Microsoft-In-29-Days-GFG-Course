/* Reverse a Linked List in groups of given size
Given a linked list of size N. The task is to reverse every k nodes (where k is an input to the
function) in the linked list. If the number of nodes is not a multiple of k then left-out nodes,
in the end, should be considered as a group and must be reversed (See Example 2 for clarification).

Example 1:	Input:	LinkedList: 1->2->2->4->5->6->7->8,	K = 4
Output: 4 2 2 1 8 7 6 5 
Explanation: The first 4 elements 1,2,2,4 are reversed first and then the next 4 elements 5,6,7,8.
	Hence, the resultant linked list is 4->2->2->1->8->7->6->5.

Example 2:	Input:	LinkedList: 1->2->3->4->5,	K = 3
Output: 3 2 1 5 4 
Explanation: The first 3 elements are 1,2,3 are reversed first and then elements 4,5 are reversed.
	Hence, the resultant linked list is 3->2->1->5->4. */


// Naive approach
class Solution {
	// Time: O(N)		Space: O(2N)
    public static Node reverse(Node node, int k) {
        if (node == null || node.next == null) return node;
        List<Integer> lst = new ArrayList<>();
        for (Node tmp = node; tmp != null; tmp = tmp.next)
            lst.add(tmp.data);
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < lst.size(); i += k) {
            int j = i + k - 1 < lst.size() ? i + k - 1 : lst.size() - 1;
            for (int l = j; l >= i; l--) {
                res.add(lst.get(l));
            }
        }
        Node h = null, t = null;
        for (int i : res) {
            Node n = new Node(i);
            if (h == null) {
                h = n;
                t = n;
            } else {
                t.next = n;
                t = n;
            }
        }
        return h;
    }
}
