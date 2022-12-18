/* Detect cycle in an undirected graph
Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not.

Example 1:
Input:  V = 5, E = 5
        adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}}
Output: 1

Example 2:
Input:  V = 4, E = 2
        adj = {{}, {2}, {1, 3}, {2}}
Output: 0  */

// Disjoint Set Approach
class Solution {
    // Time: O(V + E)       Space: O(V)
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] parent = new int[V];
        boolean[][] visited = new boolean[V][V];
        
        for(int i = 0; i < V; i++)
            parent[i] = i;
        
        for(int i = 0; i < V; i++){
            int u = i;
            int parentNode = find(parent, u);
            
            for(int v : adj.get(i)) {
                if (visited[u][v] || visited[v][u])
                    continue;
                
                visited[u][v] = true;
                
                int parentAdj = find(parent, v);
                if (parentNode == parentAdj)
                    return true;
                parent[v] = parentNode;
            }
        }
        return false;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent, parent[x]);
    }
}