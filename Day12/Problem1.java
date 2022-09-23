/* Most frequent word in an array of strings
Given an array arr containing N words consisting of lowercase characters. Your task is to find the most frequent word in the array.
If multiple words have same frequency, then print the word whose first occurence occurs last in the array as compared to the other
strings with same frequency.

Example 1:	Input:	N = 3,	arr[] = {geeks,for,geeks}
Output: geeks
Explanation: "geeks" comes 2 times.

Example 2:	Input:	N = 2,	arr[] = {hello,world}
Output: world
Explanation: "hello" and "world" both have 1 frequency. We print world as its first occurence comes last in the input array. */


class Solution {
	// Time: O(n log n)		Space: O(n)
    public String mostFrequentWord(String[] arr, int n) {
        HashMap<String, Pair> hmap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            if (!hmap.containsKey(arr[i])) {
                hmap.put(arr[i], new Pair(i));
            } else {
                hmap.get(arr[i]).count++;
            }
        }
        Arrays.sort(arr, (a, b) -> {
            if (hmap.get(a).count == hmap.get(b).count)
                return hmap.get(b).fIdx - hmap.get(a).fIdx;
            return hmap.get(b).count - hmap.get(a).count;
        });
        return arr[0];
    }

}

class Pair {
    int count, fIdx;
    Pair(int idx) {
        this.count = 1;
        this.fIdx = idx;
    }
}


