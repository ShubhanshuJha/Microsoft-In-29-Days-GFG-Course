/* Kth element in Matrix
Given a N x N matrix, where every row and column is sorted in non-decreasing order. Find the kth smallest element in the matrix.
Example 1:

Input:	N = 4,	K = 3
		mat[][] =  {{16, 28, 60, 64},
                   {22, 41, 63, 91},
                   {27, 50, 87, 93},
                   {36, 78, 87, 94 }}
Output: 27
Explanation: 27 is the 3rd smallest element.

Example 2:
Input:	N = 4,	K = 7
		mat[][] =  {{10, 20, 30, 40}
                   {15, 25, 35, 45}
                   {24, 29, 37, 48}
                   {32, 33, 39, 50}}
Output: 30
Explanation: 30 is the 7th smallest element.  */


// Naive Solution: PriorityQueue Approach
class Solution {
	// Time: O(n * n * log(k))		Space: O(k)
	public static int kthSmallest(int[][] mat, int n, int k) {
	    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	    for (int[] row : mat) { // runs n time
	        for (int val : row) { // runs n * n time
	            maxHeap.offer(val); // insertion/deletion takes log time, and max. size of PQ = k, n^2*log(k) time
	            if (maxHeap.size() > k) maxHeap.poll();
	        }
	    }
	    return maxHeap.poll();
    }
}
----------------------------------------------------------------------
// Efficient Solution: Binary Search Approach
class Solution {
	// Time: O(log(10000)*n*log(n))=> O(n log n)		Space: O(1)
    public static int kthSmallest(int[][] mat, int n, int k) {
	    int lowestVal = mat[0][0], highVal = mat[n - 1][n - 1];

        while (lowestVal <= highVal) { // log(max(mat)) => log(10000)
            int midVal = (lowestVal + highVal) >> 1;
            int countOfLowValsThanMidVal = 0;

            for (int i = 0; i < n; i++) {
                int left = 0, right = n - 1;
                while (left <= right) { // log(n)
                    int mid = left + ((right - left) >> 1);
                    if (mat[i][mid] <= midVal)
                        left = mid + 1;
                    else
                        right = mid - 1;
                }
                countOfLowValsThanMidVal += left;
            }
            if (countOfLowValsThanMidVal < k) lowestVal = midVal + 1;
            else highVal = midVal - 1;
        }
        return lowestVal;
    }
}


