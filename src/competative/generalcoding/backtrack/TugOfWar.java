package competative.generalcoding.backtrack;

import java.util.ArrayList;
import java.util.List;

public class TugOfWar {

    public static void main(String[] args) {
        ArrayList<Integer> ls = new ArrayList<>(List.of(1, 2, 3, 4));
        System.out.println(tugOfWar(ls, ls.size()));
    }

    static int minDiff = 0;
    public static int tugOfWar(ArrayList<Integer> arr, int n) {
        int sum = 0;
        for(int a : arr){
            sum+=a;
        }
        minDiff = sum;

        solve(0,0,0,sum,n,arr,"");
        return minDiff;
    }

    static void solve(int i, int count, int curSum, int sum, int n, ArrayList<Integer> arr, String s){

        if(count == n/2){
         /*   System.out.println(s);
            System.out.println(minDiff+" "+curSum+" "+sum+" "+Math.abs(curSum - sum));*/
            minDiff = Math.min(minDiff, Math.abs(curSum - sum));
            return;
        }

        if(i == n) return;

        solve(i+1, count+1, curSum+arr.get(i), sum - arr.get(i), n, arr, s+i+",");
        solve(i+1, count, curSum, sum, n, arr, s);

    }
}
