// Longest subarray with sum divisible by K
/*Given an array containing N integers and a positive integer K, find the length of the longest sub array
with sum of the elements divisible by the given value K.

Example 1: Input: A[] = {2, 7, 6, 1, 4, 5}	K = 3
Output: 4
Explanation: The subarray is {7, 6, 1, 4} with sum 18, which is divisible by 3.

Example 2: Input: A[] = {-2, 2, -5, 12, -11, -1, 7}	 K = 3
Output: 5
Explanation: The subarray is {2,-5,12,-11,-1} with sum -3, which is divisible by 3. */

// Solution:
class Solution {
    // int longSubarrWthSumDivByK(int a[], int n, int k) {
    //     HashMap<Integer, Integer> hmap = new HashMap<>(); // O(n) space
    //     int[] pSum = new int[n]; // O(n) space
    //     pSum[0] = a[0];
    //     for (int i = 1; i < n; i++) {
    //         pSum[i] = a[i] + pSum[i - 1];
    //     }
    //     int max = 0; // don't initialize with Integer.MIN_VALUE bcz this won't handle the case for 0 elems div by k
    //     for (int i = 0; i < n; i++) {
    //         int rem = pSum[i] % k;
    //         if (rem == 0) {
    //             max = Integer.max(max, i + 1);
    //         } else {
    //             if (rem < 0)
    //                 rem += k; // handling the possibility of -ve nos.
    //             if (hmap.containsKey(rem))
    //                 max = Integer.max(max, i - hmap.get(rem));
    //             else
    //                 hmap.put(rem, i);
    //         }
    //     }
    //     return max;
    // }
    
    // Space Optimized approach: Removed the extra O(n) space
    int longSubarrWthSumDivByK(int a[], int n, int k) {
        HashMap<Integer, Integer> hmap = new HashMap<>(); // O(n) space
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            int rem = sum % k;
            if (rem == 0) {
                max = Integer.max(max, i + 1);
            } else {
                if (rem < 0)
                    rem += k; // handling the possibility of -ve nos.
                if (hmap.containsKey(rem))
                    max = Integer.max(max, i - hmap.get(rem));
                else
                    hmap.put(rem, i);
            }
        }
        return max;
    }
}