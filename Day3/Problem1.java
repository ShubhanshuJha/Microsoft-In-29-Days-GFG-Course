/* Sort array after converting elements to their squares
Given an array A consisting of both positive and negative integers of size N which are sorted. Your task is
to sort the square of the numbers of the array.

Example 1:	Input: N = 6, A[] = {-6, -3, -1, 2, 4, 5}
Output: 1 4 9 16 25 36
Explanation: Square of the given array is - [36, 9, 1, 4,16, 25]. When this array is sorted,
		it gives - [1, 4, 9, 16, 25, 36].

Example 2:	Input: N = 5, 	A[]  = {-5, -4, -2, 0, 1}
Output: 0 1 4 16 25 */

class Solution {
	// Time: O(n log n) Space: O(n)
	public int[] sortSquares(int[] arr,int n){
	    int[] res = new int[n];
	    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(){
	        {
	            for (int i : arr) offer(i * i);
	        }
	    };
	    for (int i = 0; i < n; i++) {
	        res[i] = pq.poll();
	    }
	    return res;
	}
}