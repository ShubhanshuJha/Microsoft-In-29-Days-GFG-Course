/* Palindrome Pairs
Given an array of strings arr[] of size N, find if there exists 2 strings arr[i] and arr[j] such that arr[i]+arr[j] is a palindrome i.e the
concatenation of string arr[i] and arr[j] results into a palindrome.

Example 1:	Input:	N = 6,	arr[] = {"geekf", "geeks", "or","keeg", "abc", "bc"}
Output:	1
Explanation: There is a pair "geekf" and "keeg".

Example 2:	Input:	N = 5,	arr[] = {"abc", "xyxcba", "geekst", "or", "bc"}
Output:	1
Explanation: There is a pair "abc" and "xyxcba".  */


// HashMap  Approach
class Solution {
    private static boolean isPalin(String str) {
        int i = 0, j = str.length() - 1;
        while (i <= j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    // Time: O(N * (l ^ 2))		Space: O(N * (l ^ 2)), 	where l = length of longest string
    public static int palindromepair(int N, String[] arr) {
        if (N == 1) return 0;
        HashMap<String, Integer> hmap = new HashMap<>();
        for (int i = 0; i < N; i++)
            hmap.put(arr[i], i);

        StringBuilder left = null, right = null;
        for (int i = 0; i < N; i++) {
            if (arr[i].equals("") || arr[i].length() == 0)
                continue;
            String temp = reverse(arr[i]);
            if (hmap.containsKey(temp) && hmap.get(temp) != i)
                return 1;
            int l = arr[i].length();
            left = new StringBuilder();
            for (int j = 0; j < l - 1; j++) {
                left.append(arr[i].charAt(j));
                right = new StringBuilder(arr[i].substring(j + 1, l));
                if (isPalin(left.toString())) {
                    String rev = reverse(right.toString());;
                    if (hmap.containsKey(rev) && hmap.get(rev) != i)
                        return 1;
                }
                if (isPalin(right.toString())) {
                    String rev = reverse(left.toString());
                    if (hmap.containsKey(rev) && hmap.get(rev) != i)
                        return 1;
                }
            }
        }
        return 0;
    }
    private static String reverse(String str) {
        StringBuffer sb = new StringBuffer();
        int i = str.length() - 1;
        while (i >= 0) {
            sb.append(str.charAt(i));
            i--;
        }
        return sb.toString();
    }
}
-----------------------------------------------------------------------------------------------
// Trie Approach
class Solution {
    private static String rev(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    // Time: O(N * (l ^ 2))        Space: O(N)
    public static int palindromepair(int N, String[] arr) {
        if (N == 1) return 0;
        Trie root = new Trie();
        for (String str : arr) {
            root.insert(str);
        }
        StringBuilder left = null;
        for (int i = 0; i < N; i++) {
            int l = arr[i].length();
            if (l == 0) continue;
            
            String rev = rev(arr[i]);
            boolean isSame = arr[i].equals(rev);
            
            if (isSame && root.count(arr[i]) > 1)
                return 1;
            if (!isSame && root.contains(rev))
                return 1;
            left = new StringBuilder();
            for (int j = 0; j < l - 1; j++) {
                left.append(arr[i].charAt(j));
                String right = arr[i].substring(j + 1, l);
                
                if (isPalindrome(left.toString())) {
                    rev = rev(right);
                    if (root.contains(rev))
                        return 1;
                }
                if (isPalindrome(right)) {
                    rev = rev(left.toString());
                    if (root.contains(rev))
                        return 1;
                }
            }
        }
        return 0;
    }
    private static boolean isPalindrome(String str) {
        if (str.length() <= 1) return true;
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r))
                return false;
            l++; r--;
        }
        return true;
    }
};

class TrieNode {
    final int SIZE = 256;
    TrieNode[] children;
    boolean isEnd;
    int wCount;
    TrieNode() {
        this.children = new TrieNode[SIZE];
        this.isEnd = false;
        this.wCount = 0;
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
            if (curr.children[ch] == null)
                curr.children[ch] = new TrieNode();
            curr = curr.children[ch];
        }
        curr.isEnd = true;
        curr.wCount++;
    }
    public boolean contains(String key) {
        TrieNode curr = this.root;
        for (char ch : key.toCharArray()) {
            if (curr.children[ch] != null)
                curr = curr.children[ch];
            else
                return false;
        }
        return curr.isEnd;
    }
    public int count(String key) {
        TrieNode curr = this.root;
        for (char ch : key.toCharArray()) {
            if (curr.children[ch] != null)
                curr = curr.children[ch];
            else
                return -1;
        }
        return curr.isEnd ? curr.wCount : -1;
    }
}

