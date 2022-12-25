/* First negative integer in every window of size k
Given an array A[] of size N and a positive integer K, find the first negative integer for each and every window
(contiguous subarray) of size K.

Example 1:
Input:	N = 5,	A[] = {-8, 2, 3, -6, 10},	K = 2
Output:	-8 0 -6 -6
Explanation: First negative integer for each window of size k {-8, 2} = -8
	{2, 3} = 0 (does not contain a negative integer), {3, -6} = -6, {-6, 10} = -6.

Example 2:
Input:	N = 8,	A[] = {12, -1, -7, 8, -15, 30, 16, 28},	K = 3
Output:	-1 -1 -7 -15 -15 0  */


// Constant-Space Approach
class Compute {
    // Time: O((N - K) * K)        Space: O(1)
    public long[] printFirstNegativeInteger(long A[], int N, int K) {
        long[] res = new long[N - K + 1];
        int resCounter = 0;
        for (int i = 0; i <= N - K; i++) {
            long minVal = 0;
            int currIdx = i, endIdx = i + K;
            while (currIdx < endIdx) {
                if (A[currIdx] < 0) {
                    minVal = A[currIdx];
                    break;
                }
                currIdx++;
            }
            res[resCounter++] = minVal;
        }
        return res;
    }
}
---------------------------------------------------------------------------------
// Queue with Two Pointer Approach
class Compute {
    // Time: O(N)       Space: O(K)
    public long[] printFirstNegativeInteger(long[] A, int N, int K) {
        long[] res = new long[N - K + 1];
        Queue<Long> q = new LinkedList<>();
        int l = 0, r = 0;
        while (r < K) {
            if (A[r] < 0)
                q.offer(A[r]);
            r++;
        }
        while (r < N) {
            if (q.isEmpty()) {
                res[l] = 0;
            } else {
                res[l] = q.peek();
                if (A[l] == q.peek())
                    q.poll();
            }
            if (A[r] < 0)
                q.offer(A[r]);
            l++;
            r++;
        }
        if (!q.isEmpty())
            res[l] = q.peek();
        else
            res[l] = 0;
        return res;
    }
}
