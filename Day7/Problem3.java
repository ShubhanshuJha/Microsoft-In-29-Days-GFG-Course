/* Bike Racing
Geek is organising a bike race with N bikers. The initial speed of the ith biker is denoted by
Hi Km/hr and the acceleration of ith biker as Ai Km/Hr2. A biker whose speed is 'L' or more, is
considered be a fast biker. The total speed on the track for every hour is calculated by adding the
speed of each fast biker in that hour. When the total speed on the track is 'M' kilometers per hour or more,
the safety alarm turns on. Find the minimum number of hours after which the safety alarm will start.

Example 1:	Input: 	N = 3, M = 400, L = 120
					H = {20, 50, 20}
					A = {20, 70, 90}
Output: 3
Explaination: Speeds of all the Bikers at ith hour
		Biker1= [20  40  60  80 100] 
		Biker2= [50 120 190 260 330]
		Biker3= [20 110 200 290 380] 

		Initial Speed on track  = 0 because none of the biker's speed is fast enough.
		Speed on track after 1st Hour= 120
		Speed on track after 2nd Hour= 190+200=390
		Speed on track after 3rd Hour= 260+290=550
		Alarm will start at 3rd Hour.

Example 2:	Input: 	N = 2, M = 60, L = 120
					H = {50, 30}
					A = {20, 40}
Output: 3
Explaination: Speeds of all the Bikers at ith hour
		Biker1= [50 70  90 110 130] 
		Biker2= [30 70 110 150 190]

		Initial Speed on track = 0 because none of the biker's speed is fast enough.
		Speed on track at 1st Hour= 0
		Speed on track at 2nd Hour= 0
		Speed on track at 3rd Hour= 150
		Alarm will buzz at 3rd Hour. */

// Bruteforce approach
class Solution {
	// Time: O(N * M)		Space: (1) [This produces TLE]
    static long buzzTime(int N, long M, long L, long H[], long A[]){
        long count = 0;
        long tSpeed = 0;
        while (tSpeed < M) {
            count++;
            long currTSpeed = 0;
            for (int i = 0; i < N; i++) {
                long currSpeed = H[i] + (A[i] * count);
                if (currSpeed >= L)
                    currTSpeed += currSpeed;
            }
            tSpeed = currTSpeed;
        }
        
        return count;
    }
}
----------------------------------------------------------
// Optimized approach
class Solution {
	// Time: O(N * log(max(L, M)))		Space: O(1)
    static long buzzTime(int N, long M, long L, long H[], long A[]){
        long count = 0;
        long l = 0, r = Long.max(L, M);
        while (l <= r) {
            long mid = (l + r) >> 1L;
            long currTSpeed = 0L;
            for (int i = 0; i < N; i++) {
                long currSpeed = H[i] + (A[i] * mid);
                if (currSpeed >= L)
                    currTSpeed += currSpeed;
            }
            if (currTSpeed >= M) {
                count = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return count;
    }
}