/* Minimum Platforms
Given arrival and departure times of all trains that reach a railway station. Find the minimum number of
platforms required for the railway station so that no train is kept waiting. Consider that all the trains
arrive on the same day and leave on the same day. Arrival and departure time can never be the same for a
train but we can have arrival time of one train equal to departure time of the other. At any given instance
of time, same platform can not be used for both departure of a train and arrival of another train. In such
cases, we need different platforms.


Example 1:	Input:	n = 6 
					arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
					dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
Output: 3
Explanation: Minimum 3 platforms are required to safely arrive and depart all trains.

Example 2:	Input:	n = 3
					arr[] = {0900, 1100, 1235}
					dep[] = {1000, 1200, 1240}
Output: 1
Explanation: Only 1 platform is required to safely manage the arrival and departure of all trains. */


class Solution {
    //Function to find the minimum number of platforms required at the railway station such that no train waits.
    // Time: O(n log n)	Space: O(n)
    static int findPlatform(int arr[], int dep[], int n) {
    	Train[] trains = new Train[n];
    	for (int i = 0; i < n; i++)
    		trains[i] = new Train(arr[i], dep[i]);
    	// Sorting the trains[] based on the arrival time of trains
    	Arrays.sort(trains, (a, b) -> (a.arr - b.arr));
    // 	System.out.println(Arrays.toString(trains));
    
    	PriorityQueue<Integer> platOccup = new PriorityQueue<>();
    	platOccup.offer(0); // initially 1 platform is vacant
    	for (Train t : trains) {
    	    if (t.arr > platOccup.peek()) {
    	        platOccup.poll();
    	        platOccup.offer(t.dep);
    	    } else {
    	        boolean foundVacantPlat = false;
    	        for (int i : platOccup) {
    	            if (t.arr > i) {
    	                platOccup.remove(i);
    	                platOccup.offer(t.dep);
    	                foundVacantPlat = true;
    	                break;
    	            }
    	        }
    	        if (!foundVacantPlat) platOccup.offer(t.dep);
    	    }
    	}
    	return platOccup.size();
    }
}

class Train {
	Integer arr, dep;
	Train(int arr, int dep) {
		this.arr = arr;
		this.dep = dep;
	}
	public String toString() {
		return arr + " -> " + dep;
	}
}
