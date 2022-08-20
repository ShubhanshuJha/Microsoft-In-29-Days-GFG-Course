/* Factorials of large numbers
Given an integer N, find its factorial.

Example 1:	Input: N = 5
Output: 120
Explanation: 5! = 1*2*3*4*5 = 120

Example 2:	Input: N = 10
Output: 3628800
Explanation: 10! = 1*2*3*4*5*6*7*8*9*10 = 3628800 */

class Solution {
	// Here I'm using a DoublyLinkedList approach to solve.

	// Time: O(n ^ 2)		Space: O(n)
    static ArrayList<Integer> factorial(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        if (N <= 2) {
            res.add(N);
            return res;
        }
        Node h;
        Node t;
        h = t = new Node(1);
        for (int i = 2; i <= N; i++) {
            int prod = t.val * i;
            int carry = prod / 10;
            t.val = prod % 10;
            for (Node tmp = t.prev; tmp != null; tmp = tmp.prev) {
                prod = tmp.val * i;
                int v = prod + carry;
                tmp.val = v % 10;
                carry = v / 10;
            }
            while (carry > 0) {
                h = h.add(carry % 10);
                carry /= 10;
            }
        }
        while (h != null) {
            res.add(h.val);
            h = h.next;
        }
        return res;
    }
}
class Node {
    int val;
    Node next, prev;
    Node(int val) {
        this.val = val;
        prev = next = null;
    }
    Node add(int v) {
        Node temp = new Node(v);
        this.prev = temp;
        temp.prev = null;
        temp.next = this;
        return temp;
    }
}
---------------------------------------------------------------------------------------

// BigInteger approach
class Solution {
    static ArrayList<Integer> factorial(int N){
        ArrayList<Integer> list = new ArrayList<>();
        java.math.BigInteger n = new java.math.BigInteger(String.valueOf(N));
        String f = fact(n);
        for (int i = 0; i < f.length(); i++) {
            list.add(Integer.parseInt(f.substring(i, i+1)));
        }
        return list;
    }
    static String fact(java.math.BigInteger num) {
		java.math.BigInteger fact = java.math.BigInteger.ONE;
		for (java.math.BigInteger i = java.math.BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(java.math.BigInteger.ONE)) {
			fact = fact.multiply(i);
		}
		return fact.toString();
	}
}

-------------------------------------------------------------------------------
class Solution {
	// Time: O(n ^ 2)		Space: O(1)
    public static ArrayList<Integer> factorial(int N){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 2; i <= N; i++)
            multiply(list, i);
        return list;
    }
    private static void multiply(ArrayList<Integer> prod, int num){
        int n = prod.size();
        int carry = 0;
        int a;
        
        for (int i = n - 1; i >= 0; i --){
            a = prod.get(i) * num + carry;
            prod.set(i, a % 10);
            carry = a / 10;
        }
        
        a = carry;
        while (a > 0){
            prod.add(0, a % 10);
            a /= 10;
        }
    }
}
