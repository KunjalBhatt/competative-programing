package competative.generalcoding;

import java.util.Arrays;
import java.util.Comparator;

public class DPMaxChain {
    public static void main(String[] args) {
        Pair[] arr = new Pair[5];
        arr[0] = new Pair(5, 24);
        arr[1] = new Pair(39, 60);
        arr[2] = new Pair(15, 28);
        arr[3] = new Pair(27, 40);
        arr[4] = new Pair(50, 90);
        Arrays.sort(arr, Comparator.comparingInt(o -> o.x));

        System.out.println(Arrays.toString(arr));


        //  {5  24 , 39 60 , 15 28 , 27 40 , 50 90}
        System.out.println("this is ans = " + maxChainLength(arr, arr.length));

    }

    static int maxChainLength(Pair[] arr, int n) {
        Arrays.sort(arr, Comparator.comparingInt(o -> o.x));

        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(arr[i].x + " " + arr[j].y);
                if (arr[i].x > arr[j].y && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[n - 1] + 1;
    }
}

class Pair {

    int x;
    int y;

    public Pair(int a, int b) {
        x = a;
        y = b;
    }

    public String toString() {
        return "[x = " + x + ", y = " + y + "]";
    }

}