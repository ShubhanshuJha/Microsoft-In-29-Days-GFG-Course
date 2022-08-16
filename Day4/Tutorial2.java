/* Count the number of possible triangles
Given an unsorted array arr[] of n positive integers. Find the number of triangles that can be formed with
three different array elements as lengths of three sides of triangles. 

Example 1:	Input: n = 3,	arr[] = {3, 5, 4}
Output: 1
Explanation: A triangle is possible with all the elements 5, 3 and 4.

Example 2:	Input: n = 5,	arr[] = {6, 4, 9, 7, 8}
Output: 10
Explanation: There are 10 triangles possible  with the given elements like (6,4,9), (6,7,8),... */

class Solution {
    //Function to count the number of possible triangles.
    // Time: O(n ^ 2)		Space: (1)
    static int findNumberOfTriangles(int arr[], int n) {
    	/* Approach: Sum of any two sides > third side
    If the array is sorted, then, by fixing the currLargest elem as the third side reduces complexity,
    as- arr[i] + arr[j] > arr[currLargest], then it is possible to create triangle with arr values inside
    range(i, j). Otherwise, we need larger sides to form a triangle. */
        Arrays.sort(arr);
        int count = 0;
        for (int k = n - 1; k >= 0; k--) { // O(n) running time
            int i = 0, j = k - 1;
            while (i < j) { // at most O(n) running time
                if (arr[i] + arr[j] > arr[k]) {
                    count += j - i;
                    j--;
                } else i++;
            }
        }
        return count;
    }
}
