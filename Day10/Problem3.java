/* Length of longest palindrome in linked list
Given a linked list, the task is to complete the function maxPalindrome() which returns an integer denoting the
length of the longest palindrome list that exist in the given linked list.

Constraints:
1 <= T <= 100
1 <= N <= 100

Input:
	2
	7
	2 3 7 3 2 12 24
	5
	12 4 4 3 14
Output:
	5
	2
Explanation: Testcase 1: 2 -> 3 -> 7 -> 3 -> 2 is the linked list whose nodes leads to a palindrome as 2 3 7 3 2.  */

// Naive approach
class GfG {
	// Time: O(n ^ 3)		Space: O(n)
	public static int maxPalindrome(Node head) {
	    if (head == null) return 0;
	    if (head.next == null) return 1;
	    List<Integer> lst = new ArrayList<>();
	    for (Node itr = head; itr != null; itr = itr.next)
	        lst.add(itr.data);
	    int n = lst.size();
	    int maxLen = 0;
	    for (int i = 0; i < n; i++) {
	        int currLen = 1;
	        for (int j = i + 1; j < n; j++) {
	            int l = i, r = j;
	            boolean poss = true;
	            while (l <= r) {
	                if (lst.get(l) != lst.get(r)) {
	                    poss = false;
	                    break;
	                }
	                l++; r--;
	            }
	            if (poss) {
	                currLen = Integer.max(currLen, j - i + 1);
	            }
	        }
	        maxLen = Integer.max(maxLen, currLen);
	    }
	    return maxLen;
	}
}
-----------------------------------------------------------------------
// LinkedList based approach
class GfG {
	// Time: O(n ^ 3)		Space: O(n)
	public static int maxPalindrome(Node head) {
	    if (head == null) return 0;
	    if (head.next == null) return 1;

	    int maxLen = 0;
	    int lP = 0;
	    for (Node i = head; i != null; i = i.next) {
	        Node rev = null;
	        int currLen = 1;
	        int rP = lP;
	        
	        for (Node j = i; j != null; j = j.next) {
	            Node tmp = new Node(j.data);
	            tmp.next = rev;
	            rev = tmp;
	            if (isPalin(i, rev)) {
	                currLen = Integer.max(currLen, rP - lP + 1);
	            }
	            rP++;
	        }
	        maxLen = Integer.max(maxLen, currLen);
	        lP++;
	    }
	    return maxLen;
	}
	private static boolean isPalin(Node a, Node b) {
	    if (a == null || b == null) return true;
	    if (a.next == null || b.next == null) return a.data == b.data;
	    // as b ⊆ a, so len(b) <= len(a) => terminating condition is: when itr at b is null
	    for (Node t1 = a, t2 = b; t1 != null && t2 != null; t1 = t1.next, t2 = t2.next)
	        if (t1.data != t2.data) return false;
	    return true;
	}
}
-----------------------------------------------------------------------
// Optimized approach
class GfG {
	// Time: O(n ^ 2)		Space: O(1)
	public static int maxPalindrome(Node head) {
	    if (head == null) return 0;
	    if (head.next == null) return 1;
	    int maxLen = 0;
	    int lP = 0;
	    Node rev = new Node(head.data);
	    head = head.next;
	    
	    while (head != null) {
	        Node next = head.next;
	        head.next = rev;
	        rev = head;
	        head = next;
	        
	        maxLen = Integer.max(maxLen, (countPalin(rev, head) << 1));
	        if (head != null) {
	            maxLen = Integer.max(maxLen, (countPalin(rev, head.next) << 1) + 1);
	        }
	    }
	    return maxLen;
	}
	private static int countPalin(Node a, Node b) {
	    if (a == null || b == null) return 0;
	    if (a.next == null || b.next == null) return a.data == b.data ? 1 : 0;
	    int count = 0;
	    
	    while (a != null && b != null) {
	        if (a.data == b.data) {
	            count++;
	            a = a.next;
	            b = b.next;
	        } else {
	            break;
	        }
	    }
	    return count;
	}
}

