/* Word Break (Trie)
Given a string A and a dictionary of n words B, find out if A can be segmented into a space-separated sequence of dictionary words.
Example 1:
Input:	n = 12,	B = { "i", "like", "sam", "sung", "samsung", "mobile","ice","cream", "icecream", "man", "go", "mango" }, A = "ilike"
Output: 1
Explanation: The string can be segmented as "i like".

Example 2:
Input:	n = 12,	B = { "i", "like", "sam", "sung", "samsung", "mobile","ice","cream", "icecream", "man", "go", "mango" }, A = "ilikesamsung" 
Output: 1
Explanation: The string can be segmented as "i like samsung" or "i like sam sung". */

// Trie Approach
class Solution {
	// Time: O(n*l+|A|2) where l = legth of longest string present in the dictionary and |A| = length of string A
	// Space: O(|A| + k) , where k = sum of length of all strings present in B
    public static int wordBreak(String A, ArrayList<String> B) {
        Trie root = new Trie();
        for (String str : B) {
            root.insert(str);
        }
        return wordBreakUtil(root, A) ? 1 : 0;
    }
    public static boolean wordBreakUtil(Trie root, String key) {
    	int n = key.length();
    	if (n == 0) return true; // base case for the recursion
    	for (int i = 1; i <= n; i++) {
    		if (root.contains(key.substring(0, i)) && wordBreakUtil(root, key.substring(i, n)))
    			return true;
    	}
    	return false;
    }
}

class Node {
    private final int SIZE = 26;
    Node[] children;
    boolean isEndOfWord;
    Node() {
        this.children = new Node[SIZE];
        this.isEndOfWord = false;
    }
}

class Trie {
    private Node root;
    Trie() {
        this.root = new Node();
    }
    public void insert(String key) {
        Node curr = this.root;
        for (char ch : key.toCharArray()) {
            if (curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new Node();
            curr = curr.children[ch - 'a'];
        }
        curr.isEndOfWord = true;
    }
    public boolean contains(String key) {
        Node curr = this.root;
        for (char ch : key.toCharArray()) {
        	if (curr.children[ch - 'a'] == null)
        		return false;
        	curr = curr.children[ch - 'a'];
        }
        return curr != null && curr.isEndOfWord;
    }
}

