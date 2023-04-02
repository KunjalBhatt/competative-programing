package generalcoding;

import java.util.Arrays;

public class DPnCr {

    public static void main(String[] args) {
        int n = 5;
        int r = 4;
        System.out.println("Ans = " + findCombincation(n, r));
    }

    static int findCombincation(int n, int r) {
        if (r > n) return 0;

        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * i;
        }
        long c = dp[n] / (dp[r] * dp[n - r]);
        System.out.println(c);
        return (int) c % (1000000000 + 7);

    }
}