/* Snake and Ladder Problem
Given a 5x6 snakes and ladders board, find the minimum number of dice throws required to reach the destination or
last cell (30th cell) from the source (1st cell).
You are given an integer N denoting the total number of snakes and ladders and an array arr[] of 2*N size
where 2*i and (2*i + 1)th values denote the starting and ending point respectively of ith snake or ladder.
(https://contribute.geeksforgeeks.org/wp-content/uploads/snake-and-ladders.jpg)
Note: Assume that you have complete control over the 6 sided dice. No ladder starts from 1st cell.


Example 1:
Input:  N = 8,  arr[] = {3, 22, 5, 8, 11, 26, 20, 29, 17, 4, 19, 7, 27, 1, 21, 9}
Output: 3
Explanation: The given board is the board shown in the figure. For the above board output will be 3.
    a) For 1st throw get a 2. 
    b) For 2nd throw get a 6.
    c) For 3rd throw get a 2.   */


// BFS Approach: It is implemented as DP because the problems become Overlapping and to deal with that we use a vis[]
class Solution {
    // Time: O(N)       Space: O(N)
    public static int minThrow(int N, int[] arr) {
        int[] connection = new int[31];
        Arrays.fill(connection, -1);

        for (int i = 0; i < (N << 1); i += 2) {
            connection[arr[i]] = arr[i + 1];
        }
        boolean[] vis = new boolean[31];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        vis[1]= true;
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 1; i <= size; i++) {
                int curr = q.poll();
                if (curr == 30)
                    return step;
                for (int j = curr + 1; j <= curr + 6 && j <= 30; j++) {
                    if (vis[j]) continue;
                    vis[j] = true;
                    if (connection[j] != -1)
                        q.offer(connection[j]);
                    else
                        q.offer(j);
                }
            }
            step++;
        }
        return -1;
    }
}
