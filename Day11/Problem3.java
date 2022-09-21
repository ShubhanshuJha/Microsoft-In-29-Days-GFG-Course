/* Job Sequencing Problem
Given a set of N jobs where each jobi has a deadline and profit associated with it.
Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit associated with
job if and only if the job is completed by its deadline.
Find the number of jobs done and the maximum profit.
Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job.

Example 1:	Input:	N = 4,	Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
Output:	2 60
Explanation: Job1 and Job3 can be done with maximum profit of 60 (20+40).

Example 2:	Input:	N = 5,	Jobs = {(1,2,100),(2,1,19),(3,2,27),(4,1,25),(5,1,15)}
Output:	2 127
Explanation: 2 jobs can be done with maximum profit of 127 (100+27). */


class Solution {
	// Time: O(n log n)		Space: O(n)
    int[] JobScheduling(Job arr[], int n) {
        int[] res = {0, 0};
        Arrays.sort(arr, (a, b) -> {
            if (b.profit == a.profit) return a.deadline - b.deadline;
            return b.profit - a.profit;
        });
        int maxDeadline = 0;
        for (Job j : arr)
            maxDeadline = Integer.max(maxDeadline, j.deadline);
        
        boolean[] interval = new boolean[maxDeadline];
        for (Job j : arr) {
            int idx = j.deadline - 1;
            while (idx >= 0 && interval[idx]) idx--;
            if (idx >= 0) {
                interval[idx] = true;
                res[0]++;
                res[1] += j.profit;
            }
        }
        return res;
    }
}

/*
class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}
*/