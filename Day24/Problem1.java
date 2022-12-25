/* Missing number in array
Given an array of size N-1 such that it only contains distinct integers in the range of 1 to N. Find the missing element.

Example 1:
Input:  N = 5,  A[] = {1,2,3,5}
Output: 4

Example 2:
Input:  N = 10, A[] = {6,1,2,8,3,4,7,10,5}
Output: 9  */


// Set Approach
class Solution {
    // Time: O(n)       Space: O(n)
    int MissingNumber(int[] array, int n) {
        HashSet<Integer> hset = new HashSet<>();
        for (int val : array)
            hset.add(val);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (!hset.contains(i)) {
                res = i;
                break;
            }
        }
        return res;
    }
}
----------------------------------------------------------------
// Math Approach: [sum of numbers upto N - sum(array)]
class Solution {
    // Time: O(n)       Space: O(1)
    int MissingNumber(int[] array, int n) {
        int sum = Arrays.stream(array).sum();
        int sumUptoN = (n * (n + 1)) >> 1;
        return sumUptoN - sum;
    }
}
----------------------------------------------------------------
// XOR Approach: xor(1 to n) ^ xor(array)
class Solution {
    // Time: O(n)       Space: O(1)
    int MissingNumber(int[] array, int n) {
        int res = 0;
        for (int i = 1; i <= n; i++)
            res ^= i;
        for (int val : array)
            res ^= val;
        return res;
    }
}
