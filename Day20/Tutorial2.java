/* Prerequisite Tasks
There are a total of N tasks, labeled from 0 to N-1. Some tasks may have prerequisites, for example to do task 0 you have to first complete task 1,
which is expressed as a pair: [0, 1].
Given the total number of tasks N and a list of prerequisite pairs P, find if it is possible to finish all tasks.

Example 1:
Input:	N = 4,	P = 3,	prerequisites = {{1,0},{2,1},{3,2}}
Output:	Yes
Explanation: To do task 1 you should have completed task 0, and to do task 2 you should have finished task 1, and to do task 3
		you should have finished task 2. So it is possible.

Example 2:
Input:	N = 2,	P = 2,	prerequisites = {{1,0},{0,1}}
Output:	No
Explanation: To do task 1 you should have completed task 0, and to do task 0 you should have finished task 1. So it is impossible.  */


// Kahn's Algorithm Approach (Topological Sorting)
class Solution {
	// Time: O(N + P)		Space: O(N + P), 	where N = no. of vertex		P = no. of prerequisites
    public boolean isPossible(int N, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++)
            adj.add(new ArrayList<>());
            
        for (int[] edge : prerequisites) {
            adj.get(edge[1]).add(edge[0]);
        }
        
        int[] indegree = new int[N];
        for (int v = 0; v < N; v++) {
            for (int neigh : adj.get(v)) {
                indegree[neigh]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < N; v++) {
            if (indegree[v] == 0) {
                q.offer(v);
            }
        }
        
        int count = 0;
        
        while (!q.isEmpty()) {
            int v = q.poll();
            count++;
            for (int neigh : adj.get(v)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0)
                    q.offer(neigh);
            }
        }
        
        return count == N;
    }
}

