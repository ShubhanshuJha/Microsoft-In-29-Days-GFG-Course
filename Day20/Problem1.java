/* Bipartite Graph
Given an adjacency list of a graph adj  of V no. of vertices having 0 based index. Check whether the graph is bipartite or not.
 
Example 1:	Input:	3 2
					0 1
					1 2
Output:	1
Explanation: The given graph can be colored in two colors so, it is a bipartite graph.

Example 2:	Input:	4 4
					0 2
					0 3
					1 3
					2 3
Output:	0
Explanation: The given graph cannot be colored in two colors such that color of adjacent vertices differs.   */



// [Bipartite Graph: Graph that can be colored using two colors such that no adjacent nodes have the same color]
// Observation: If a graph doesn't have any odd length cycle, then it's a bipartite graph, otherwise not a bipartite graph.

// BFS Approach
class Solution {
	// Time: O(V + E)		Space: O(V)
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1); // initially no node is colored

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
            	if (!bfsCheck(adj, i, color))
            		return false;
            }
        }
        return true;
    }
    private boolean bfsCheck(ArrayList<ArrayList<Integer>>adj, int node, int[] color) {
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(node);
    	color[node] = 0;

    	while (!q.isEmpty()) {
    		int u = q.poll();
    		for (int v : adj.get(u)) {
    			if (color[v] == -1) {
    				q.offer(v);
    				color[v] = (color[u] == 0 ? 1 : 0);
    			} else if (color[u] == color[v])
    				return false;
    		}
    	}

    	return true;
    }
}

