// Find All Four Sum Numbers
/* Given an array of integers and another number. Find all the unique quadruple from the given array that
sums up to the given number.

Example 1: Input: 	N = 5, K = 3
					A[] = {0,0,2,1,1}
Output: 0 0 1 2 $
Explanation: Sum of 0, 0, 1, 2 is equal to K.

Example 2: Input: 	N = 7, K = 23
					A[] = {10,2,3,4,5,7,8}
Output: 2 3 8 10 $2 4 7 10 $3 5 7 8 $
Explanation: Sum of 2, 3, 8, 10 = 23, sum of 2, 4, 7, 10 = 23 and sum of 3, 5, 7, 8 = 23. */

class Solution {
	// Time: O(n ^ 3)   Space: O(4) + O(n ^ 2)
	public ArrayList<ArrayList<Integer>> fourSum(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i + 1; j < arr.length - 2; j++) {
                int d = k - (arr[i] + arr[j]);
                int l = j + 1,
                    r = arr.length - 1;
                while (l < r) {
                    if (arr[l] + arr[r] == d) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(arr[i]);
                        tmp.add(arr[j]);
                        tmp.add(arr[l]);
                        tmp.add(arr[r]);
                        res.add(tmp);
                        while (r > l && arr[r] == d - arr[l]) r--;
                    } else if (arr[l] + arr[r] < d) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        var tmp = res.stream().distinct().collect(java.util.stream.Collectors.toList());
        res = new ArrayList<>();
        for (ArrayList<Integer> l : tmp) {
            res.add(l);
        }
        return res;
    }

    // Time: O(n ^ 3)   Space: O(n ^ 2) [Only for returning the result]
    public ArrayList<ArrayList<Integer>> fourSum(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i + 1; j < arr.length - 2; j++) {
                int d = k - (arr[i] + arr[j]);
                int l = j + 1,
                    r = arr.length - 1;
                while (l < r) {
                    if (arr[l] + arr[r] == d) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(arr[i]);
                        tmp.add(arr[j]);
                        tmp.add(arr[l]);
                        tmp.add(arr[r]);
                        res.add(tmp);
                        // removing the duplicates of 3rd number
                        while (l < r && arr[l] == tmp.get(2)) l++;
                        // removing the duplicates of 4th number
                        while (r > l && arr[r] == tmp.get(3)) r--;
                    } else if (arr[l] + arr[r] < d) {
                        l++;
                    } else {
                        r--;
                    }
                }
                // removing the duplicates of 2nd number
                while (j + 1 < arr.length - 1 && arr[j] == arr[j + 1]) j++;
            }
            // removing the duplicates of 1st number
            while (i + 1 < arr.length - 1 && arr[i] == arr[i + 1]) i++;
        }
        return res;
    }
}