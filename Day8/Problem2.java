/* Median in a row-wise sorted Matrix
Given a row wise sorted matrix of size RxC where R and C are always odd, find the median of the matrix.

Example 1:	Input:	R = 3, C = 3
					M = [[1, 3, 5], 
					     [2, 6, 9], 
					     [3, 6, 9]]
Output: 5
Explanation: Sorting matrix elements gives us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median.

Example 2:	Input:	R = 3, C = 1
					M = [[1], [2], [3]]
Output: 2 */

// Bruteforce approach
class Solution {
	// Time: O(r * c + r * c log(r * c))		Space: O(r * c)
    int median(int[][] matrix, int r, int c) {
        int[] flatMat = new int[r * c];
        for (int i = 0, k = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                flatMat[k++] = matrix[i][j];
            }
        }
        Arrays.sort(flatMat);
        int mid = (r * c) >> 1;
        return flatMat[mid];
    }
}
-----------------------------------------------------------------
// Optimized approach
class Solution {
	// Time: O(log(2000) * r * log(c))	Space: O(1)
    int median(int[][] matrix, int r, int c) {
        int l = 1, h = 2000;
        while (l <= h) {
            int m = (l + h) >> 1;
            int smallerThanMid = 0;
            for (int i = 0; i < r; i++) {
                int left = 0, right = c - 1;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (matrix[i][mid] > m) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                smallerThanMid += left;
            }
            if (smallerThanMid <= (r * c) >> 1) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l;
    }
}

