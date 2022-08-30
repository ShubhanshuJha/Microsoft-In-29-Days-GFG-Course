/* Killing Spree
There are Infinite People Standing in a row, indexed from 1. A person having index 'i' has strength of (i*i).
You have Strength 'P'. You need to tell what is the maximum number of People You can Kill With your Strength P.
You can only Kill a person with strength 'X' if P >= 'X' and after killing him, Your Strength decreases by 'X'.

Example 1:	Input:	N = 14
Output: 3
Explanation: The strengths of people is 1, 4, 9, 16, ....  and so on. WE can kill the first 3 person,
	after which our Power becomes 0 and we cant kill anyone else. So answer is 3.

Example 2:	Input:	N = 10
Output: 2 */

// Linear Search approach: Time = O(Sqrt(n))
class Solution {
	long killinSpree(long n) {
	    long count = 0L;
	    for (long i = 1; i * i <= n; i++) {
	        count++;
	        n -= (i * i);
	    }
	    return count;
	}
}
------------------------------------------------
// Binary Search: Time = O(logN)
class Solution {
    long sumUptoNSquare(long m) {
        long _2mPlus1 = (((m << 1) % Long.MAX_VALUE) + 1) % Long.MAX_VALUE;
        return ((m * (m + 1) * _2mPlus1) / 6) % Long.MAX_VALUE;
    }
    
	long killinSpree(long n) {
	    long count = 0L;
	    long l = 1l, r = (long)1e6;
	    while (l <= r) {
	        long m = l + (r - l) / 2;
	        long sum = sumUptoNSquare(m);
	        if (sum <= n) {
	            count = m;
	            l = m + 1;
	        } else {
	            r = m - 1;
	        }
	    }
	    return count;
	}
}
