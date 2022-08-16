/*  Overlapping Intervals
Given a collection of Intervals, the task is to merge all of the overlapping Intervals.

Example 1:  Input:  Intervals = {{1,3},{2,4},{6,8},{9,10}}
Output: {{1, 4}, {6, 8}, {9, 10}}
Explanation: Given intervals: [1,3],[2,4][6,8],[9,10], we have only two overlapping
    intervals here,[1,3] and [2,4]. Therefore we will merge these two and return [1,4], [6,8], [9,10].

Example 2:  Input:  Intervals = {{6,8},{1,9},{2,4},{4,7}}
Output: {{1, 9}} */

class Solution {
    // Time: O(n log n)     Space: O(n)
    public int[][] overlappedInterval(int[][] Intervals) {
        // Sorting the Intervals based on the starting value of the interval
        Arrays.sort(Intervals, (a, b) -> a[0] - b[0]);
        Stack<int[]> stack = new Stack<>();
        for (int[] i : Intervals) {
            if (stack.isEmpty()) stack.push(i);
            else {
                int[] up = stack.peek();
                if (up[1] >= i[0]) {
                    int[] val = {Integer.min(i[0], up[0]), Integer.max(i[1], up[1])};
                    stack.pop();
                    stack.push(val);
                } else {
                    stack.push(i);
                }
            }
        }
        int[][] newIntervals = new int[stack.size()][2];
        int i = stack.size() - 1;
        while (!stack.isEmpty())
            newIntervals[i--] = stack.pop();
        return newIntervals;
    }
}
