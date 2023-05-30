package competative.generalcoding.binarysearch;

public class BinarySearchAlgo {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 67, 123, 125};

        for(int i = 0; i< arr.length;i++){
            int found = binarySearch(arr, arr[i]);
            System.out.println(found);
            if(found!= i){
                System.out.println(found);
            }
        }

    }

    /**
     *
     * @param arr int Array
     * @param val value to be search in array
     * @return Returns the fist occurrence of value in sorted array,
     *        if value not present than return next bigger value's index;
     */
    public static int lowerBound(int[] arr, int val){

        int start =0;
        int end = arr.length - 1;

        while(start < end){

            int mid = start + (end-start)/2;

            if(arr[mid] < val){
                start = mid+1;
            }else{
                end=mid;
            }

        }

        return start;
    }

    /**
     *
     * @param arr int Array
     * @param val value to be search in array
     * @return Returns next bigger element index
     */
    public static int upperBound(int[] arr, int val){
        int start = 0;
        int end = arr.length - 1;

        while(start < end){

            int mid = start + (end- start)/2;

            if(arr[mid] <= val){
                start = mid + 1;
            }else{
                end = mid;
            }
        }

        return start;
    }

    public static int binarySearch(int[] arr, int val){

        int start = 0;
        int end = arr.length - 1;

        while(start <= end){

            int mid = start + (end - start)/2;


            if(arr[mid] == val){
                return mid;
            }else if(arr[mid] < val){
                start= mid +1;
            }else{
                end = mid-1;
            }
        }
        return -1;
    }

}
