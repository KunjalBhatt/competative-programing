package competative.generalcoding.binarysearch;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AllocateMinimumNumberOfPages {
    public static void main(String[] args) {
        int[] arr = {12,34,67,90};
        int m = 2;
        System.out.println(findPages(arr, arr.length, m));
    }

    public static int findPages(int[] arr,int n,int m) {

        int[] ps = new int[n+1];
        for(int i=0; i<n;i++){
            ps[i + 1] = ps[i] + arr[i];
        }
        System.out.println(Arrays.toString(ps));

        int low = ps[1];
        int high = ps[ps.length-1];
        int best = 0;
        while(low <= high){

            int mid = low + (high-low)/2;

            if(isPossible(arr, n, m, mid)){
                best= mid;
                System.out.println(best);
                high = mid - 1;

            }else{
                low = mid + 1;
            }

        }

        return best;
    }

    static boolean isPossible(int arr[], int n, int m, int curr_min) {
        int studentsRequired = 1;
        int curr_sum = 0;

        // iterate over all books
        for(int i=0;i<n;i++)
        {
            curr_sum += arr[i];
            if(curr_sum > curr_min)
            {
                studentsRequired++ ;   // increment student count

                curr_sum = arr[i] ;    // update curr_sum
            }
        }

        return studentsRequired <= m;
    }
}
