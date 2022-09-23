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

// HashMap Approach: Bruteforce
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
---------------------------------------------------------------------------------------
// Trie Approach
class Solution {
    // Time: O(n * max(wordLen))        Space: O(n * max(wordLen))
    public String mostFrequentWord(String[] arr, int n) {
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            root.add(arr[i], i);
        }
        return arr[root.getFreqWordIdx()];
    }
}

class TrieNode {
    final int ALPHABET_SIZE = 26;
    TrieNode[] children;
    
    int count;
    int first_appear_idx;
    TrieNode() {
        this.children = new TrieNode[ALPHABET_SIZE]; // for small letter alphabets
        for (int i = 0; i < ALPHABET_SIZE; i++) this.children[i] = null;
        
        this.count = 0;
        this.first_appear_idx = -1;
    }
}

public class Trie {
    private TrieNode root;
    private int resIdx, resCount;
    
    Trie() {
        this.root = new TrieNode();
        this.resIdx = -1;
        this.resCount = 0;
    }
    public void add(String key, int idx) {
        TrieNode curr = this.root;
        for (char ch : key.toCharArray()) {
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode(); // adding a new node to the char
            }
            curr = curr.children[ch - 'a'];
        }
        curr.count++;
        if (curr.first_appear_idx == -1)
            curr.first_appear_idx = idx;
        if (resIdx == -1) {
            resCount = curr.count;
            resIdx = curr.first_appear_idx;
        } else if (curr.count > resCount || (curr.count == resCount && curr.first_appear_idx > resIdx)) {
            resCount = curr.count;
            resIdx = curr.first_appear_idx;
        }
    }
    public int getFreqWordIdx() {
        return this.resIdx;
    }
}
---------------------------------------------------------------------------------------
// HashMap Approach: Efficient
class Solution {
    // Time: O(n)     Space: O(n)
    public String mostFrequentWord(String[] arr, int n) {
        HashMap<String, Integer> hmap = new HashMap<>();
        int idx = 0, count = 0;
        for (int i = 0; i < n; i++) {
            hmap.put(arr[i], hmap.getOrDefault(arr[i], 0) + 1);
            count = Integer.max(count, hmap.get(arr[i]));
        }
        for (int i = 0; i < n; i++) {
            if (hmap.get(arr[i]) == count) {
                idx = i;
                hmap.put(arr[i], hmap.get(arr[i]) - 1);
            }
        }
        return arr[idx];
    }
}
