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

// DFS Approach
class Solution {
    public String[] wordBoggle(char[][] board, String[] dictionary){
        int m = board.length, n = board[0].length;
        ArrayList<String> lst = new ArrayList<>();
        for (String word : dictionary) {
            if (search(word, board, m, n)) lst.add(word);
        }
        String[] res = new String[lst.size()];
        for (int i = 0; i < res.length; i++) res[i] = lst.get(i);
        return res;
    }
    private boolean search(String word, char[][] grid, int r, int c) {
        boolean[][] vis = new boolean[r][c];
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == word.charAt(
                    0) && dfs(word, grid, i, j, r, c, 0, vis))
                    return true;
            }
        }
        return false;
    }
    // poss. dir.: ULdiag, up, URdiag, left, right, LLdiag, down, LRdiag
    private final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    private boolean dfs(String word, char[][] grid, int x, int y, int r, int c, int idx, boolean[][] vis) {
        // if idx out of bound or vis[x][y] or char not matching return false
        if (x < 0 || x >= r || y < 0 || y >= c || idx >= word.length() || vis[x][y] || word.charAt(idx) != grid[x][y])
            return false;
        if (idx == word.length() - 1)
            return true;
        
        vis[x][y] = true;
        
        for (int i = 0; i < 8; i++) { // checking for each direct.
            int pX = x + dx[i];
            int pY = y + dy[i];
            if (dfs(word, grid, pX, pY, r, c, idx + 1, vis))
                return true; // word found
        }
        // if word not found, then backtrack
        vis[x][y] = false;
        return false;
    }
}


