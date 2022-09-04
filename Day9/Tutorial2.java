/* Finding middle element in a linked list
Given a singly linked list of N nodes. The task is to find the middle of the linked list. For example, if the linked
list is 1-> 2->3->4->5, then the middle node of the list is 3. If there are two middle nodes(in case, when N is even),
print the second middle element. For example, if the linked list given is 1->2->3->4->5->6, then the middle node of the list is 4.

Example 1:	Input:	LinkedList: 1->2->3->4->5
Output: 3 
Explanation: Middle of linked list is 3.

Example 2:	Input:	LinkedList: 2->4->6->7->5->1
Output: 7 
Explanation: Middle of linked list is 7. */

// Naive approach
class Solution {
	// Time: O(N + N/2)		Space: O(1)
    int getMiddle(Node head) {
        int count = 0;
        Node temp = head;
        while(temp != null) {
            temp = temp.next;
            count++;
        }
        if((count & 1) == 0) count++;
        count = (count + 1) >> 1;
        for (temp = head; temp.next != null && count-- > 1; temp = temp.next);
        return temp.data;
    }
}
--------------------------------------------------------------
// Optimized approach
class Solution {
	// Time: O(N)	Space: O(1)
    int getMiddle(Node head) {
        Node small = head,
            fast = head;
        while (fast != null && fast.next != null) {
            small = small.next;
            fast = fast.next.next;
        }
        return small.data;
    }
}
