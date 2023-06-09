package competative.generalcoding;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] a = {3, 4, 5};
        int total = 1 << a.length;
        for(int mask=0; mask < total;mask++){
            for(int i=0;i<a.length;i++){
                if((mask & (1<<i)) != 0){
                    System.out.print(a[i]+" ");
                }
            }
            System.out.println();
        }

    }

    public static long minimumNumberOfCoins(int[] coins, int numberOfCoins, int value) {
        long[] dp = new long[value + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < numberOfCoins; i++) {
            for (int j = 1; j <= value; j++) {

                if (j >= coins[i]) {
                    long res = dp[j - coins[i]];
                    if (res != Long.MAX_VALUE && res + 1 < dp[j]) {
                        dp[j] = res + 1;
                    }
                }

            }
        }
        if (dp[value] == Long.MAX_VALUE)
            return -1;

        return dp[value];
    }


}
