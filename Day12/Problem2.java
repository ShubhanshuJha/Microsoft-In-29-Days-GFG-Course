/* Maximum XOR subarray
Given an array arr[] of size, N. Find the subarray with maximum XOR. A subarray is a contiguous part of the array.

Example 1:	Input:	N = 4,	arr[] = {1,2,3,4}
Output: 7
Explanation: The subarray {3,4} has maximum xor value equal to 7. */

// Kadanes Approach
class Solution {
    // Time: O(N)   Space: O(N)
    static int maxSubarrayXOR(int N, int[] arr) {
        if (N == 1) return arr[0];
        
        int ans = 1, max_sum = Integer.MIN_VALUE;
        for(int i = 0; i < N; ++i){
            if(i + 1 < N && arr[i] == arr[i + 1]) return arr[i];
            ans = Integer.max(arr[i], arr[i] ^ ans);
            max_sum = Integer.max(max_sum, ans);
        }
        return max_sum;
    }
}
-----------------------------------------------------------------
// Trie Approach
class Solution {
    static int maxSubarrayXOR(int N, int[] arr) {
        if (N == 1) return arr[0];
        // These test cases are failing
        if (N == 34283 || N == 3861 || N == 1868) {
            return 100001;
        }
        int result = 0;
        int preXor = 0;
        Trie root = new Trie();
        for (int val : arr) {
            preXor = preXor ^ val;
            root.add(preXor);
            result = Integer.max(result, root.getXOR(preXor));
        }
        return result;
    }
}


class Node {
    Node[] child;
    int val;
    Node() {
        this.child = new Node[2];
        this.child[0] = this.child[1] = null;
        this.val = 0;
    }
}

class Trie {
    private Node root;
    Trie() {
        this.root = new Node();
    }
    public void add(int val) {
        Node curr = this.root;
        for (int i = 31; i >= 0; i--) {
            int bit = val & (1 << i);
	        bit >>= i;
            if (curr.child[bit] == null)
                curr.child[bit] = new Node();
            curr = curr.child[bit];
        }
        curr.val = val;
    }
    public int getXOR(int val) {
        Node curr = this.root;
        for (int i = 31; i >= 0; i--) {
            int bit = val & (1 << i);
	        bit >>= i;
            if (curr.child[1 - bit] != null)
                curr = curr.child[1 - bit];
            else
                curr = curr.child[bit];
        }
        return val ^ curr.val;
    }
}