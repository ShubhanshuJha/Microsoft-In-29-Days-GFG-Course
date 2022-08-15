/* Insertion Sort
The task is to complete the insert() function which is used to implement Insertion Sort.

Example 1:	Input: N = 5,	arr[] = { 4, 1, 3, 9, 7}
Output:	1 3 4 7 9

Example 2:	Input:	N = 10,	arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
Output:	1 2 3 4 5 6 7 8 9 10 */

class Solution {
	static void insert(int[] arr, int i) {
		if (i == 0) return;
		int temp = arr[i];
		int j = i - 1;
		while (j >= 0 && arr[j] >= temp) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = temp;
	}

	//Function to sort the array using insertion sort algorithm.
	// Time: O(n ^ 2) 	Space: O(1)
	public void insertionSort(int[] arr, int n) {
		for (int i = 1; i < n; i++) {
			insert(arr, i);
		}
	}
}