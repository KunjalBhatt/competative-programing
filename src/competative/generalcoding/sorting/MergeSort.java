package competative.generalcoding.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeSort {
    public static void main(String[] args) {
        Integer[] arr = {5, 2, 3, 9, 4, 6, 7, 15, 32};

        /*
        5, 2, 3, 9, 4
        2, 3, 4, 5, 9


         */

        Arrays.sort(arr, MergeSort::compareValues);

        int[] array = Arrays.stream(arr).mapToInt(i -> i).toArray();


        //  mergeSort(arr,0, arr.length - 1);
       System.out.println(Arrays.toString(arr));

       // Arrays.stream(arr).boxed().sorted((a,b)->countBit(b)-countBit(a)).collect(Collectors.toList()).forEach(System.out::println);

    }

    static void mergeSort(int[] arr, int start, int end){

        if(start >= end) return;

        int mid = start + (end - start)/2;

        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);

        merge(arr, start, mid, end);
    }

    static void merge(int[] arr, int start, int mid, int end) {

        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i= start; i<=mid;i++){
            left[i - start] = arr[i];
        }
        for(int i= mid+1; i<=end;i++){
            right[i - (mid+1)] = arr[i];
        }

        int i=0, j=0;
        while(i<n1 && j<n2){

            if(compareValues(left[i],right[j]) >= 0) {
                arr[start++] = left[i++];
            }else{
                arr[start++] = left[i++];
            }
        }

        while(i<n1){
            arr[start++] = left[i++];
        }

        while(j<n2){
            arr[start++] = right[j++];
        }

    }

    static int countBit(int a){
        int count=0;
        while(a > 0){
            count+= a & 1;
            //System.out.println(a);
            a = a>>1;
        }
        return count;
    }

    static int compareValues(int a, int b){

        int aBitCount = countBit(a);
        int bBitCount = countBit(b);
        return bBitCount - aBitCount;
    }

}
