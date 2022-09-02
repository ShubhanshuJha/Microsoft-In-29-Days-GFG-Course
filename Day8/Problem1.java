/* First and last occurrences of X
Given a sorted array having N elements, find the indices of the first and last occurrences of an
element X in the given array.
Note: If the number X is not found in the array, return '-1' as an array.

Example 1:	Input:	N = 4 , X = 3
					arr[] = { 1, 3, 3, 4 }
Output:	1 2
Explanation: For the above array, first occurence of X = 3 is at index = 1 and
		last occurence is at index = 2.

Example 2:	Input:	N = 4, X = 5
			arr[] = { 1, 2, 3, 4 }
Output:	-1
Explanation: As 5 is not present in the array, so the answer is -1. */

// Bruteforce approach
class Solution {
	// Time: O(n + size(map))	Space: O(n)
    public ArrayList<Integer> firstAndLast(int arr[], int n, int x) {
        Map<Integer, Stack<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            if (!map.containsKey(val)) {
                map.put(val, new Stack<>());
                map.get(val).push(i);
            } else if (map.get(val).size() == 1) {
                map.get(val).push(i);
            } else {
                map.get(val).pop();
                map.get(val).push(i);
            }
        }
        ArrayList<Integer> lst = new ArrayList<>();
        if (!map.containsKey(x)) {
            lst.add(-1);
            return lst;
        }
        int b = map.get(x).pop();
        int a = b;
        if (!map.get(x).isEmpty()) {
            a = map.get(x).pop();
        }
        lst.add(a);
        lst.add(b);
        return lst;
    }
}
--------------------------------------------------------------
// Optimized approach
class Solution {
	// Time: O(log(n) + log(n))		Space: O(1)
    public ArrayList<Integer> firstAndLast(int[] arr, int n, int x) {
        int a = -1, b = -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (arr[mid] == x) {
                a = mid;
                r = mid - 1;
            } else if (arr[mid] > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        ArrayList<Integer> lst = new ArrayList<>();
        if (a != -1) {
            l = 0; r = n - 1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (arr[mid] == x) {
                    b = mid;
                    l = mid + 1;
                } else if (arr[mid] > x) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            lst.add(a);
            lst.add(b);
        } else {
            lst.add(a);
        }
        return lst;
    }
}

