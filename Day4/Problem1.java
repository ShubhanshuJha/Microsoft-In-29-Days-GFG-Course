/* Top K Frequent Elements in Array - |
Given a non-empty array of integers, find the top k elements which have the highest frequency in the array.
If two numbers have the same frequency then the larger number should be given preference. 
Note: Print the elements according to the frequency count (from highest to lowest) and if the frequency is
equal then larger number will be given preference.

Example 1:	Input:	N = 6,	nums = {1,1,1,2,2,3},	k = 2
Output: {1, 2}

Example 2:	Input:	N = 8,	nums = {1,1,2,2,3,3,3,4},	k = 2
Output: {3, 2}
Explanation: Elements 1 and 2 have the same frequency ie. 2. Therefore, in this case, the answer
		includes the element 2 as 2 > 1. */

class Solution {
	// Time: O(n log n)		Space: O(n)
    public int[] topK(int[] nums, int k) {
    	// Creating a TreeMap that sort the nums as per their freq, and then poll() largest K values from it.
    	HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(){
    		{
    			for (int i : nums) put(i, getOrDefault(i, 0) + 1);
    		}
    	};
    	TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>(new Comparator<Integer>(){
    		public int compare(Integer k1, Integer k2) {
				int comp = hmap.get(k1).compareTo(hmap.get(k2));
				if (comp == 0) return k1 - k2;
				else return comp;
			}
    	});
    	tmap.putAll(hmap);
    	int[] res = new int[k];
    	for (int i = 0; i < k; i++) {
    		res[i] = tmap.pollLastEntry().getKey();
    	}
    	return res;
    }
}