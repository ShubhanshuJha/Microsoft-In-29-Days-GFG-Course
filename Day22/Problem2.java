/* Maximize The Array
Given two integer arrays Arr1 and Arr2 of size N. Use the greatest elements from the given arrays to create a new array of size N
such that it consists of only unique elements and the sum of all its elements is maximum.
The created elements should contain the elements of Arr2 followed by elements of Arr1 in order of their appearance.
Note: The input array will be given in such way, that every time it is possible to make a new arr by maintaing the following conditions.

Example 1:
Input:	N = 5
		Arr1 = {7, 4, 8, 0, 1}
		Arr2 = {9, 7, 2, 3, 6}
Output: 9 7 6 4 8
Explanation: 9, 7, 6 are from 2nd array and 4, 8 from 1st array.


Example 2:
Input:	N = 4
		Arr1 = {6, 7, 5, 3}
		Arr2 = {5, 6, 2, 9} 
Output: 5 6 9 7 
Explanation: 5, 6, 9 are from 2nd array and 7 from 1st array.  */


class Solution {
	// Time: O(n logn)		Space: O(n)
    public ArrayList<Integer> maximizeArray(int[] arr1, int[] arr2, int n) {
    	ArrayList<Integer> maxArr = new ArrayList<>();
    	TreeSet<Pair> tset = new TreeSet<>((a, b) -> b.val - a.val);
    	HashSet<Integer> vis = new HashSet<>();
    	for (int i = 0; i < n; i++) {
    	    if (vis.add(arr2[i]))
    	        tset.add(new Pair(arr2[i], 2, i));
    	}
    	for (int i = 0; i < n; i++) {
    	    if (vis.add(arr1[i]))
    	        tset.add(new Pair(arr1[i], 1, i));
    	}
    	while (tset.size() > n) {
    		tset.pollLast();
    	}
    	PriorityQueue<Pair> pq1 = new PriorityQueue<>((a, b) -> a.idx - b.idx),
    						pq2 = new PriorityQueue<>((a, b) -> a.idx - b.idx);
    	for (Pair p : tset) {
    		if (p.ar == 1) {
    			pq1.offer(p);
    		} else {
    			pq2.offer(p);
    		}
    	}
    	while (!pq2.isEmpty())
    		maxArr.add(pq2.poll().val);
    	while (!pq1.isEmpty())
    		maxArr.add(pq1.poll().val);
    	return maxArr;
    }
}
class Pair {
	int val, ar, idx;
	Pair(int val, int ar, int idx) {
		this.val = val;
		this.ar = ar;
		this.idx = idx;
	}
}
