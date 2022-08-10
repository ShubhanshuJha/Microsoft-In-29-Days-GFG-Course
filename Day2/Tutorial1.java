// Count distinct elements in every window
/* Given an array of integers and a number K. Find the count of distinct elements in every window of size K
in the array.

Example 1: Input: 	N = 7, K = 4
					A[] = {1,2,1,3,4,2,3}
Output: 3 4 4 3
Explanation: Window 1 of size k = 4 is 1 2 1 3. Number of distinct elements in this window are 3.
		Window 2 of size k = 4 is 2 1 3 4. Number of distinct elements in this window are 4.
		Window 3 of size k = 4 is 1 3 4 2. Number of distinct elements in this window are 4.
		Window 4 of size k = 4 is 3 4 2 3. Number of distinct elements in this window are 3.

Example 2: Input:	N = 3, K = 2
					A[] = {4,1,1}
Output: 2 1 */

class Solution {
    // Bruteforce approach: Time = O(n*k)
    // ArrayList<Integer> countDistinct(int A[], int n, int k) {
    //     ArrayList<Integer> res = new ArrayList<>();
    //     for (int i = 0; i <= n - k; i++) {
    //         HashSet<Integer> vals = new HashSet<>();
    //         for (int j = i; j < i + k; j++)
    //             vals.add(A[j]);
    //         res.add(vals.size());
    //     }
    //     return res;
    // }
    
    // Optimized approach: Time=O(n)     Space=O(k) [If consider res list, then O(n)]
    // ArrayList<Integer> countDistinct(int A[], int n, int k) {
    //     ArrayList<Integer> res = new ArrayList<>();
    //     Deque<Integer> deq = new LinkedList<>();
    //     HashSet<Integer> set = new HashSet<>();
    //     HashMap<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < k - 1; i++) {
    //         deq.offer(A[i]);
    //         set.add(A[i]);
    //         map.put(A[i], map.getOrDefault(A[i], 0) + 1);
    //     }
    //     for (int i = k - 1; i < n; i++) {
    //         deq.offer(A[i]);
    //         set.add(A[i]);
    //         map.put(A[i], map.getOrDefault(A[i], 0) + 1);
    //         res.add(set.size());
            
    //         int lastUsed = deq.pollFirst();
    //         map.put(lastUsed, map.get(lastUsed) - 1);
    //         if (map.get(lastUsed) == 0) {
    //             set.remove(lastUsed);
    //             map.remove(lastUsed);
    //         }
    //     }
    //     return res;
    // }
    
    // More Optimized approach: Time=O(n)     Space=O(k) [If consider res list, then O(n)]
    ArrayList<Integer> countDistinct(int A[], int n, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>(){
            {
                for (int i = 0; i < k; i++)
                put(A[i], getOrDefault(A[i], 0) + 1);
            }
        };
        if (n >= k)
            res.add(map.size());
        for (int i = k, prev = 0; i < n; i++) {
            map.put(A[prev], map.get(A[prev]) - 1);
            if (map.get(A[prev]) == 0) {
                map.remove(A[prev]);
            }
            prev++;
            
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            res.add(map.size());
        }
        return res;
    }
}