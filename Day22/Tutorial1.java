/* Merge k Sorted Arrays
Given K sorted arrays arranged in the form of a matrix of size K*K. The task is to merge them into one sorted array.

Example 1:
Input:  K = 3,  arr[][] = {{1,2,3},{4,5,6},{7,8,9}}
Output: 1 2 3 4 5 6 7 8 9
Explanation:Above test case has 3 sorted arrays of size 3, 3, 3 arr[][] = [[1, 2, 3],[4, 5, 6], [7, 8, 9]].
        The merged list will be [1, 2, 3, 4, 5, 6, 7, 8, 9].

Example 2:
Input:  K = 4,  arr[][]={{1,2,3,4}{2,2,3,4},{5,5,6,6},{7,8,9,9}}
Output: 1 2 2 2 3 3 4 4 5 5 6 6 7 8 9 9
Explanation: Above test case has 4 sorted arrays of size 4, 4, 4, 4 arr[][] = [[1, 2, 2, 2],[3, 3, 4, 4],[5, 5, 6, 6],[7, 8, 9, 9]].
        The merged list will be [1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9, 9].  */


// Bruteforce Approach
class Solution {
    // Time: O(K^2*Log(K^2))      Space: O(K^2)
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        ArrayList<Integer> merged = new ArrayList<Integer>(){{ // size=K^2
            for (int[] a : arr) {
                for (int val : a)
                    add(val);
            }
        }};
        Collections.sort(merged); // sorting time= K^2 Log(K^2)
        return merged;
    }
}
-----------------------------------------------------------------------
// Optimized Approach
class Solution {
    // Time: O(K^2*Log(K))      Space: O(K)
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        ArrayList<Integer> merged = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        int[] ptr = new int[K];
        for (int i = 0; i < K; i++) {
            pq.offer(new Pair(arr[i][0], i));
        }
        while (!pq.isEmpty()) {
            Pair currMin = pq.poll();
            merged.add(currMin.val);
            ptr[currMin.row]++;
            if (ptr[currMin.row] < K)
                pq.offer(new Pair(arr[currMin.row][ptr[currMin.row]], currMin.row));
        }
        return merged;
    }
}

class Pair {
    int val, row;
    Pair(int val, int row) {
        this.val = val;
        this.row = row;
    }
}

