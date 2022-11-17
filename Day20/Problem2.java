/* Word Search
Given a 2D board of letters and a word. Check if the word exists in the board. The word can be constructed from letters of adjacent cells only.
i.e - horizontal or vertical neighbors. The same letter cell can not be used more than once. 

Example 1:
Input: 	board = {{a,g,b,c},{q,e,e,l},{g,b,k,s}},
		word = "geeks"
Output:	1
Explanation: The board is-
		a g b c
		q e e l
		g b k s
	The letters which are used to make the "geeks" are colored.

Example 2:
Input:	board = {{a,b,c,e},{s,f,c,s},{a,d,e,e}},
		word = "sabfs"
Output:	0
Explanation: The board is-
		a b c e
		s f c s
		a d e e
	Same letter can not be used twice hence ans is 0.  */


// Recursive DFS Approach
class Solution {
	// Time: O(m * n * (4 ^ L))		Space: O(L)		where L = length of word
    public boolean isWordExist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        List<int[]> lst = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    lst.add(new int[]{i, j});
                }
            }
        }
        
        for (int[] r : lst) {
            if (dfs(board, m, n, word, new boolean[m][n], r[0], r[1], ""))
                return true;
        }
        
        return false;
    }
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    
    private boolean dfs(char[][] board, int m, int n, String key, boolean[][] vis, int r, int c, String curr) {
        if (r < 0 || r >= m || c < 0 || c >= n || vis[r][c])
            return false;
        
        if (curr.length() == key.length()) {
            return curr.equals(key);
        }
        if (board[r][c] != key.charAt(curr.length()))
            return false;
        
        vis[r][c] = true;
        curr += board[r][c];
        
        for (int i = 0; i < 4; i++) {
            int r_ = r + dy[i];
            int c_ = c + dx[i];
            if (dfs(board, m, n, key, vis, r_, c_, curr))
                return true;
        }
        vis[r][c] = false;
        
        return false;
        
    }
}


