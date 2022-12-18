/* Strongly Connected Components (Kosaraju's Algo)
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, Find the number of strongly connected components in the graph.  */

/** Kosaraju's Algo approach:
 * 1. TopoSort the Graph Vertices [Get Nodes in a Stack as per their execution time]
 * 2. Transpose the Graph, i.e. if A->B then make A<-B
 * 3. Apply DFS on each unvisited Node of Stack and count the number of strongly connected components
*/

class Solution {
    // Time: O(V + E)        Space: O(V)
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        // 1. TopoSorting using DFS
        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(adj, i, stack, vis);
            }
        }

        // 2. Creating Transpose of the Graph
        ArrayList<ArrayList<Integer>> transposeAdj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            transposeAdj.add(new ArrayList<>());
        transpose(transposeAdj, adj, V); // adjTransposeList is generated

        // 3. Counting strongly connected components
        int count = 0;
        Arrays.fill(vis, false);
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!vis[curr]) {
                dfs2(transposeAdj, curr, vis);
                count++;
            }
        }
        return count;
    }
    private void dfs(ArrayList<ArrayList<Integer>> adj, int v, Stack<Integer> stack, boolean[] vis) {
        vis[v] = true;
        for (int neigh : adj.get(v)) {
            if (!vis[neigh]) {
                dfs(adj, neigh, stack, vis);
            }
        }
        stack.push(v);
    }
    private void transpose(ArrayList<ArrayList<Integer>> trans, ArrayList<ArrayList<Integer>> adj, int V) {
        for (int i = 0; i < V; i++) {
            for (int neigh : adj.get(i)) {
                trans.get(neigh).add(i);
            }
        }
    }
    private void dfs2(ArrayList<ArrayList<Integer>> adj, int v, boolean[] vis) {
        vis[v] = true;
        for (int neigh : adj.get(v)) {
            if (!vis[neigh]) {
                dfs2(adj, neigh, vis);
            }
        }
    }
}
