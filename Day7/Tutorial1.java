/* Distribute N candies among K people
Given N candies and K people. In the first turn, the first person gets 1 candy, the second gets 2 candies, and so on
till K people. In the next turn, the first person gets K+1 candies, the second person gets K+2 candies and so on.
If the number of candies is less than the required number of candies at every turn, then the person receives the
remaining number of candies. Find the total number of candies every person has at the end.

Example 1:	Input:	N = 7, K = 4
Output:	1 2 3 1
Explanation: At the first turn, the fourth person has to be given 4 candies, but there is only 1 left,
		hence he takes only one. 

Example 2:	Input:	N = 10, K = 3
Output:	5 2 3
Explanation: At the second turn first one receives 4 and then we have no more candies left. */

class Solution {
    // Time: O(Number of turns + K)      Space: O(K)
    static Long[] distributeCandies(int N, int K) {
        Long[] arr = new Long[K];
        Arrays.fill(arr, 0L);
        int j = 0;
        while (N > 0) {
            for (int i = 0; i < K; i++) {
                j++;
                if (N <= 0) break;
                if (j < N)
                    arr[i] += Long.valueOf(j);
                else
                    arr[i] += Long.valueOf(N);
                N -= j;
            }
        }
        return arr;
    }
}

---------------------------------------------------------------------------------

class Solution {
    // Time: O(logN + K)      Space: O(K)
    static Long[] distributeCandies(int N, int K) {
        Long[] arr = new Long[K];
        Arrays.fill(arr, 0L);
        
        long low = 0L, high = N;
        long count = 0L, n = Long.valueOf(N);
        while (low <= high) {
            long mid = (high + low) >> 1;
            long sum = (mid * (mid + 1)) >> 1;
            
            if (sum <= n) {
                count = mid / K; // no. of turns
                low = mid + 1; // right half
            } else {
                high = mid - 1; // left half
            }
        }
        // Last term of the last complete series
        long last = count * K;
        n -= ((last * (last + 1)) >> 1);
        
        int i = 0;
        // First term of the incomplete series
        Long term = Long.valueOf((count * K) + 1);
        while (n > 0L) {
            if (term <= n) {
                arr[i++] = term;
                n -= term;
                term++;
            } else {
                arr[i++] += n;
                n = 0L;
            }
        }
        // Counting total candies
        for (i = 0; i < K; i++)
            arr[i] += Long.valueOf((count * (i + 1)) + ((K * (count * (count - 1))) >> 1));
        
        return arr;
    }
}

