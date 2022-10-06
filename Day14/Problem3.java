/* Maximum Rectangular Area in a Histogram
Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a number of contiguous bars.
For simplicity, assume that all bars have the same width and the width is 1 unit, there will be N bars height of each bar will be given
by the array arr.

Example 1:
Input:	N = 7,	arr[] = {6,2,5,4,5,1,6}
Output: 12

Example 2:
Input:	N = 8,	arr[] = {7 2 8 9 1 3 6 5}
Output: 16
Explanation: Maximum size of the histogram will be 8  and there will be 2 consecutive histogram. And hence
		the area of the histogram will be 8x2 = 16.  */


class Solution {
    // Time: O(n)       Space: O(n)
    public static long getMaxArea(long[] hist, long n) {
        Stack<Integer> stack = new Stack<>();
        
        int[] pse = new int[(int)n];
        for (int i = 0; i < (int)n; i++) {
            while (!stack.isEmpty() && hist[stack.peek()] >= hist[i])
                stack.pop();
            if (stack.isEmpty())
                pse[i] = -1;
            else
                pse[i] = stack.peek();
            stack.push(i);
        }
        
        stack.clear();
        int[] nse = new int[(int)n];
        for (int i = (int)n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && hist[stack.peek()] >= hist[i])
                stack.pop();
            if (stack.isEmpty())
                nse[i] = (int)n;
            else
                nse[i] = stack.peek();
            stack.push(i);
        }
        
        long maxArea = 0l;
        for (int i = 0; i < (int)n; i++) {
            int w = (i - (pse[i] + 1)) + (nse[i] - i);
            long currArea = w * hist[i];
            maxArea = Long.max(maxArea, currArea);
        }
        return maxArea;
    }
}