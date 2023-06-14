package competative.generalcoding.binarysearch;

/**
 * Given a sorted and rotated array A of N distinct elements which is rotated at some point, and given an element key.
 * The task is to find the index of the given element key in the array A.
 * The whole array A is given as the range to search.
 */
public class SearchInARotatedArray {
    public static void main(String[] args) {
        SearchInARotatedArray search = new SearchInARotatedArray();
        int[] arr = {3, 5, 1, 2};
        int key = 6;
        System.out.println(search.search(arr, 0, arr.length-1, key));
    }

    int search(int arr[], int l, int h, int key)
    {
        int start = 0;
        int end = h;

        while(start < end){
            int mid = start + (end-start)/2;

            if(arr[mid] > arr[h]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        int pivot = start;

        int index = binarySearch(arr, 0,pivot-1, key);
        if(index == -1){
            index = binarySearch(arr, pivot,h, key);
        }

        return index;
    }

    int binarySearch(int[] arr, int start, int end, int value){

        while(start<=end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                start = mid + 1;
            }else{
                end = mid - 1;
            }

        }
        return -1;
    }

}
