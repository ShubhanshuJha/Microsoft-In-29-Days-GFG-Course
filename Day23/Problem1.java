/* K largest elements
Given an array of N positive integers, print k largest elements from the array.

Example 1:
Input:  N = 5,  k = 2,  arr[] = {12,5,787,1,23}
Output: 787 23
Explanation: First largest element in the array is 787 and the second largest is 23.

Example 2:
Input:  N = 7,  k = 3,  arr[] = {1,23,12,9,30,2,50}
Output: 50 30 23
Explanation: Three Largest element in the array are 50, 30 and 23.  */

class Solution {
    // Time: O(n log k)     Space: O(k)
    public static ArrayList<Integer> kLargest(int[] arr, int n, int k) {
        ArrayList<Integer> lst = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(arr[i]);
            if (pq.size() > k)
                pq.poll();
        }
        
        while (!pq.isEmpty()) {
            lst.add(0, pq.poll());
        }
        return lst;
    }
}
