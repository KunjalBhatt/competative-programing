package competative.generalcoding.binarysearch;

public class SmallestNumAlteastNTrailingZero {

    public static void main(String[] args) {
        SmallestNumAlteastNTrailingZero s1 = new SmallestNumAlteastNTrailingZero();
        System.out.println(s1.findNum(10));
    }

    int findNum(int n) {
        // Complete this function
        int low = 0;
        int high = 5*n;

        while(low < high){

            int mid = low + (high - low) / 2;

            if(countTrailingZero(mid) < n){
                low = mid + 1;
            }else{
                high = mid;
            }

        }
        return low;
    }

    int countTrailingZero(int num){

        int count = 0;
        while(num > 0){
            count+=num/5;
            num/=5;
        }
        return count;
    }
}
