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
	// Time: O(N * M)		Space: O(1)
	int intersectPoint(Node head1, Node head2) {
	    if (head1 == null || head2 == null) return -1;
	    for (Node tmp1 = head1; tmp1 != null; tmp1 = tmp1.next)
		    for (Node tmp2 = head2; tmp2 != null; tmp2 = tmp2.next) {
		        if (tmp1 == tmp2) return tmp2.data;
		    }
	    return -1;
	}
}
------------------------------------------------
// Optimized approach
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
// Optimal approach1
class Intersect {
	// Time: O(N) + O(M) + O(abs(M-N)) + O(min(M, N))	Space: O(1)
	//		=> O(2 max(M, N))
	int intersectPoint(Node head1, Node head2) {
	    if (head1 == null || head2 == null) return -1;
	    int countOf1 = 0, countOf2 = 0;
	    for (Node tmp = head1; tmp != null; tmp = tmp.next)
	        countOf1++;
	    for (Node tmp = head2; tmp != null; tmp = tmp.next)
	        countOf2++;
	    Node h1 = head1, h2 = head2;
	    if (countOf1 > countOf2) {
	    	while (countOf1-- > countOf2)
	    		h1 = h1.next;
	    } else if (countOf1 < countOf2) {
	    	while (countOf2-- > countOf1)
	    		h2 = h2.next;
	    }
	    while (h1 != null) {
	    	if (h1 == h2) return h1.data;
	    	h1 = h1.next;
	    	h2 = h2.next;
	    }
	    return -1;
	}
}
------------------------------------------------
// Optimal approach2: same time complexity but optimized code structure
class Intersect {
	// Time: O(2 max(M, N))		Space: O(1)
	int intersectPoint(Node head1, Node head2) {
	    if (head1 == null || head2 == null) return -1;
	    Node h1 = head1, h2 = head2;
	    while (h1 != h2) {
	    	h1 = h1 == null ? head2 : h1.next;
	    	h2 = h2 == null ? head1	: h2.next;
	    }
	    return h1 == null ? -1 : h1.data;
	}
}

