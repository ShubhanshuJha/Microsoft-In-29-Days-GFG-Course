/* Alien Dictionary
Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary. Find
the order of characters in the alien language.
Note: Many orders may be possible for a particular test case, thus you may return any valid order and output
will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.

Example 1:	Input: 	N = 5, K = 4
					dict = {"baa","abcd","abca","cab","cad"}
Output: 1
Explanation: Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given
	language "baa" comes before "abcd", therefore 'b' is before 'a' in output. Similarly we can find other orders.

Example 2:	Input: 	N = 3, K = 3
					dict = {"caa","aaa","aab"}
Output: 1
Explanation: Here order of characters is 'c', 'a', 'b' Note that words are sorted and in the given language "caa"
	comes before "aaa", therefore 'c' is before 'a' in output. Similarly we can find other orders. */


class Solution {
	// Time: O(N * |S| + K) , where |S| denotes maximum length.		Space: O(K)
    public String findOrder(String [] dict, int N, int K) {
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> indegree = new HashMap<Character, Integer>(){
            {
                for (String word : dict) {
                    for (char ch : word.toCharArray())
                        putIfAbsent(ch, 0);
                }
            }
        };
        
        for (int i = 0; i < dict.length - 1; i++) {
            String curr = dict[i],
                next = dict[i + 1];
            int len = Integer.min(curr.length(), next.length());
            for (int j = 0; j < len; j++) {
                char char1 = curr.charAt(j),
                    char2 = next.charAt(j);
                if (char1 != char2) {
                    HashSet<Character> hset = graph.getOrDefault(char1, new HashSet<>());
                    if (hset.add(char2))
                        indegree.put(char2, indegree.get(char2) + 1);
                    graph.put(char1, hset);
                    break;
                }
            }
        }
        
        // Kahn's algorithm (Tropological Sorting using BFS)
        Queue<Character> q = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        
        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0)
                q.offer(ch);
        }
        while (!q.isEmpty()) {
            char curr = q.poll();
            result.append(curr);
            if (graph.containsKey(curr)) {
                for (char neighbour : graph.get(curr)) {
                    indegree.put(neighbour, indegree.get(neighbour) - 1);
                    if (indegree.get(neighbour) == 0)
                        q.offer(neighbour);
                }
            }
        }
        // System.out.println(result);
        return result.toString();
    }
}
