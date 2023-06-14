package competative.generalcoding.binarysearch;


import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Two ﬁnite, strictly increasing, integer sequences are given. Any common integer between the two sequences constitute an intersection point. Take for example the following two sequences where intersection points are
 * printed in bold:
 *
 * First= 3 5 7 9 20 25 30 40 55 56 57 60 62
 * Second= 1 4 7 11 14 25 44 47 55 57 100
 * You can ‘walk” over these two sequences in the following way:
 *
 * You may start at the beginning of any of the two sequences. Now start moving forward.
 * At each intersection point, you have the choice of either continuing with the same sequence you’re currently on, or switching to the other sequence.
 * The objective is ﬁnding a path that produces the maximum sum of data you walked over. In the above example, the largest possible sum is 450, which is the result of adding 3, 5, 7, 9, 20, 25, 44, 47, 55, 56, 57, 60, and 62
 */
public class DoubleHelix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t != 0){
            int path1 = t;
            int[] path1ele = new int[path1];
            for(int i=0; i<path1;i++){
                path1ele[i] = sc.nextInt();
            }
            int path2 = sc.nextInt();
            int[] path2ele = new int[path2];
            for(int i=0; i<path2;i++){
                path2ele[i] = sc.nextInt();
            }
            t = sc.nextInt();

            int ans = path1 > path2 ? getMaxCount(path2ele,path1ele) : getMaxCount(path1ele,path1ele);
            System.out.println(ans);

        }


    }

    static int getMaxCount(int[] arr1, int[] arr2){

        int[] ps1 = new int[arr1.length + 1];
        int[] ps2 = new int[arr2.length + 1];

        for(int i=0; i<arr1.length;i++){
            ps1[i+1] = ps1[i]+arr1[i];
        }
        for(int i=0; i<arr2.length;i++){
            ps2[i+1] = ps2[i]+arr2[i];
        }
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        int i = 0;
        int j = 0;
        int prev1 = 0;
        int prev2 = 0;
        int ans = 0;
        while(i < arr1.length){
            j = findIndex(arr2, prev2, arr1[i]);

            if(j > -1){
                ans+=Math.max(ps1[i]-ps1[prev1], ps2[j]-ps2[prev2]);
                ans+=arr1[i];
                System.out.println(ans+" "+arr1[i]);
                prev1 = i+1;
                prev2 = j+1;
            }
            i++;
        }

        ans+=Math.max(ps1[arr1.length]-ps1[prev1], ps2[arr2.length]-ps2[prev2]);
      //  System.out.println(ans+" final");
        return ans;
    }

    static int findIndex(int[] arr2,int start, int find){
        int end = arr2.length - 1;

        while(start <= end){
            int mid = start + (end- start) /2;
            if(arr2[mid] == find){
                return mid;
            }else if(arr2[mid] < find){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        return  -1;
    }
}
