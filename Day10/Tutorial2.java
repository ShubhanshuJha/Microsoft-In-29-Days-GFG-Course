/* Remove all occurences of duplicates in a linked list
Given a sorted linked list, delete all nodes that have duplicate numbers (all occurrences), leaving
only numbers that appear once in the original list. 

Example 1:  Input: N = 8,   Linked List = 23->28->28->35->49->49->53->53
Output: 23 35
Explanation: The duplicate numbers are 28, 49 and 53 which are removed from the list.

Example 2:  Input:  N = 6,  Linked List = 11->11->11->11->75->75
Output: Empty list
Explanation: All the nodes in the linked list have duplicates. Hence the resultant list
        would be empty. */

// Naive approach
class Solution {
    // Time: O(2N)    Space: O(N)
    public static Node removeAllDuplicates(Node head) {
        if (head == null || head.next == null) return head;
        
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(){
            {
                for (Node t = head; t != null; t = t.next)
                    put(t.data, getOrDefault(t.data, 0) + 1);
            }
        };
        Node curr = head;
        Node h = null, t = null;
        while (curr != null) {
            if (hmap.get(curr.data) == 1) {
                Node n = new Node(curr.data);
                if (h == null) {
                    h = n;
                    t = n;
                } else {
                    t.next = n;
                    t = n;
                }
            }
            curr = curr.next;
        }
        return h;
    }
}
--------------------------------------------------------------
// Optimized approach
class Solution {
    // Time: O(N)    Space: O(1)
    public static Node removeAllDuplicates(Node head) {
        if (head == null || head.next == null) return head;
        
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        Node curr = head;
        
        while (curr != null) {
            while (curr.next != null && curr.next.data == prev.next.data)
                curr = curr.next;
            
            if (prev.next == curr)
                prev = prev.next;
            else
                prev.next = curr.next;
            curr = curr.next;
        }
        head = dummy.next;
        return head;
    }
}
