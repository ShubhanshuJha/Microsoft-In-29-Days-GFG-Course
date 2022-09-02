/* The Painter's Partition Problem-II
Dilpreet wants to paint his dog's home that has n boards with different lengths. The length of ith board
is given by arr[i] where arr[] is an array of n integers. He hired k painters for this work and each
painter takes 1 unit time to paint 1 unit of the board. 
The problem is to find the minimum time to get this job done if all painters start together with the
constraint that any painter will only paint continuous boards, say boards numbered {2,3,4} or only board
{1} or nothing but not boards {2,4,5}.


Example 1:	Input:	n = 5,	k = 3
					arr[] = {5,10,30,20,15}
Output: 35
Explanation: The most optimal way will be:
		Painter 1 allocation : {5,10}
		Painter 2 allocation : {30}
		Painter 3 allocation : {20,15}
		Job will be done when all painters finish i.e. at time = max(5+10, 30, 20+15) = 35

Example 2:	Input:	n = 4,	k = 2
					arr[] = {10,20,30,40}
Output: 60
Explanation: The most optimal way to paint:
		Painter 1 allocation : {10,20,30}
		Painter 2 allocation : {40}
		Job will be complete at time = 60 */

class Solution {
	// Time: O(log(sum(arr)) n)		Space: O(1)
    static long minTime(int[] arr, int n, int k) {
        // One painter can paint all the board in sum(arr) time
        // N painters can paint all the board in max(arr) time
        // So, for this problem, lower bound = max(arr) and upper bound = sum(arr)
        long low = 0L, high = 0L;
        for (int i : arr) {
            low = Long.max(low, i);
            high += i;
        }
        long result = 0L;
        while (low <= high) {
            long mid = (low + high) >> 1;
            int pCounter = 1;
            long sum = 0L;
            for (int i = 0; i < n; i++) {
                if (sum + arr[i] <= mid) {
                    sum += arr[i];
                } else {
                    // new painter is needed
                    pCounter++;
                    sum = arr[i];
                }
            }
            if (pCounter <= k) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}