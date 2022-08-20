/* Triplet Sum in Array
Given an array arr of size n and an integer X. Find if there's a triplet in the array which sums up to the given
integer X.

Example 1:	Input: n = 6, X = 13, arr[] = [1 4 45 6 10 8]
Output: 1
Explanation: The triplet {1, 4, 8} in the array sums up to 13.

Example 2:	Input: n = 5, X = 10, arr[] = [1 2 4 3 6]
Output: 1
Explanation: The triplet {1, 3, 6} in the array sums up to 10. */

class Solution {
    //Function to find if there exists a triplet in the array A[] which sums up to X.
    // Time: O(n ^ 2)		Space: O(n)
    public static boolean find3Numbers(int A[], int n, int X) {
        int[] req = new int[n];
        for (int i = 0; i < n; i++)
            req[i] = X - A[i];
        for (int i = 0; i < n; i++) {
            HashSet<Integer> hset = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (hset.contains(req[i] - A[j])) return true;
                hset.add(A[j]);
            }
        }
        return false;
    }

    // Space Optimized Approach
    // Time: O(n ^ 2)		Space: O(1)
    public static boolean find3Numbers(int A[], int n, int X) {
        Arrays.sort(A);
        for (int i = 0; i < n; i++) {
            int a = 0;
            int b = n - 1;
            while (a < b) {
                if (a == i) {
                    a++;
                    continue;
                }
                if (b == i) {
                    b--;
                    continue;
                }
                int sum = A[i] + A[a] + A[b];
                if (sum == X) return true;
                if (sum < X) a++;
                else b--;
            }
        }
        return false;
    }
}

