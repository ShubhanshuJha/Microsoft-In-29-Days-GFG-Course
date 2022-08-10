// Three Sum Closest
/* Given an array, Arr of N numbers, and another number target, find three integers in the array such that
the sum is closest to the target. Return the sum of the three integers.
Note: If there are multiple solutions, print the maximum one.

Example 1: Input: 	N = 6, target = 2
					A[] = {-7,9,8,3,1,1}
Output: 2
Explanation: There is one triplet with sum 2 in the array. Triplet elements are -7,8,1 whose sum is 2.

Example 2: Input: 	N = 4, target = 13
					A[] = {5,2,7,5}
Output: 14
Explanation: There is one triplet with sum 12 and other with sum 14 in the array. Triplet elements are
			5, 2, 5 and 2, 7, 5 respectively. Since abs(13-12) == abs(13-14) maximum triplet sum will be
			preferred i.e 14. */

class Solution {
    // Bruteforce approach: Time=O(n ^ 3)	Space=O(1)
//     static int threeSumClosest(int[] array, int target) {
//         int n = array.length;
//         int res = Integer.MAX_VALUE;
//         Arrays.sort(array);
//         for (int i = 0; i < n - 2; i++) {
//             for (int j = i + 1; j < n - 1; j++) {
//                 for (int k = j + 1; k < n; k++) {
//                     int currSum = array[i] + array[j] + array[k];
                    
//                     if (Math.abs(target - currSum) <= Math.abs(target - res))
//                         res = currSum;
//                 }
//             }
//         }
//         return res;
// 	}

   // Bruteforce approach: Time=O(n ^ 2) [DFS Approach]	Space=O(n) [Stack space]
    static int diff, res;
    static int threeSumClosest(int[] array, int target) {
        int n = array.length;
        res = Integer.MAX_VALUE;
        diff = Integer.MAX_VALUE;
        dfs(0, 0, 0, array, target);
        return res;
	}
	static void dfs(int idx, int count, int sum, int[] arr, int x) {
	    if (diff == 0) return;
	    if (count == 3) {
	        if (sum == x) {
	            res = sum;
	            diff = 0;
	            return;
	        }
	        int d = Math.abs(x - sum);
	        if (diff == d) {
	            res = Integer.max(res, sum);
	        } else if (d < diff) {
	            diff = d;
	            res = sum;
	        }
	        return;
	    }
	    if (idx == arr.length) return;
	    dfs(idx + 1, count + 1, sum + arr[idx], arr, x);
	    dfs(idx + 1, count, sum, arr, x);
	}
} 