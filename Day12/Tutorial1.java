/* Word Boggle
Given a dictionary of distinct words and an M x N board where every cell has one character. Find all possible words from the dictionary
that can be formed by a sequence of adjacent characters on the board. We can move to any of 8 adjacent characters
Note: While forming a word we can move to any of the 8 adjacent cells. A cell can be used only once in one word.

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

class Solution {
    public String[] wordBoggle(char[][] board, String[] dictionary) {
        ArrayList<String> lst = new ArrayList<>();
        int m = board.length, n = board[0].length;
        
        for (String word : dictionary) {
            if (search(board, word, m, n)) lst.add(word);
        }
        String[] res = new String[lst.size()];
        for (int i = 0; i < res.length; i++) res[i] = lst.get(i);
        return res;
    }
    
    private boolean search(char[][] board, String word, int r, int c) {
        boolean[][] vis = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, vis, i, j, r, c, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // All possible directions co-ordinates
    private int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    private int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    private boolean dfs(char[][] board, String word, boolean[][] vis, int x, int y, int r, int c, int idx) {
        if (x < 0 || x >= r || y < 0 || y >= c || vis[x][y] || word.charAt(idx) != board[x][y]
            || idx >= word.length()) return false;
        
        if (idx == word.length() - 1) return true;
        
        vis[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int pX = x + dx[i];
            int pY = y + dy[i];
            if (dfs(board, word, vis, pX, pY, r, c, idx + 1)) {
                return true;
            }
        }
        // if not found, then backtrack
        vis[x][y] = false;
        return false;
    }
}

