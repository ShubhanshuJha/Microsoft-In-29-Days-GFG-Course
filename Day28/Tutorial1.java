/* Union-Find
This problem is to implement disjoint set union. There will be 2 incomplete functions namely union and find.
You have to complete these functions.
Union: Join two subsets into a single set.
isConnected: Determine which subset a particular element is in. This can be used for determining if two elements are in same subset.


Example 1:
Input:  N = 5,  q = 4,  Queries = Union(1,3), isConnected(1,2), Union(1,5), isConnected(3,5)
Output: 0 1
Explanation: Initially all nodes 1 2 3 4 5 are not connected.
        After Union(1,3), node 1 and 3 will be connected. When we do isConnected(1,2), as node 1 and 2 are not connected
        it will return 0. After Union(1,5), node 1 and 5 will be connected. When we do isConnected(3,5), as node 1 and 3
        are connected, and node 1 and 5 are connected, hence 3 and 5 are connected. Thus, it will return 1.

Example 2:
Input:  N = 5,  q = 4,  Queries = Union(1,4), Union(1,5), isConnected(2,3), Union(3,4)
Output: 0  */



// Naive Approach
class Solution {
    // Time: O(N)        Space: O(1)    Worst case time complexity is linear.
    //Function to merge two nodes a and b.
    public void union_(int a, int b, int[] par, int[] rank) {
        if (isConnected(a, b, par, rank))
            return;
        a = find(par, a);
        b = find(par, b);
        par[a] = b;
    }
    private int find(int[] parent, int V) {
        if (parent[V] == V) return V;
        return find(parent, parent[V]);
    }

    //Function to check whether 2 nodes are connected or not.
    public Boolean isConnected(int a, int b, int[] par, int[] rank) {
        int X = find(par, a);
        int Y = find(par, b);
        return X == Y;
    }
}
-----------------------------------------------------------------------
// Union by Rank Approach
class Solution {
    // Time: O(Log N)        Space: O(1)    Worst case time complexity is logarithmic.
    //Function to merge two nodes a and b.
    public void union_(int a, int b, int[] par, int[] rank) {
        if (isConnected(a, b, par, rank))
            return;
        a = find(par, a);
        b = find(par, b);
        if (rank[a] >= rank[b])
            par[b] = a;
        else
            par[a] = b;
    }
    private int find(int[] parent, int V) {
        if (parent[V] == V) return V;
        return find(parent, parent[V]);
    }

    //Function to check whether 2 nodes are connected or not.
    public Boolean isConnected(int a, int b, int[] par, int[] rank) {
        int X = find(par, a);
        int Y = find(par, b);
        return X == Y;
    }
}
-----------------------------------------------------------------------
// Path Compression Technique in Union-Find
class Solution {
    // Time: O(1)        Space: O(1)
    //Function to merge two nodes a and b.
    public void union_(int a, int b, int[] par, int[] rank) {
        if (isConnected(a, b, par, rank))
            return;
        a = find(par, a);
        b = find(par, b);

        if (rank[a] > rank[b]) {
            par[b] = a;
        } else if (rank[a] < rank[b]) {
            par[a] = b;
        } else {
            par[b] = a;
            rank[a]++;
        }
    }
    private int find(int[] parent, int V) {
        if (parent[V] == V) return V;
        return parent[V] = find(parent, parent[V]);  // path-compression
    }

    //Function to check whether 2 nodes are connected or not.
    public Boolean isConnected(int a, int b, int[] par, int[] rank) {
        int X = find(par, a);
        int Y = find(par, b);
        return X == Y;
    }
}

