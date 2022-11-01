/* Construct Tree from Inorder & Preorder
Given 2 Arrays of Inorder and preorder traversal. Construct a tree and print the Postorder traversal. 

Example 1:
Input:	N = 4,	inorder[] = {1 6 8 7},	preorder[] = {1 6 7 8}
Output: 8 7 6 1

Example 2:
Input:	N = 6,	inorder[] = {3 1 4 0 5 2},	preorder[] = {0 1 3 4 2 5}
Output: 3 4 1 5 2 0
Explanation: The tree will look like
								       0
								    /     \
								   1       2
								 /   \    /
								3    4   5  	*/



// Iterative Search approach
class Solution {
    private static int idx;
    // Time: O(N ^ 2)		Space: O(N)
    public static Node buildTree(int[] inorder, int[] preorder, int n) {
        idx = 0;
        return add(inorder, preorder, 0, n - 1);
    }
    private static Node add(int[] in, int[] pre, int l, int r) {
        if (l > r)
            return null;
        
        int curr = pre[idx++];
        Node root = new Node(curr);
        
        if (l == r) {
            return root;
        }
        
        int pivot = search(in, l, r, curr);
        
        root.left = add(in, pre, l, pivot - 1);
        root.right = add(in, pre, pivot + 1, r);
        
        return root;
    }
    private static int search(int[] inorder, int left, int right, int key) {
    	int idx = -1;
    	for (int i = left; i <= right; i++)
    		if (inorder[i] == key) {
    			idx = i;
    			break;
    		}
    	return idx;
    }
}
--------------------------------------------------------------------------------------------
// HashMap approach
class Solution {
    private static  HashMap<Integer, Integer> hmap;
    private static int idx;
    // Time: O(N)		Space: O(N)
    public static Node buildTree(int[] inorder, int[] preorder, int n) {
        hmap = new HashMap<>();
        for (int i = 0; i < n; i++)
            hmap.put(inorder[i], i);
        idx = 0;
        return add(inorder, preorder, 0, n - 1);
    }
    private static Node add(int[] in, int[] pre, int l, int r) {
        if (l > r)
            return null;
        
        int curr = pre[idx++];
        Node root = new Node(curr);
        
        if (l == r) {
            return root;
        }
        
        int pivot = hmap.get(curr);
        
        root.left = add(in, pre, l, pivot - 1);
        root.right = add(in, pre, pivot + 1, r);
        
        return root;
    }
}

