package competative.generalcoding;

public class DPOptimalStrategy {
    public static void main(String[] args) {
        int[] arr = {8, 15, 3, 7};

        System.out.println("This ans = " + countMaximum(arr, arr.length));
        System.out.println("This Dp ans = " + countMaximumDP(arr, arr.length));
    }

    static long countMaximum(int[] arr, int n) {

        return countMaximumRe(arr, 0, n - 1);
    }

    static long countMaximumRe(int[] arr, int i, int j) {
        if (i + 1 == j) return Math.max(arr[i], arr[j]);

        return Math.max(arr[i] + Math.min(countMaximumRe(arr, i + 2, j), countMaximumRe(arr, i + 1, j - 1)),
                arr[j] + Math.min(countMaximumRe(arr, i, j - 2), countMaximumRe(arr, i + 1, j - 1))
        );
    }


    static long countMaximumDP(int[] arr, int n) {
        long[][] dp = new long[n][n];

        for (int gap = 1; gap < n; gap += 2) {
            for (int i = 0; i + gap < n; i++) {
                int j = i + gap;
                if (gap == 1) {
                    dp[i][j] = Math.max(arr[i], arr[j]);
                } else {
                    dp[i][j] = Math.max(arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                            arr[j] + Math.min(dp[i][j - 2], dp[i + 1][j - 1])
                    );
                }
            }
        }

        /* for(long a[] : dp){
            System.out.println(Arrays.toString(a));
        } */

        return dp[0][n - 1];

    }
}