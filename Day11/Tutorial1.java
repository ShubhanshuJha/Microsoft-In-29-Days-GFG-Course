/* Jump Game
Given an positive integer N and a list of N integers A[]. Each element in the array denotes the
maximum length of jump you can cover. Find out if you can make it to the last index if you start
at the first index of the list.

Example 1:  Input:  N = 6,  A[] = {1, 2, 0, 3, 0, 0} 
Output: 1
Explanation: Jump 1 step from first index to second index. Then jump 2 steps to reach 
    4th index, and now jump 2 steps to reach the end.

Example 2:  Input:  N = 3,  A[] =  {1, 0, 2}
Output: 0
Explanation: You can't reach the end of the array. */


// Naive approach: Recursive approach
class Solution {
    // Time: O(2^N)     Space: O(N) [Stack space]
    static int canReach(int[] A, int N) {
        return check(0, N, A);
    }
    // checking recursively
    private static int check(int idx, int n, int[] arr) {
        if (idx == n - 1) return 1;
        for (int k = idx + 1; k <= idx + arr[idx]; k++)
            if (check(k, n, arr) == 1) return 1;
        
        return 0;
    }
}
------------------------------------------------------------------------------
// Optimized naive approach: Recursive approach with memoization
class Solution {
    private static int[] memo;
    // Time: O(N ^ 2)       Space: O(2N)
    static int canReach(int[] A, int N) {
        memo = new int[N + 1];
        Arrays.fill(memo, -1);
        return check(0, N, A);
    }
    // checking recursively using memoization
    private static int check(int idx, int n, int[] arr) {
        if (idx == n - 1) return memo[idx] = 1;
        if (memo[idx] != -1) return memo[idx];
        
        for (int k = idx + 1; k <= idx + arr[idx]; k++)
            if (check(k, n, arr) == 1) return memo[idx] = 1;
        
        return memo[idx] = 0;
    }
}
------------------------------------------------------------------------------
// Efficient approach
class Solution {
    // Time: O(N)       Space: O(1)
    static int canReach(int[] A, int N) {
        int max = 1;
        for (int i = 1; i < N; i++) {
            if (A[i] == 0 && max > i) continue;
            else if (A[i] == 0 && max <= i) break;
            max = Math.max(max, i + A[i]);
        }
        return max >= N - 1 ? 1 : 0;
    }
};
