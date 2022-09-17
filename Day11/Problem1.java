/* Stock buy and sell
The cost of stock on each day is given in an array A[] of size N. Find all the segment of days on which you
buy and sell the stock so that in between those days your profit is maximum.
Note: Since there can be multiple solutions, the driver code will return 1 if your answer is correct, otherwise,
it will return 0. In case there's no profit the driver code will return the string "No Profit" for a correct solution.

Example 1:  Input:  N = 7,  A[] = {100,180,260,310,40,535,695}
Output: 1
Explanation: One possible solution is (0 3) (4 6). We can buy stock on day 0, and sell it on 3rd day,
    which will give us maximum profit. Now, we buy stock on day 4 and sell it on day 6.

Example 2:  Input:  N = 5,  A[] = {4,2,2,2,4}
Output: 1
Explanation: There are multiple possible solutions, one of them is (3 4). We can buy stock on day 3,
    and sell it on 4th day, which will give us maximum profit. */


// Naive approach
class Solution{
    ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
    }
}
-------------------------------------------------------------------
// Efficient approach
class Solution {
    // Time: O(n)   Space: O(1)
    ArrayList<ArrayList<Integer>> stockBuySell(int[] A, int n) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int currI = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] - A[currI] > 0) {
                res.add(get(currI, i));
                currI = i;
            } else {
                if (A[currI] > A[i])
                    currI = i;
            }
        }
        
        // System.out.println(res);
        return res;
    }
    ArrayList<Integer> get(int a, int b) {
        return new ArrayList<Integer>(){{ add(a); add(b); }};
    }
}