package competative.generalcoding.binarysearch;

import java.util.*;

/**
 *
 * Given a sequence of N (1 ≤ N ≤ 34) numbers S1, ..., SN (-20,000,000 ≤ Si ≤ 20,000,000), determine how many subsets of S (including the empty one) have a sum between A and B (-500,000,000 ≤ A ≤ B ≤ 500,000,000), inclusive.
 *
 * Input
 * The first line of standard input contains the three integers N, A, and B. The following N lines contain S1 through SN, in order.
 *
 * Output
 * Print a single integer to standard output representing the number of subsets satisfying the above property. Note that the answer may overflow a 32-bit integer.
 */
public class SubsetSum {

    static int ans=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

      /*  countSubSet(arr, a, b, n - 1, 0);
        System.out.println(ans);*/

        System.out.println(countSubSet(arr,a,b));
    }
    //recursion
    static void countSubSet(int[] arr, int a, int b, int n, int sum){
        if(n < 0){
            if(sum >= a && sum <=b){
                ans++;
            }
            return;
        }

        countSubSet(arr, a, b, n-1, sum);
        countSubSet(arr, a, b, n-1, sum + arr[n]);
    }


    //bitmask and binary search

    static long countSubSet(int[] arr, int a, int b){
        long ans = 0;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        getSubSet(arr, 0, arr.length/2,left);
        getSubSet(arr, arr.length/2, arr.length - (arr.length/2),right);

        int[] rightArr = right.stream().mapToInt(i->i).sorted().toArray();

        for(int val : left){
            int low = BinarySearchAlgo.lowerBound(rightArr,a - val);
            int high = BinarySearchAlgo.upperBound(rightArr, b - val);
            ans+=high-low;
        }

        return ans;

    }

    static void getSubSet(int[] arr, int start, int len, List<Integer> sumset){

        int total = 1 << len;

        for(int mask=0; mask < total; mask++){
            int sum = 0;
            for(int i = 0; i < len; i++){
                if((mask & (1<<i)) !=0){
                    sum+=arr[start+i];
                }
            }
            sumset.add(sum);
        }

    }

}
