/* Find duplicate rows in a binary matrix
Given a boolean matrix of size RxC where each cell contains either 0 or 1, find the row numbers of row (0-based) which already exists or are repeated.

Example 1:
Input:	R = 2, C = 2
		matrix[][] = {{1, 0},
		              {1, 0}}
Output:	1
Explanation: Row 1 is duplicate of Row 0.

Example 2:
Input:	R = 4, C = 3
		matrix[][] = {{ 1, 0, 0},
		              { 1, 0, 0},
		              { 1, 0, 0},
		              { 0, 0, 0}}
Output:	1 2 
Explanation: Row 1 and Row 2 are duplicates of Row 0.  */

class Solution {
	// Time: O(mn)		Space: O(mn)
    public static ArrayList<Integer> repeatedRows(int[][] matrix, int m, int n) {
        ArrayList<Integer> res = new ArrayList<>();
        Trie root = new Trie();
        for (int i = 0; i < m; i++) {
            StringBuilder row = new StringBuilder();
            for (int val : matrix[i]) {
                row.append(val);
            }
            if (root.contains(row.toString()))
                res.add(i);
            else
                root.insert(row.toString());
        }
        
        return res;
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    TrieNode() {
        this.children = new TrieNode[2];
        this.isEnd = false;
    }
}

class Trie {
    private TrieNode root;
    Trie() {
        this.root = new TrieNode();
    }
    public void insert(String key) {
        TrieNode curr = this.root;
        for (char ch : key.toCharArray()) {
            int idx = ch - '0';
            if (curr.children[idx] == null)
                curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }
    public boolean contains(String key) {
        TrieNode curr = this.root;
        for (char ch : key.toCharArray()) {
            int idx = ch - '0';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.isEnd = true;
    }
}


