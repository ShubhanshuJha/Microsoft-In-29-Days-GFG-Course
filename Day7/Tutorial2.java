/* Coin Piles
There are N piles of coins each containing  Ai (1<=i<=N) coins. Find the minimum number of coins to be
removed such that the absolute difference of coins in any two piles is at most K.
Note: You can also remove a pile by removing all the coins of that pile.

Example 1:	Input:	N = 4, K = 0,	arr[] = {2, 2, 2, 2}
Output: 0
Explanation: For any two piles the difference in the number of coins is <=0.
	So no need to remove any coins. 

Example 2:	Input:	N = 6, K = 3,	arr[] = {1, 5, 1, 2, 5, 1} 
Output: 2
Explanation: If we remove one coin each from both the piles containing 5 coins, then for
	any two piles the absolute difference in the number of coins is <=3. */


class Solution {
	// Time: O(N logN)		Space: O(N)
    static int minSteps(int[] A, int N, int K) {
        Arrays.sort(A);
        if (A[N - 1] - A[0] <= K) return 0;
        int[] pref = new int[N];
        pref[0] = A[0];
        for (int i = 1; i < N; i++)
            pref[i] = A[i] + pref[i - 1];
        int minCount = Integer.MAX_VALUE;
        // System.out.println(Arrays.toString(A));
        // System.out.println(Arrays.toString(pref));
        
        for (int i = 0; i < N; i++) {
            int idx = getUpper_Bound(A, i, N - 1, A[i] + K);
            // System.out.print(idx + " ");
            
            int curr = i > 0 ? pref[i - 1] : 0;
            int rightPortionSum = pref[N - 1] - pref[idx - 1];
            int valToSubtract = A[i] + K;
            int sizeOfRemArr = N - idx;
            
            minCount = Integer.min(minCount,
                curr + (rightPortionSum - (valToSubtract * sizeOfRemArr)));
        }
        
        // System.out.println();
        return minCount;
    }
    // BS function to return the index of value wiz. just greater than key
    private static int getUpper_Bound(int[] arr, int l, int r, int key) {
        while (l <= r) {
            int m = (l + r) >> 1;
            if (arr[m] <= key)
                l = m + 1;
            else
                r = m - 1;
        }
        return l;
    }
}
// 9 5
// 5 3 9 3 6 5 2 7 2
// => 2

// 10 4
// 7 1 1 10 9 8 6 2 3 3
// => 10

// 10 7
// 1 7 9 8 10 4 2 10 7 1
// => 4


