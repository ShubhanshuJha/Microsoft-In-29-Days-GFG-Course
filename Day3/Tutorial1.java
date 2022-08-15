/*  Count Inversions
Given an array of integers. Find the Inversion Count in the array.
Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.

Example 1:	Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3
Explanation: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

Example 2:	Input: N = 5, arr[] = {2, 3, 4, 5, 6}
Output: 0
Explanation: As the sequence is already sorted so there is no inversion count.

Example 3:	Input: N = 3, arr[] = {10, 10, 10}
Output: 0
Explanation: As all the elements of array are same, so there is no inversion count. */

class Solution {
    // Time: O(n log n) Space: O(n)  [Merge Sorting Approach]
    static long counter;
    static long inversionCount(long arr[], long N) {
        counter = 0L;
        mergeSort(arr, 0, (int)(N - 1));
        return counter;
    }
    static void mergeSort(long[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r - l) >> 1);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }
    static void merge(long[] arr, int l, int m, int r) {
        int n1 = m - l + 1,
            n2 = r - m;
        long[] L = new long[n1],
            R = new long[n2];
        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int i = 0; i < n2; i++)
            R[i] = arr[m + 1 + i];
            
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else {
                counter += (n1 - i);
                arr[k++] = R[j++];
            }
        }
        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }
}