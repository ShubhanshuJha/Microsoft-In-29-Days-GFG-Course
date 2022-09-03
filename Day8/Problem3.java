/* Capacity To Ship Packages Within D Days
Given an array arr[] of N weights. Find the least weight capacity of a boat to ship all weights within D days.
The ith weight has a weight of arr[i]. Each day, we load the boat with weights given by arr[i].We may not load
more weight than the maximum weight capacity of the ship.
Note: You have to load weights on the boat in the given order.

Example 1:	Input:	N = 3,	arr[] = {1, 2, 1},	D = 2
Output:	3
Explanation: We can ship the weights in 2 days in the following way:-
		Day 1- 1,2
		Day 2- 1

Example 2:	Input:	N = 3,	arr[] = {9, 8, 10},	D = 3
Output:	10
Explanation: We can ship the weights in 3 days in the following way:-
		Day 1- 9
		Day 2- 8
		Day 3- 10  */

class Solution {
	// Time: O(N*log(Sum of weights - max(list of weights) + O(N))		Space: O(1)
    static int leastWeightCapacity(int[] arr, int N, int D) {
        int result = 0;
        int low = 0, high = 0;
        for (int i = 0; i < N; i++) {
            low = Integer.max(low, arr[i]);
            high += arr[i];
        }
        while (low <= high) {
            int mid = (low + high) >> 1;
            int sum = 0;
            int count = 1;
            for (int i = 0; i < N; i++) {
                if (arr[i] + sum <= mid) {
                    sum += arr[i];
                } else {
                    sum = arr[i];
                    count++;
                }
            }
            if (count <= D) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
};