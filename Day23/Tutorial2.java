/* Find median in a stream
Given an input stream of N integers. The task is to insert these numbers into a new stream and find the median of the stream formed
by each insertion of X to the new stream.

Example 1:
Input:	N = 4,	X[] = 5,15,1,3
Output:	5
		10
		5
		4
Explanation:Flow in stream : 5, 15, 1, 3:
		5 goes to stream --> median 5 (5) 
		15 goes to stream --> median 10 (5,15) 
		1 goes to stream --> median 5 (5,15,1) 
		3 goes to stream --> median 4 (5,15,1 3).

Example 2:
Input:	N = 3,	X[] = 5,10,15
Output:	5
		7.5
		10
Explanation:Flow in stream : 5, 10, 15:
		5 goes to stream --> median 5 (5) 
		10 goes to stream --> median 7.5 (5,10) 
		15 goes to stream --> median 10 (5,10,15).

Your Task: You are required to complete the class Solution. It should have 2 data members to represent 2 heaps. 
	It should have the following member functions:
    insertHeap()-> takes x as input and inserts it into the heap, the function should then call balanceHeaps() to balance the new heap.
    balanceHeaps()-> does not take any arguments. It is supposed to balance the two heaps.
    getMedian()-> does not take any arguments. It should return the current median of the stream.  */


// Max Heap and Min Heap Approach
class Solution {
	// Time: O(n log(n))		Space: O(n)
	private static PriorityQueue<Integer> minHeap = new PriorityQueue<>(),
										  maxHeap = new PriorityQueue<>((a, b) -> b - a);
    //Function to insert heap.
    public static void insertHeap(int x) {
    	if (maxHeap.isEmpty() || maxHeap.peek() >= x)
    		maxHeap.offer(x);
    	else
    		minHeap.offer(x);
    	balanceHeaps();
    }
    
    //Function to balance heaps.
    public static void balanceHeaps() {
    	int diff = Math.abs(maxHeap.size() - minHeap.size());
    	if (diff > 1) {
    		if (maxHeap.size() > minHeap.size()) {
    			minHeap.offer(maxHeap.poll());
    		} else {
    			maxHeap.offer(minHeap.poll());
    		}
    	}
    }
    
    //Function to return Median.
    public static double getMedian() {
    	if (maxHeap.size() == minHeap.size()) {
    		return (maxHeap.peek() + minHeap.peek()) / 2d;
    	}
    	if (maxHeap.size() > minHeap.size())
    		return maxHeap.peek();
    	else
    		return minHeap.peek();
    }
}


