/* Word Boggle - II
Given a dictionary of words and an M x N board where every cell has one character. Find all possible different words from the dictionary
that can be formed by a sequence of adjacent characters on the board. We can move to any of 8 adjacent characters, but a word should not
have multiple instances of the same cell.
Example 1:  Input:  N = 1,   dictionary = {"CAT"}
                    R = 3, C = 3,   board = {{C,A,P},{A,N,D},{T,I,E}}
Output: CAT
Explanation:    C A P
                A N D
                T I E
Words we got is denoted using same color.

Example 2:  Input:  N = 4,  dictionary = {"GEEKS","FOR","QUIZ","GO"}
                    R = 3, C = 3,   board = {{G,I,Z},{U,E,K},{Q,S,E}}
Output: GEEKS QUIZ
Explanation:    G I Z
                U E K
                Q S E
Words we got is denoted using same color. */


// Trie Approach
// Time: O((8 ^ (M * N)) MN)
class Solution {
    public String[] wordBoggle(char[][] board, String[] dictionary) {
        Trie root = new Trie();
        for (String word : dictionary)
            root.add(word.toCharArray());
        
        String[] res = root.search(board, board.length, board[0].length);
        return res;
    }
}

class TrieNode {
    final int SIZE = 26;
    TrieNode[] children;
    boolean isEndOfWord;
    TrieNode() {
        this.children = new TrieNode[SIZE];
        this.isEndOfWord = false;
        for (int i = 0; i < SIZE; i++)
            this.children[i] = null;
    }
}

class Trie {
    TrieNode root = null;
    Trie() {
        this.root = new TrieNode();
    }
    public void add(char[] key) {
        TrieNode curr = this.root;
        for (char ch : key) {
            if (curr.children[ch - 'A'] == null)
                curr.children[ch - 'A'] = new TrieNode();
            curr = curr.children[ch - 'A'];
        }
        curr.isEndOfWord = true;
    }
    public String[] search(char[][] board, int m, int n) {
        boolean[][] vis = new boolean[m][n];
        
        String str = "";
        TrieNode curr = this.root;
        HashSet<String> hset = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (curr.children[board[i][j] - 'A'] != null) {
                    str += board[i][j];
                    searchWord(curr.children[board[i][j] - 'A'], board, str, vis, hset, i, j, m, n);
                    str = "";
                }
            }
        }
        
        String[] res = new String[hset.size()];
        int idx = 0;
        for (String s : hset) res[idx++] = s;
        return res;
    }
    
    // poss. dir.: ULdiag, up, URdiag, left, right, LLdiag, down, LRdiag
    private final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    private void searchWord(TrieNode root, char[][] grid, String key, boolean[][] vis, HashSet<String> hset,
                                int x, int y, int r, int c) {
        if (root == null || vis[x][y]) return;
        if (root.isEndOfWord) {
            hset.add(key);
        }
        
        vis[x][y] = true;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                char ch = (char)(i + 'A');
                for (int j = 0; j < 8; j++) {
                    int pX = x + dx[j];
                    int pY = y + dy[j];
                    if (pX >= 0 && pX < r && pY >= 0 && pY < c && grid[pX][pY] == ch)
                        searchWord(root.children[i], grid, key + ch, vis, hset, pX, pY, r, c);
                }
            }
        }
        vis[x][y] = false;
    }
}
