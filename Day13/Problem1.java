/* Minimum XOR value pair
Given an array of integers of size N find minimum xor of any 2 elements.

Example 1:	Input:	N = 3,	arr[] = {9,5,3}
Output: 6
Explanation: There are 3 pairs - 9^5 = 12, 5^3 = 6, 9^3 = 10. Therefore output is 6. */


// Naive Approach
class Solution {
	// Time: O(N ^ 2)		Space: O(1)
    static int minxorpair(int N, int[] arr) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                res = Integer.min(res, arr[i] ^ arr[j]);
            }
        }
        return res;
    }
}
-----------------------------------------------------------------------
// Optimized Approach
class Solution {
    // Time: O(N logN)      Space: O(1)
    static int minxorpair(int N, int[] arr) {
        Arrays.sort(arr);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            res = Integer.min(res, arr[i] ^ arr[i + 1]);
        }
        return res;
    }
}
-----------------------------------------------------------------------
// Trie Approach
class Solution {
    // Time: O(N)       Space: O(N)
    static int minxorpair(int N, int[] arr) {
        if (N == 1) return arr[0];
        int res = Integer.MAX_VALUE;
        Trie root = new Trie();
        for (int val : arr) {
            res = Integer.min(res, root.getXOR(val));
            root.insert(val);
        }
        return res;
    }
}

class Node {
    final int SIZE = 2;
    Node[] children;
    int value;
    Node() {
        this.children = new Node[SIZE];
        this.value = 0;
    }
}

class Trie {
    private Node root;
    Trie() {
        this.root = new Node();
    }
    public void insert(int val) {
        Node curr = this.root;
        for (int i = 31; i >= 0; i--) {
            int bit = (val >> i) & 1;
            if (curr.children[bit] == null)
                curr.children[bit] = new Node();
            curr = curr.children[bit];
        }
        curr.value = val;
    }
    public int getXOR(int value) {
        Node curr = this.root;
        if (curr == null) return Integer.MAX_VALUE;
        for (int i = 31; i >= 0; i--) {
            int bit = (value >> i) & 1;
            if (curr.children[bit] != null)
                curr = curr.children[bit];
            else if (curr.children[1 - bit] != null)
                curr = curr.children[1 - bit];
            else
                return Integer.MAX_VALUE;
        }
        return value ^ curr.value;
    }
}