/* Check if Linked List is Palindrome
Given a singly linked list of size N of integers. The task is to check if the given linked list is palindrome or not.

Example 1:	Input:	N = 3, value[] = {1,2,1}
Output: 1
Explanation: The given linked list is 1 2 1, which is a palindrome and hence, the output is 1.

Example 2:	Input:	N = 4, value[] = {1,2,3,4}
Output: 0
Explanation: The given linked list is 1 2 3 4, which is not a palindrome and hence, the output is 0. */


// Bruteforce approach
class Solution {
	// Time: O(N)		Space: O(N)
    boolean isPalindrome(Node head) {
        LinkedList<Integer> list = new LinkedList<>();
        for (Node t = head; t != null; t = t.next)
            list.add(t.data);
        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (list.get(i) != list.get(j)) return false;
            i++; j--;
        }
        return true;
    }    
}
-------------------------------------------------------------------
// Optimized approach
class Solution {
	// Time: O(N)		Space: O(1)
    boolean isPalindrome(Node head) {
    	if (head == null || head.next == null) return true;
    	Node slow = head, fast = head;
    	// Finding the middle node
    	while (fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	Node prev = null,
    		curr = slow;
    	// Reversing the list from the middle node
    	while (curr != null) {
    		Node newNode = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = newNode;
    	}
    	curr = head;
    	// Checking if we're getting the same data from the start and end
    	while (prev != null) {
    		if (curr.data != prev.data) return false;
    		curr = curr.next;
    		prev = prev.next;
    	}
    	return true;
    }
}

