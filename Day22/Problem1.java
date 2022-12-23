/* Merge K sorted linked lists
Given K sorted linked lists of different sizes. The task is to merge them in such a way that after merging they will be a single sorted linked list.

Example 1:
Input:	K = 4,	value = {{1,2,3},{4 5},{5 6},{7,8}}
Output: 1 2 3 4 5 5 6 7 8
Explanation: The test case has 4 sorted linked list of size 3, 2, 2, 2
		1st    list      1->2->3
		2nd    list      4->5
		3rd    list      5->6
		4th    list      7->8
	The merged list will be 1->2->3->4->5->5->6->7->8.

Example 2:
Input:	K = 3,	value = {{1,3},{4,5,6},{8}}
Output: 1 3 4 5 6 8
Explanation: The test case has 3 sorted linked list of size 2, 3, 1.
		1st list 1->3
		2nd list 4 ->5->6
		3rd list 8
	The merged list will be 1->3->4->5->6->8.  */


// Merge Function of Merge Sort Approach
class Solution {
	// Time: O(K*n^2)		Space: O(n)
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
        
        Node dummy = new Node(0); // dummyList max. size = n
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
-----------------------------------------------------------------------
// Efficient Approach
class Solution {
	// Time: O(nK LogK)		Space: O(K)
    Node mergeKList(Node[] arr, int K) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.node.data - b.node.data);
        Node head = null, tail = null;
        for (int i = 0; i < K; i++)
            pq.offer(new Pair(arr[i], i));
        
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            arr[curr.idx] = arr[curr.idx].next;
            if (arr[curr.idx] != null) {
                pq.offer(new Pair(arr[curr.idx], curr.idx));
            }
            
            Node newNode = new Node(curr.node.data);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }
}
class Pair {
    Node node;
    int idx;
    Pair(Node node, int idx) {
        this.node = node;
        this.idx = idx;
    }
}

