/* Sort an array according to the other
Given two integer arrays A1[] and A2[] of size N and M respectively. Sort the first array A1[ ] such that
all the relative positions of the elements in the first array are the same as the elements in the second
array A2[].
Note: If elements are repeated in the second array, consider their first occurance only.

Example 1:	Input: 	N = 11 M = 4
					A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}
					A2[] = {2, 1, 8, 3}
Output: 2 2 1 1 8 8 3 5 6 7 9
Explanation: Array elements of A1[] are sorted according to A2[]. So 2 comes first then 1 comes,
	then comes 8, then finally 3 comes, now we append remaining elements in sorted order.

Example 2:	Input:	N = 11 M = 4
					A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}
					A2[] = {99, 22, 444, 56}
Output: 1 1 2 2 3 5 6 7 8 8 9
Explanation: No A1[] elements are in A2[] so we cannot sort A1[] according to A2[].
	Hence we sort the elements in non-decreasing order. */

class Solution{
    // A1[] : the input array-1
    // N : size of the array A1[]
    // A2[] : the input array-2
    // M : size of the array A2[]
    
    // Time: O(n log n) [Inserting n elems in TreeMap [each insert() = (log n)]]	Space: O(n)
    public static int[] sortA1ByA2(int A1[], int N, int A2[], int M) {
        TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>(){
            {
                for (int i : A1) put(i, getOrDefault(i, 0) + 1);
            }
        };
        int j = 0;
        for (int i = 0; i < M; i++) {
            if (!tmap.containsKey(A2[i])) continue;
            int freq = tmap.get(A2[i]);
            while (freq-- > 0) {
                A1[j++] = A2[i];
            }
            tmap.remove(A2[i]);
        }
        while (!tmap.isEmpty()) {
            Map.Entry<Integer, Integer> e = tmap.pollFirstEntry();
            int val = e.getKey(),
                freq = e.getValue();
            while (freq-- > 0) {
                A1[j++] = val;
            }
        }
        return A1;
    }
}