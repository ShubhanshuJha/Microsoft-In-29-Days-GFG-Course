/* Number of Provinces
Given an undirected graph with V vertices. We say two vertices u and v belong to a single province if there is a path from u to v or v to u.
Your task is to find the number of provinces.
Note: A province is a group of directly or indirectly connected cities and no other cities outside of the group.

Example 1:
Input:  [
         [1, 0, 1],
         [0, 1, 0],
         [1, 0, 1]
        ]

Output: 2
Explanation: The graph clearly has 2 Provinces [1,3] and [2]. As city 1 and city 3 has a path between them they belong to a single province.
        City 2 has no path to city 1 or city 3 hence it belongs to another province.

Example 2:
Input:  [
         [1, 1],
         [1, 1]
        ]
Output: 1  */


// DFS Approach
class Solution {
    // Time: O(V ^ 2)        Space: O(V)
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] vis = new boolean[V];
        int provinceCount = 0;
        
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                provinceCount++;
                dfs(adj, vis, V, i);
            }
        }
        
        return provinceCount;
    }
    private static void dfs(ArrayList<ArrayList<Integer>> adj, boolean[] vis, int V, int curr) {
        vis[curr] = true;
        ArrayList<Integer> neighbors = adj.get(curr);
        for (int i = 0; i < V; i++) {
            int neigh = neighbors.get(i);
            if (!vis[i] && neigh == 1) {
                dfs(adj, vis, V, i);
            }
        }
    }
};
