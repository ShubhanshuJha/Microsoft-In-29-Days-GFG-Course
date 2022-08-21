/* Print Anagrams together
Given an array of strings, return all groups of strings that are anagrams. The groups must be created in order of
their appearance in the original array. Look at the sample case for clarification.
Note: The final output will be in lexicographic order.

Example 1:	Input:	N = 5,	words[] = {act,god,cat,dog,tac}
Output:	act cat tac 
		god dog
Explanation: There are 2 groups of anagrams "god", "dog" make group 1. "act", "cat", "tac" make group 2.

Example 2:	Input:	N = 3,	words[] = {no,on,is}
Output: no on
		is
Explanation: There are 2 groups of anagrams "no", "on" make group 1. "is" makes group 2. */

class Solution {
	// Time: O(N M log M)	Space: O(N * M)		[N-> no. of strings, M-> len. of biggest string]
    public List<List<String>> Anagrams(String[] string_list) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> hmap = new HashMap<>();
        for (String str : string_list) {
            String key = sort(str.toCharArray());
            if (!hmap.containsKey(key)) {
                hmap.put(key, new ArrayList<String>());
            }
            hmap.get(key).add(str);
        }
        for (Map.Entry<String, List<String>> e : hmap.entrySet())
            result.add(e.getValue());
        return result;
    }
    String sort(char[] str) {
        Arrays.sort(str);
        return String.valueOf(str);
    }
}

-----------------------------------------------------

class Solution {
	// Not Optimized because having inner loop with sorting and each index is being visited again,
	// no matter, there's a continue statement.
	// Time: O(N M log M)		Space: O(N * M) [N * M-> Result List,	M-> vis HasSet]
    public List<List<String>> Anagrams(String[] string_list) {
        List<List<String>> result = new ArrayList<>();
        HashSet<Integer> vis = new HashSet<>();
        for (int i = 0; i < string_list.length; i++) {
            if (vis.contains(i)) continue;
            String key = sort(string_list[i].toCharArray());
            ArrayList<String> lst = new ArrayList<>();
            lst.add(string_list[i]);
            for (int j = i + 1; j < string_list.length; j++) {
                if (string_list[i].length() != string_list[j].length()) continue;
                String sorted = sort(string_list[j].toCharArray());
                if (key.equals(sorted)) {
                    lst.add(string_list[j]);
                    vis.add(j);
                }
            }
            result.add(lst);
        }
        return result;
    }
    String sort(char[] str) {
        Arrays.sort(str);
        return String.valueOf(str);
    }
}

