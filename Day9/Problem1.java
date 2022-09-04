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
        Node temp = head;
        String s = "";
        while(temp.next != null) {
            s += (String.valueOf(temp.value) + " ");
            temp = temp.next;
        }
        String[] str = s.split(" ");
        Node end;
        for(int i = str.length - 1; i >= 0; i--) {
            temp.value = Integer.parseInt(str[i]);
            temp.next = null;
            if(i == str.length - 1)
                end = temp;
            temp = temp.next;
        }
        return end;
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
