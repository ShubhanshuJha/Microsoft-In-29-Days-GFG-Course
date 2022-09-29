/* Shortest Unique prefix for every word
Given an array of words, find all shortest unique prefixes to represent each word in the given array. Assume that no word is prefix of another.

Example 1:	Input:	N = 4,	arr[] = {"zebra", "dog", "duck", "dove"}
Output: z dog du dov
Explanation: 	z => zebra 
				dog => dog 
				duck => du 
				dove => dov 

Example 2:	Input:	N = 3,	arr[] =  {"geeksgeeks", "geeksquiz", "geeksforgeeks"};
Output: geeksg geeksq geeksf
Explanation:	geeksgeeks => geeksg 
				geeksquiz => geeksq 
				geeksforgeeks => geeksf  */

// Trie Approach
class Solution {
    // Time: O(N*length of each word)      Space: O(N*length of each word)
    static String[] findPrefixes(String[] arr, int N) {
        Trie root = new Trie();
        for (String str : arr) {
            root.insert(str);
        }
        String[] res = new String[N];
        for (int i = 0; i < N; i++)
            res[i] = root.search(arr[i]);
        
        return res;
    }
};

class Node {
    final int SIZE = 26;
    Node[] children;
    int count;
    Node() {
        this.children = new Node[SIZE];
        this.count = 0;
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
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new Node();
            }
            curr = curr.children[ch - 'a'];
            curr.count++;
        }
    }
    public String search(String key) {
        Node curr = this.root;
        String res = "";
        for (char ch : key.toCharArray()) {
            curr = curr.children[ch - 'a'];
            res += ch;
            if (curr.count == 1) break;
        }
        return res;
    }
}
