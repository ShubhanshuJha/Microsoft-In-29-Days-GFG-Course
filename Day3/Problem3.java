/* Merge Sort
Given an array arr[], its starting position l and its ending position r. Sort the array using merge sort
algorithm.

Example 1:	Input: N = 5,	arr[] = {4 1 3 9 7}
Output:	1 3 4 7 9

Example 2:	Input: N = 10,	arr[] = {10 9 8 7 6 5 4 3 2 1}
Output: 1 2 3 4 5 6 7 8 9 10 */

class Solution {
	// Time: O(n log n)		Space: O(n)
    void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1,
            n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i];
        }
        int a = 0, b = 0;
        int x = l;
        while (a < n1 && b < n2) {
            if (L[a] < R[b])
                arr[x++] = L[a++];
            else
                arr[x++] = R[b++];
        }
        while (a < n1) {
            arr[x++] = L[a++];
        }
        while (b < n2) {
            arr[x++] = R[b++];
        }
    }
    void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int mid = l + ((r - l) >> 1);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }
}