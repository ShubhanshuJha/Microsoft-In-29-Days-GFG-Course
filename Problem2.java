/* Minimum Swaps to Sort
Given an array of n distinct elements. Find the minimum number of swaps required to sort the array in strictly increasing order.

Example 1:	Input:	nums = {2, 8, 5, 4}
Output: 1
Explaination: swap 8 with 4.

Example 2:	Input:	nums = {10, 19, 6, 3, 5}
Output: 2
Explaination: swap 10 with 3 and swap 19 with 5. */

class Solution {
	// Time: O(n log n)		Space: O(n)
    public int minSwaps(int nums[]) {
        int[] cp = nums.clone(); // Creating a copy of nums
        Arrays.sort(cp); // Sorting it will let me know which elem I need at idx i
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cp[i]) continue;
            int idx = -1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == cp[i]) {
                    idx = j;
                    break;
                }
            }
            if (idx != -1) {
                nums[i] ^= nums[idx];
                nums[idx] ^= nums[i];
                nums[i] ^= nums[idx];
                count++;
            }
        }
        return count;
    }
}