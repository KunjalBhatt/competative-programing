package competative.generalcoding.binarysearch;

import competative.generalcoding.Point;

import java.util.Arrays;

public class MaximumProfitJobScheduling {

    public static void main(String[] args) {
        MaximumProfitJobScheduling s = new MaximumProfitJobScheduling();
        int[] startTime = {1,2,3,3};
        int[] endTime = {3,4,5,6};
        int[] profit = {50,10,40,70};
        System.out.println(s.jobScheduling(startTime,endTime,profit));
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];

        for(int i=0; i<startTime.length;i++){
            jobs[i] = new Job(startTime[i],endTime[i], profit[i]);
           }

        Arrays.sort(jobs, (i1,i2) -> i1.finish - i2.finish);

      //  return getMaxProfit(jobs,startTime.length-1);
        return getMaxProfitDP(jobs);
    }

    int getMaxProfit(Job[] intervals, int n){

        if(n == 0) return intervals[n].profit;

        int include = intervals[n].profit;
        int prev = getPreviousNotOverlapping(n, intervals);
        if(prev > -1)
            include+=getMaxProfit(intervals,prev);

        int exclude = getMaxProfit(intervals,n-1);
        return Math.max(include,exclude);
    }

    int getMaxProfitDP(Job[] intervals){

        int[] dp = new int[intervals.length];
        dp[0] = intervals[0].profit;

        for(int i=1; i< intervals.length; i++){
            int prev = getPreviousNotOverlapping(i,intervals);
            int including = intervals[i].profit;
            if(prev != -1) including+=dp[prev];
            dp[i] = Math.max(including, dp[i-1]);
        }
        return dp[dp.length-1];
    }

    int binarySearch(Job jobs[], int index)
    {
        // Initialize 'lo' and 'hi' for Binary Search
        int lo = 0, hi = index - 1;

        // Perform binary Search iteratively
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (jobs[mid].finish <= jobs[index].start)
            {
                if (jobs[mid + 1].finish <= jobs[index].start)
                    lo = mid + 1;
                else
                    return mid;
            }
            else
                hi = mid - 1;
        }

        return -1;
    }

    int getPreviousNotOverlapping(int current, Job[] interval){

        for(int i=current-1; i >=0;i--){
            if(interval[i].finish <= interval[current].start){
                return i;
            }
        }
        return -1;
    }

    static class Job
    {
        int start, finish, profit;
        Job(int start, int finish, int profit)
        {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }

}

