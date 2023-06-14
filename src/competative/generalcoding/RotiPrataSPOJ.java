package competative.generalcoding;

import java.util.Arrays;
import java.util.Scanner;

public class RotiPrataSPOJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int p = sc.nextInt();
            int l = sc.nextInt();
            int[] r = new int[l];
            for(int i=0;i<l;i++){
                r[i] = sc.nextInt();
            }
           // System.out.println(Arrays.toString(r));

            int low = 0;
            int high = 1000000000;

            while(low < high){
                int mid = low + (high - low) /2;

                if(isPossible(r,p,mid)){
                    high = mid;
                }else{
                    low = mid + 1;
                }
            }

            System.out.println(low);
        }
    }

    static boolean isPossible(int[] r, int p, int time){
        int make = 0;
        for(int re : r){
           int timeTaken = re;
           int i =2;
           while(timeTaken <= time){
               make++;
               timeTaken+=re*i++;
           }
        }
        return p <=make;
    }


}
