// Zero Sum Subarrays
/* You are given an array arr[] of size n. Find the total count of sub-arrays having their sum equal to 0.

Example 1: Input: 	n = 6
					arr[] = {0,0,5,5,0,0}
Output: 6
Explanation: The 6 subarrays are [0], [0], [0], [0], [0,0], and [0,0].

Example 2: Input: 	n = 10
					arr[] = {6,-1,-3,4,-2,2,4,6,-12,-7}
Output: 4
Explanation: The 4 subarrays are [-1 -3 4], [-2 2], [2 4 6 -12], and [-1 -3 4 -2 2] */

class Solution {
    // This is the function to count subarrays with sum equal to 0.
    // Efficient approach:  Time=O(n)
    public static long findSubarray(long[] arr, int n) {
        // Kadanes + HashMap concept is used
        HashMap<Long, Integer> hmap = new HashMap<>();
        long count = 0L;
        long currSum = 0L;
        hmap.put(currSum, 1);
        for (long l : arr) {
            currSum += l;
            count += hmap.getOrDefault(currSum, 0);
            hmap.put(currSum, hmap.getOrDefault(currSum, 0) + 1);
        }
        return count;
    }
}
