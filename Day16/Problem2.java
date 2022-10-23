/* Preorder Traversal and BST
Given an array arr[ ] of size N consisting of distinct integers, write a program that returns 1 if given array can represent preorder
traversal of a possible BST, else returns 0.

Example 1:
Input:	N = 3,	arr = {2, 4, 3}
Output: 1
Explaination: Given arr[] can represent preorder traversal of following BST:
               2
                \
                 4
                /
               3

Example 2:
Input:	N = 3,	arr = {2, 4, 1}
Output: 0
Explaination: Given arr[] cannot represent preorder traversal of a BST.  */


// BST Approach: took more time
class Solution {
    // Time: O(N) [in worst-case]      Space: O(N) [in worst-case]
    static int canRepresentBST(int[] arr, int N) {
        BST root = new BST();
        for (int val : arr) {
            root.add(val);
        }
        ArrayList<Integer> pot = root.preorder();
        if (pot.size() != N) return 0;
        
        for (int i = 0; i < N; i++)
            if (pot.get(i) != arr[i])
                return 0;
        return 1;
    }
}

class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BST {
    private Node root;
    BST() {
        this.root = null;
    }
    public final void add(int key) {
        this.root = add(this.root, key);
    }
    private Node add(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (root.data > key) {
            if (root.left == null)
                root.left = new Node(key);
            else
                root.left = add(root.left, key);
        } else if (root.data < key) {
            if (root.right == null)
                root.right = new Node(key);
            else
                root.right = add(root.right, key);
        }
        
        return root;
    }
    private ArrayList<Integer> pot;
    public final ArrayList<Integer> preorder() {
        pot = new ArrayList<>();
        traverse(this.root);
        return pot;
    }
    private void traverse(Node root) {
        if (root == null)
            return;
        pot.add(root.data);
        traverse(root.left);
        traverse(root.right);
    }
}
-----------------------------------------------------------------------------
// Stack Approach: using right min. and next greater element approach
class Solution {
    // Time: O(N)       Space: O(N)
    static int canRepresentBST(int[] arr, int N) {
        int[] rightMin = new int[N];
        rightMin[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--)
            rightMin[i] = Math.min(arr[i], rightMin[i + 1]);
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) stack.pop();
            if (stack.isEmpty())
                nge[i] = -1;
            else
                nge[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < N; i++) {
            int root = arr[i];
            if (nge[i] != -1 && rightMin[nge[i]] < root)
                return 0;
        }
        return 1;
    }
}


