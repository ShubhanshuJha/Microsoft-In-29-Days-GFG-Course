/* Police and Thieves
Given an array of size n such that each element contains either a 'P' for policeman or a 'T' for thief.
Find the maximum number of thieves that can be caught by the police. 
Keep in mind the following conditions:
    Each policeman can catch only one thief.
    A policeman cannot catch a thief who is more than K units away from him.

Example 1:	Input:	N = 5, K = 1,	arr[] = {P, T, T, P, T}
Output: 2
Explanation: Maximum 2 thieves can be caught. First policeman catches first thief 
	and second police man can catch either second or third thief.

Example 2:	Input:	N = 6, K = 2,	arr[] = {T, T, P, P, T, P}
Output: 3
Explanation: Maximum 3 thieves can be caught. */

// Naive approach
class Solution {
	// Time: O(n * k)		Space: O(n)
    static int catchThieves(char[] arr, int n, int k) {
        int count = 0;
        boolean[] vis = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'P') {
                if (i == 0) {
                    for (int j = i + 1; j < n && j <= i + k; j++)
                        if (!vis[j] && arr[j] == 'T') {
                            count++;
                            vis[j] = true;
                            break;
                        }
                } else if (i == n - 1) {
                    for (int j = i - k; j < n; j++) {
                        if (j < 0) continue;
                        if (!vis[j] && arr[j] == 'T') {
                            count++;
                            vis[j] = true;
                            break;
                        }
                    }
                } else {
                    boolean flag = false;
                    for (int j = i - k; j < i; j++) {
                        if (j < 0) continue;
                        if (!vis[j] && arr[j] == 'T') {
                            count++;
                            vis[j] = true;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag)
                        for (int j = i + 1; j < n && j <= i + k; j++)
                            if (!vis[j] && arr[j] == 'T') {
                                count++;
                                vis[j] = true;
                                break;
                            }
                }
            }
        }
        return count;
	} 
}
------------------------------------------------------------------------------
// Efficient approach
class Solution {
    // Time: O(n)       Space: O(n)
    static int catchThieves(char[] arr, int n, int k) {
        List<Integer> T = new ArrayList<>(),
                      P = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'P') P.add(i);
            else T.add(i);
        }
        int count = 0;
        int i = 0, j = 0;
        while (i < P.size() && j < T.size()) {
            if (Math.abs(P.get(i) - T.get(j)) <= k) {
                count++;
                i++;
                j++;
            } else if (P.get(i) > T.get(j))
                j++;
            else i++;
        }
        return count;
    } 
}
