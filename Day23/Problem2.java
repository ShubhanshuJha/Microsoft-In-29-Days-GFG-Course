/* Nearly sorted
Given an array of n elements, where each element is at most k away from its target position, you need to sort the array optimally.

Example 1:
Input:	n = 7,	k = 3,	arr[] = {6,5,3,2,8,10,9}
Output: 2 3 5 6 8 9 10
Explanation: The sorted array will be 2 3 5 6 8 9 10.

Example 2:
Input:	n = 5,	k = 2,	arr[] = {3,1,4,2,5}
Output: 1 2 3 4 5.  */

// Bruteforce Approach
class Solution {
	// Time: O(n log n)			Space: O(1)
    ArrayList<Integer> nearlySorted(int[] arr, int num, int k) {
        ArrayList<Integer> lst = new ArrayList<Integer>(){{
            for (int i = 0; i < num; i++)
                add(arr[i]);
        }};
        Collections.sort(lst);
        return lst;
    }
}
--------------------------------------------------------------------
// Stream Approach
class Solution {
    ArrayList<Integer> nearlySorted(int[] arr, int num, int k) {
        return Arrays.stream(arr).boxed().sorted().collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
}
--------------------------------------------------------------------
// PriorityQueue Stream Approach
class Solution {
    ArrayList<Integer> nearlySorted(int[] arr, int num, int k) {
        return new PriorityQueue<Integer>(){{
            for (int i = 0; i < num; i++) offer(arr[i]);
        }}.stream().sorted().collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
}
--------------------------------------------------------------------
// Efficient Solution
class Solution {
    // Time: O(n log k)         Space: O(k)
    ArrayList<Integer> nearlySorted(int[] arr, int num, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < num; i++) {
            pq.offer(arr[i]);
            if (pq.size() > k) {
                res.add(pq.poll());
            }
        }
        while (!pq.isEmpty()) res.add(pq.poll());
        return res;
    }
}
