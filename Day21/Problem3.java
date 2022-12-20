/* Course Schedule
There are a total of n tasks you have to pick, labeled from 0 to n-1. Some tasks may have prerequisites tasks, for example to pick task 0
you have to first finish tasks 1, which is expressed as a pair: [0, 1].
Given the total number of n tasks and a list of prerequisite pairs of size m. Find a ordering of tasks you should pick to finish all tasks.
Note: There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all tasks, return an empty array.
Returning any correct order will give the output as 1, whereas any invalid order will give the output 0.

Example 1:
Input:	n = 2, m = 1
		prerequisites = {{1, 0}}
Output:	1
Explanation: The output 1 denotes that the order is valid. So, if you have, implemented your function correctly, then output would be 1
	for all test cases. One possible order is [0, 1].

Example 2:
Input:	n = 4, m = 4
		prerequisites = {{1, 0},
		                 {2, 0},
		                 {3, 1},
		                 {3, 2}}
Output:	1
Explanation: There are a total of 4 tasks to pick. To pick task 3 you should have finished both tasks 1 and 2. Both tasks 1 and 2 should be
	pick after you finished task 0. So one correct task order is [0, 1, 2, 3]. Another correct ordering is [0, 2, 1, 3]. 
	Returning any of these order will result in a Output of 1.  */

// Topo Sorting Approach
class Solution {
	// Time: O(n + m)		Space: O(n + m)
    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) {
    	Graph g = new Graph(n);
    	for (int i = 0; i < m; i++) {
    		int u = prerequisites.get(i).get(1);
    		int v = prerequisites.get(i).get(0);
    		g.add(u, v);
    	}
    	return g.topoSort();
    }
}

class Graph {
	private int V;
	private List<Integer>[] adj;
	@SuppressWarnings("unchecked")
	Graph(int V) {
		this.V = V;
		this.adj = new List[V];
		for (int i = 0; i < V; i++)
			this.adj[i] = new LinkedList<>();
	}
	public void add(int src, int dest) {
		this.adj[src].add(dest);
	}
	public int[] topoSort() {
		List<Integer> order = new ArrayList<>(V);
		int[] indegree = new int[V];
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < V; i++) {
			for (int neigh : this.adj[i]) {
				indegree[neigh]++;
			}
		}
		for (int v = 0; v < V; v++)
			if (indegree[v] == 0)
				q.offer(v);
		
		while (!q.isEmpty()) {
			int u = q.poll();
			order.add(u);
			for (int v : this.adj[u]) {
				indegree[v]--;
				if (indegree[v] == 0)
					q.offer(v);
			}
		}
		if (order.size() == V)
		    return order.stream().mapToInt(Integer::intValue).toArray();
		else
		    return new int[]{};
	}
}
