/* Minimize the Heights II
Given an array arr[] denoting heights of N towers and a positive integer K.
For each tower, you must perform exactly one of the following operations exactly once.
    Increase the height of the tower by K
    Decrease the height of the tower by K
Find out the minimum possible difference between the height of the shortest and tallest towers after you have modified each tower.

You can find a slight modification of the problem here.
Note: It is compulsory to increase or decrease the height by K for each tower. After the operation, the resultant array
should not contain any negative integers.

Example 1:	Input:	K = 2, N = 4,	Arr[] = {1, 5, 8, 10}
Output:	5
Explanation: The array can be modified as {3, 3, 6, 8}. The difference between the largest and the smallest is 8-3 = 5.

Example 2:	Input:	K = 3, N = 5,	Arr[] = {3, 9, 12, 16, 20}
Output:	11
Explanation: The array can be modified as {6, 12, 9, 13, 17}. The difference between the largest and the smallest is 17-6 = 11. */


class Solution {
	// Time: O(n log n)		Space: O(1)
    int getMinDiff(int[] arr, int n, int k) {
        Arrays.sort(arr);
		int minDiff = arr[n - 1] - arr[0];

		int smallest = arr[0] + k;
		int largest = arr[n - 1] - k;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n - 1; i++) {
        	min = Integer.min(smallest, arr[i + 1] - k);
        	max = Integer.max(largest, arr[i] + k);
        	if (min < 0) continue;
        	minDiff = Integer.min(minDiff, max - min);
        }
        return minDiff;
    }
}


