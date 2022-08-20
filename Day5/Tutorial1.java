/* Permutations of a given string
Given a string S. The task is to print all unique permutations of the given string in lexicographically sorted order.

Example 1:	Input: ABC
Output:		ABC ACB BAC BCA CAB CBA
Explanation: Given string ABC has permutations in 6 forms as ABC, ACB, BAC, BCA, CAB and CBA .

Example 2:	Input: ABSG
Output:		ABGS ABSG AGBS AGSB ASBG ASGB BAGS BASG BGAS BGSA BSAG
			BSGA GABS GASB GBAS GBSA GSAB GSBA SABG SAGB SBAG SBGA SGAB SGBA
Explanation: Given string ABSG has 24 permutations. */

class Solution {
    public List<String> find_permutation(String S) {
        TreeSet<String> tset = new TreeSet<>();
        generate(S.toCharArray(), 0, tset);
        return new ArrayList<String>(){
            {
                addAll(tset);
            }
        };
    }
    private void generate(char[] str, int idx, TreeSet<String> tset) {
        if (idx == str.length) {
            tset.add(String.valueOf(str));
            return;
        }
        for (int i = idx; i < str.length; i++) {
            swap(str, idx, i);
            generate(str, idx + 1, tset);
            // Backtrack
            swap(str, idx, i);
        }
    }
    private void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
}

