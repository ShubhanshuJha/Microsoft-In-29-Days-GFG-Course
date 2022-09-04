/* Reverse a linked list
Given a linked list of N nodes. The task is to reverse this list.

Example 1:	Input:	LinkedList: 1->2->3->4->5->6
Output: 6 5 4 3 2 1
Explanation: After reversing the list, elements are 6->5->4->3->2->1.

Example 2:	Input:	LinkedList: 2->7->8->9->10
Output: 10 9 8 7 2
Explanation: After reversing the list, elements are 10->9->8->7->2. */

// Naive approach
class Solution {
	// Time: O(N)		Space: O(N)
    Node reverseList(Node head) {
        ArrayList<Integer> vals = new ArrayList<>();
        for (Node temp = head; temp != null; temp = temp.next)
            vals.add(temp.data);
        Collections.reverse(vals);
        Node h = null;
        Node t = null;
        for (int i : vals) {
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
--------------------------------------------------------------------
// Optimized approach
class Solution {
    // Time: O(N)      Space: O(1)
    Node reverseList(Node head) {
        Node curr = head;
        Node rev = null;
        while (curr != null) {
            Node newNode = curr.next;
            curr.next = rev;
            rev = curr;
            curr = newNode;
        }
        return rev;
    }
}
