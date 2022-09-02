/* Left most and right most index
Given a sorted array with possibly duplicate elements. The task is to find indexes of first
and last occurrences of an element X in the given array.
Note: If the element is not present in the array return {-1,-1} as pair. 

Example 1:	Input:	N = 9, 	v[] = {1, 3, 5, 5, 5, 5, 67, 123, 125},	 X = 5
Output:	2 5
Explanation: Index of first occurrence of 5 is 2 and index of last occurrence of 5 is 5.

Example 2:	Input:	N = 9,	v[] = {1, 3, 5, 5, 5, 5, 7, 123, 125},	X = 7
Output: 6 6 */

class Solution {
    // Time: O(log(n) + log(n))		Space: O(1)
    public pair indexes(long[] v, long x) {
        int n = v.length;
        pair p = new pair(-1, -1);
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (v[m] == x) {
                p.first = m;
                r = m - 1;
            } else if (v[m] > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        l = 0; r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (v[m] == x) {
                p.second = m;
                l = m + 1;
            } else if (v[m] > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return p;
    }
}
