/* Intersection Point in Y Shapped Linked Lists
Given two singly linked lists of size N and M, write a program to get the point where two linked lists
intersect each other.

Example 1:
Input:	LinkList1 = 3->6->9->common
		LinkList2 = 10->common
		common = 15->30->NULL
Output: 15

Example 2:
Input: 	Linked List 1 = 4->1->common
		Linked List 2 = 5->6->1->common
		common = 8->4->5->NULL
Output: 8
Explanation: 
4              5
|              |
1              6
 \             /
  8   -----  1 
   |
   4
   |
   5
   |
  NULL       */

// Naive approach
class Intersect {
	// Time: O(N + M)		Space: O(N)
	int intersectPoint(Node head1, Node head2) {
	    if (head1 == null || head2 == null) return -1;
	    HashSet<Node> hset = new HashSet<>();
	    for (Node tmp = head1; tmp != null; tmp = tmp.next)
	        hset.add(tmp);
	    for (Node tmp = head2; tmp != null; tmp = tmp.next) {
	        if (hset.contains(tmp)) return tmp.data;
	    }
	    return -1;
	}
}
------------------------------------------------
