/* Detect Cycle using DSU
Given an undirected graph with V nodes and E edges. The task is to check if there is any cycle in undirected graph.
Note: Solve the problem using disjoint set union(dsu).  */


class Solution {
	// Time: O(V + E)		Space: O(V)
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 1; // initially all disjoin sets will be of size 1
        }

        for (int v = 0; v < V; v++) {
            for (int neigh : adj.get(v)) {
                if (v < neigh && !union(v, neigh, parent, rank)) // if union of the nodes is false then there's a cycle
                    return 1;
            }
        }
        return 0;
    }
    private boolean union(int x, int y, int[] parent, int[] rank) {
        x = find(parent, x);
        y = find(parent, y);

        if (x == y) // if they already exist in the same set
            return false;

        // union by rank
        if (rank[x] > rank[y])
            parent[y] = x;
        else if (rank[x] < rank[y])
            parent[x] = y;
        else {
            parent[y] = x;
            rank[x]++;
        }
        return true;
    }
    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]); // path compression
    }
}

