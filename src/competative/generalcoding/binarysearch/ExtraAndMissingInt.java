package competative.generalcoding.binarysearch;

import java.util.Arrays;

public class ExtraAndMissingInt {
    public static void main(String[] args) {
        int n=47;
        int[] arr = {13, 33, 43, 16, 25, 19, 23, 31, 29, 35, 10, 2, 32, 11, 47, 15, 34, 46, 30, 26, 41, 18, 5, 17,
                37, 39, 6, 4, 20, 27, 9, 3, 8, 40, 24, 44,
                14, 36, 7, 38, 12, 1, 42, 12, 28, 22, 45};
        ExtraAndMissingInt extraAndMissingInt = new ExtraAndMissingInt();
        System.out.println(Arrays.toString(extraAndMissingInt.findTwoElement(arr, n)));
    }

    /**
     * Making value as index, if visiting value is negative that mean that index(value) comes twice
     * and to find missing search for positive value
     * @param arr
     * @param n
     * @return  ans arr
     */
    int[] findTwoElement(int arr[], int n) {
        int[] ans = new int[2];

        for(int i=0; i<arr.length;i++){
            int absVal = Math.abs(arr[i]);

            if(arr[absVal - 1] > 0){
                arr[absVal - 1] = -arr[absVal - 1];
            }else{
                ans[0] = absVal;
            }
        }

        for(int i=0; i<arr.length;i++){
            if(arr[i] > 0){
                ans[1] = i+1;
            }
        }

        return ans;
    }


}
