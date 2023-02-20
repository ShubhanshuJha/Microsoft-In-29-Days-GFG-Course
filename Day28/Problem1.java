/* Number Of Islands
You are given a n,m which means the row and column of the 2D matrix and an array of  size k denoting
the number of operations. Matrix elements is 0 if there is water or 1 if there is land. Originally,
the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) and
each operator has two integer A[i][0], A[i][1] means that you can change the cell
matrix[A[i][0]][A[i][1]] from sea to island. Return how many island are there in the matrix after
each operation.You need to return an array of size k.
Note: An island means group of 1s such that they share a common side.

Example 1:
Input:  n = 4,   m = 5,  k = 4
        A = {{1,1},{0,1},{3,3},{3,4}}
Output: 1 1 2 2

Example 2:
Input:  n = 4,  m = 5,  k = 4
        A = {{0,0},{1,1},{2,2},{3,3}}
Output: 1 2 3 4  */


// Naive Approach: BFS + DFS
class Solution {
    // Time: O(k * rows * cols)        Space: O(rows * cols)
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        int k = operators.length;
        int[][] mat = new int[rows][cols];
        List<Integer> count = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            mat[operators[i][0]][operators[i][1]] = 1;
            count.add(find(mat, rows, cols));
        }
        return count;
    }
    private final int[] dx = {-1, 1, 0, 0},
                        dy = {0, 0, -1, 1};
    private int find(int[][] mat, int r, int c) {
        Deque<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (mat[i][j] == 1)
                    q.offer(new int[]{i, j});
        int count = 0;
        while (!q.isEmpty()) {
            int x = q.peek()[0],
                y = q.poll()[1];
            if (vis[x][y]) continue;
            count++;
            dfs(mat, r, c, x, y, vis);
        }
        return count;
    }
    private void dfs(int[][] mat, int r, int c, int x, int y, boolean[][] vis) {
        if (vis[x][y]) return;
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int x_ = x + dx[i],
                y_ = y + dy[i];
            if (x_ >= 0 && x_ < r && y_ >= 0 && y_ < c && mat[x_][y_] == 1) {
                dfs(mat, r, c, x_, y_, vis);
            }
        }
    }
}
----------------------------------------------------------------------------
// Efficient Approach: Union-Find Data Structure
class Solution {
    // Time: O(k * α(rows * cols))        Space: O(rows * cols)
        // α(x) = inverse Ackermann function wiz. a very slowly-growing fn and is considered almost
        // constant for practical values of x.
    private int[] rank, parent;
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        parent = new int[rows * cols];
        rank = new int[rows * cols];
        Arrays.fill(parent, -1);
        
        List<Integer> count = new ArrayList<>();
        int counter = 0;
        
        for (int[] operator : operators) {
            int x = operator[0],
                y = operator[1];
            int idx = x * cols + y;
            if (parent[idx] == -1) {
                parent[idx] = idx;
                counter++;
            }
            for (int[] dir : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                int r = x + dir[0],
                    c = y + dir[1];
                int adj = r * cols + c;
                if (r < 0 || r >= rows || c < 0 || c >= cols || parent[adj] == -1)
                    continue;
                if (union(idx, adj)) {
                    counter--;
                }
            }
            count.add(counter);
        }
        return count;
    }
    private boolean union(int x, int y) {
        int rootX = find(x),
            rootY = find(y);
        if (rootX == rootY) {
            return false;
        } else {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
