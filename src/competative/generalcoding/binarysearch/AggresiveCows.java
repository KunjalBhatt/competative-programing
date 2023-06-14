package competative.generalcoding.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls. The stalls are located along a straight line at positions x1 ... xN (0 <= xi <= 1,000,000,000).
 *
 * His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, FJ wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?
 * Input
 * t – the number of test cases, then t test cases follows.
 * * Line 1: Two space-separated integers: N and C
 * * Lines 2..N+1: Line i+1 contains an integer stall location, xi
 * Output
 * For each test case output one integer: the largest minimum distance
 * 1
 * 5 3
 * 1
 * 2
 * 8
 * 4
 * 9
 *
 */
public class AggresiveCows {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            int[] slots = new int[n];
            for (int i = 0; i < n; i++) {
                slots[i] = sc.nextInt();
            }
            int diff = findMaxDiff(n,c,slots);
            System.out.println(diff);
        }

    }

    private static int findMaxDiff(int n, int c, int[] slots) {
        Arrays.sort(slots);
        int low = 1;
        int high = slots[slots.length - 1] - 1; // gap
        int best =0;
        while(low <= high){

            int mid = low + (high-low) /2;

            if(isPossibleGap(n,c,slots,mid)){
                low= mid+1;
                best = mid;
            }else{
                high = mid - 1;
            }
        }




        return best;
    }

    static boolean isPossibleGap(int n, int c, int[] slots, int gap){

        int left = slots[0];
        int assigned = 1;

        for(int i=1; i < slots.length; i++){
            if(slots[i] - left >= gap){
                assigned++;
                left = slots[i];
            }
        }
        return assigned >= c;
    }

}
