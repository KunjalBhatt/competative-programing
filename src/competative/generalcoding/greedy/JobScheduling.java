package competative.generalcoding.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a set of N jobs where each jobi has a deadline and profit associated with it.
 *
 * Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit associated with job if and only if the job is completed by its deadline.
 *
 * Find the number of jobs done and the maximum profit.
 *
 * Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job.
 *Example 1:
 *
 * Input:
 * N = 4
 * Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
 * Output:
 * 2 60
 * Explanation:
 * Job1 and Job3 can be done with
 * maximum profit of 60 (20+40).
 * Example 2:
 *
 * Input:
 * N = 5
 * Jobs = {(1,2,100),(2,1,19),(3,2,27),
 *         (4,1,25),(5,1,15)}
 * Output:
 * 2 127
 * Explanation:
 * 2 jobs can be done with
 * maximum profit of 127 (100+27).
 */
public class JobScheduling {
    public static void main(String[] args) {

        Job[] arr = new Job[4];
        arr[0] = new Job(1,4,20);
        arr[1] = new Job(2,1,10);
        arr[2] = new Job(3,1,40);
        arr[3] = new Job(4,1,30);
        JobScheduling j1= new JobScheduling();
        System.out.println(Arrays.toString(j1.JobScheduling(arr, arr.length)));
    }

    int[] JobScheduling(Job arr[], int n)
    {
        PriorityQueue<Job> maxheap = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        for(Job j : arr){
            maxheap.add(j);
        }
        int time = 1;
        int ans = 0;
        int count = 0;
        while(!maxheap.isEmpty()){
            Job j = maxheap.poll();

            if(j.deadline < time) continue;

            ans+=j.profit;
            count++;

            time++;
        }
        int[] ansArr = new int[2];
        ansArr[0] = count;
        ansArr[1] = ans;

        return ansArr;
    }
    static class Job {
        int id, profit, deadline;
        Job(int x, int y, int z){
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }
}

