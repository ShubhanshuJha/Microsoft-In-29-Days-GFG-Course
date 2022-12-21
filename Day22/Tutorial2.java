/* Huffman Encoding
Given a string S of distinct character of size N and their corresponding frequency f[] i.e. character S[i] has f[i] frequency.
Your task is to build the Huffman tree print all the huffman codes in preorder traversal of the tree.
Note: While merging if two nodes have the same value, then the node which occurs at first will be taken on the left of Binary Tree
and the other one to the right, otherwise Node with less value will be taken on the left of the subtree and other one to the right.

Example 1:
Input:	S = "abcdef",	f[] = {5, 9, 12, 13, 16, 45}
Output: 0 100 101 1100 1101 111
Explanation: Steps to print codes from Huffman Tree HuffmanCodes will be:
		f : 0
		c : 100
		d : 101
		a : 1100
		b : 1101
		e : 111
	Hence printing them in the PreOrder of Binary Tree.  */


// PriorityQueue Approach
class Solution {
	// Time: O(N*LogN)		Space: O(N)
    public ArrayList<String> huffmanCodes(String S, int[] f, int N) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.val == b.val ? 1 : a.val - b.val);
        for (int i = 0; i < N; i++)
            minHeap.offer(new Node(f[i]));
        
        while (minHeap.size() > 1) {
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            
            Node parent = new Node(left.val + right.val);
            parent.left = left;
            parent.right = right;
            minHeap.offer(parent);
        }
        
        Node root = minHeap.poll(); // root of Huffman Tree
        ArrayList<String> res = new ArrayList<>();
        preorder(root, res, "");
        return res;
    }
    private void preorder(Node root, ArrayList<String> res, String str) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(str);
            return;
        }
        preorder(root.left, res, str + "0");
        preorder(root.right, res, str + "1");
    }
}
class Node {
    int val;
    Node left, right;
    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

