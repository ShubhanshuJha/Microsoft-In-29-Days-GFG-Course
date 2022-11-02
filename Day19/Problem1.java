/* Implementing Dijkstra Algorithm
Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where
the first integer of each list j denotes there is edge between i and j, second integers corresponds to the weight of that edge. You are given the
source vertex S and You to Find the shortest distance of all the vertex's from the source vertex S. You have to return a list of integers denoting
shortest distance between each node and Source vertex S. 

Note: The Graph doesn't contain any negative weight cycle.

Example 1:
Input:	V = 2, 	adj [] = {{{1, 9}}, {{0, 9}}},	S = 0
Output:	0 9
Explanation: The source vertex is 0. Hence, the shortest distance of node 0 is 0 and the shortest distance from node 1 is 9.

Example 2:
Input:	V = 3,	adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}},	S = 2
Output:	4 3 0
Explanation: For nodes 2 to 0, we can follow the path- 2-1-0. This has a distance of 1+3 = 4, whereas the path 2-0 has a distance of 6.
		So, the Shortest path from 2 to 0 is 4. The shortest distance from 0 to 1 is 1.  */


class Solution {
	// Time: O(V ^ 2)		Space: O(V ^ 2)
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        Graph g = new Graph(V);
        for (int i = 0; i < V; i++) {
            ArrayList<ArrayList<Integer>> edges = adj.get(i);
            for (ArrayList<Integer> edge : edges)
                g.add(i, edge.get(0), edge.get(1));
        }
        return g.dijkstra(S);
    }
}

class Graph {
    private int V;
    private int[][] adj;
    Graph(int V) {
        this.V = V;
        this.adj = new int[V][V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                this.adj[i][j] = -1;
    }
    public void add(int src, int dest, int wt) {
        this.adj[src][dest] = wt;
        this.adj[dest][src] = wt;
    }
    public int[] dijkstra(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.offer(new Vertex(src, 0));
        while (!pq.isEmpty()) {
            Vertex curr = pq.poll();
            for (int i = 0; i < V; i++) {
                if (this.adj[curr.v][i] != -1) {
                    if (curr.w + this.adj[curr.v][i] < dist[i]) {
                        dist[i] = curr.w + this.adj[curr.v][i];
                        pq.offer(new Vertex(i, dist[i]));
                    }
                }
            }
        }
        
        for (int i = 0; i < V; i++)
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        
        return dist;
    }
}

class Vertex {
    int v, w;
    Vertex(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

