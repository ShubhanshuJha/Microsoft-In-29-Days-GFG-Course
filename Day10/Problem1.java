/* Merge K sorted linked lists
Given K sorted linked lists of different sizes. The task is to merge them in such a way that
after merging they will be a single sorted linked list.

Example 1:	Input:	K = 4,	value = {{1,2,3},{4 5},{5 6},{7,8}}
Output: 1 2 3 4 5 5 6 7 8
Explanation: The test case has 4 sorted linked list of size 3, 2, 2, 2
	1st    list     1 -> 2-> 3
	2nd   list      4->5
	3rd    list      5->6
	4th    list      7->8
	The merged list will be 1->2->3->4->5->5->6->7->8.

Example 2:	Input:	K = 3,	value = {{1,3},{4,5,6},{8}}
Output: 1 3 4 5 6 8
Explanation: The test case has 3 sorted linked list of size 2, 3, 1.
	1st list 1 -> 3
	2nd list 4 -> 5 -> 6
	3rd list 8
	The merged list will be 1->3->4->5->6->8. */


// Naive approach
class Solution {
    // Time: O(NK log(NK))		Space: O(NK)
    Node mergeKList(Node[]arr, int K) {
        List<Integer> list = new ArrayList<>();
        List<Integer> l = null;
        
        for (Node i : arr) {
            l = new ArrayList<>();
            while (i != null) {
                l.add(i.data);
                i = i.next;
            }
            l.forEach(v -> list.add(v));
        }
        Collections.sort(list);
        Node h = null;
        Node t = null;
        
        for (int i : list) {
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
-------------------------------------------------------------------
//Optimized approach
class Solution {
    // Time: O(NK)		Space: O(1)
    Node mergeKList(Node[] arr, int K) {
        Node merged = arr[0];
        for (int i = 1; i < K; i++)
            merged = merge(merged, arr[i]);
        return merged;
    }
    Node merge(Node a, Node b) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;
        
        Node dummy = new Node(0);
        dummy.next = null;
        Node curr = dummy.next;
        
        while (a != null && b != null) {
            if (a.data <= b.data) {
                Node newNode = new Node(a.data);
                if (dummy.next == null) {
                    dummy.next = newNode;
                } else {
                    curr.next = newNode;
                }
                curr = newNode;
                a = a.next;
            } else {
                Node newNode = new Node(b.data);
                if (dummy.next == null) {
                    dummy.next = newNode;
                } else {
                    curr.next = newNode;
                }
                curr = newNode;
                b = b.next;
            }
        }
        while (a != null) {
            Node newNode = new Node(a.data);
            if (dummy.next == null) {
                dummy.next = newNode;
            } else {
                curr.next = newNode;
            }
            curr = newNode;
            a = a.next;
        }
        while (b != null) {
            Node newNode = new Node(b.data);
            if (dummy.next == null) {
                dummy.next = newNode;
            } else {
                curr.next = newNode;
            }
            curr = newNode;
            b = b.next;
        }
        return dummy.next;
    }
}
