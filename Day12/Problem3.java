/* Phone directory
Given a list of contacts contact[] of length n where each contact is a string which exist in a phone directory and a query string s. The task is
to implement a search query for the phone directory. Run a search query for each prefix p of the query string s (i.e. from  index 1 to |s|) that
prints all the distinct contacts which have the same prefix as p in lexicographical increasing order. Please refer the explanation part for better
understanding.
Note: If there is no match between query and contacts, print "0".

Example 1:
Input: 
	n = 3
	contact[] = {"geeikistest", "geeksforgeeks", "geeksfortest"}
	s = "geeips"
Output:
	geeikistest geeksforgeeks geeksfortest
	geeikistest geeksforgeeks geeksfortest
	geeikistest geeksforgeeks geeksfortest
	geeikistest
	0
	0
Explaination: By running the search query on contact list for "g" we get: "geeikistest", "geeksforgeeks" and "geeksfortest".
	By running the search query on contact list for "ge" we get: "geeikistest" "geeksforgeeks" and "geeksfortest".
	By running the search query on contact list for "gee" we get: "geeikistest" "geeksforgeeks" and "geeksfortest".
	By running the search query on contact list for "geei" we get: "geeikistest".
	No results found for "geeip", so print "0". No results found for "geeips", so print "0". */


// Trie Approach
class Solution{
    static ArrayList<ArrayList<String>> displayContacts(int n, 
                                        String[] contact, String s) {
        Trie root = new Trie();
        for (String str : contact) {
            root.add(str);
        }
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            root.search(s.substring(0, i + 1), temp);
            if (temp.isEmpty())
                temp.add("0");
            res.add(temp);
        }
        return res;
    }
}

class Node {
    Node[] children;
    boolean isEnd;
    Node() {
        this.children = new Node[256];
        this.isEnd = false;
    }
}

class Trie {
    private Node root;
    private final int SIZE = 256;
    Trie() {
        this.root = new Node();
    }
    public void add(String key) {
        Node curr = this.root;
        for (char ch : key.toCharArray()) {
            if (curr.children[ch] == null)
                curr.children[ch] = new Node();
            curr = curr.children[ch];
        }
        curr.isEnd = true;
    }
    public void search(String key, ArrayList<String> lst) {
        Node curr = this.root;
        for (char ch : key.toCharArray()) {
            if (curr.children[ch] == null) {
                return;
            }
            curr = curr.children[ch];
        }
        traverse(curr, key, lst);
    }
    private void traverse(Node root, String key, ArrayList<String> lst) {
        if (root == null) return;
        
        if (root.isEnd) {
            lst.add(key);
        }
        for (int i = 0; i < SIZE; i++) {
            if (root.children[i] != null) {
                traverse(root.children[i], key + (char)i, lst);
            }
        }
    }
}
