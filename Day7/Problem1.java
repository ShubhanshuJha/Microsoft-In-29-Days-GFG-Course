/* Square root of a number
Given an integer x, find the square root of x. If x is not a perfect square, then return floor(√x).
Note: Try Solving the question without using the sqrt function. The value of x>=0.

Example 1:	Input:	x = 5
Output: 2
Explanation: Since, 5 is not a perfect square, floor of square_root of 5 is 2.

Example 2:	Input:	x = 4
Output: 2
Explanation: Since, 4 is a perfect square, so its square root is 2. */

// Using Math class in Java
class Solution {
	long floorSqrt(long x) {
		return (long)Math.sqrt(x);
	}
}
------------------------------------------------
// Using Linear Search: Time = Sqrt(x)
class Solution {
	long floorSqrt(long x) {
	    long sqrt = 1L;
	    while (sqrt * sqrt <= x) sqrt++;
	    return sqrt - 1;
	}
}
------------------------------------------------
// Using Binary Search: Time = O(logX)
class Solution {
	long floorSqrt(long x) {
	    long sqrt = 0l;
	    long start = 1, end = (x >> 1) + 1;
	    while (start <= end) {
	        long mid = (start + end) >> 1;
	        long sqrMid = mid * mid;
	        if (sqrMid == x) {
	            sqrt = mid;
	            break;
	        } else if (sqrMid < x) {
	            sqrt = mid;
	            start = mid + 1;
	        } else {
	            end = mid - 1;
	        }
	    }
	    return sqrt;
	}
}
