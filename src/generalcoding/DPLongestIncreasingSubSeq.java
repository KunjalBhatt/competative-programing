package generalcoding;

import java.util.Arrays;

public class DPLongestIncreasingSubSeq {
    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 7, 9, 1};
        System.out.println("This Ans = " + longestSubsequence(6, arr));
    }

    static int longestSubsequence(int size, int[] a) {
        int[] dp = new int[size];
        int maxSize = 0;
        Arrays.fill(dp, 1);
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    maxSize = Math.max(maxSize, dp[i]);
                }
            }
        }

        return maxSize;
    }
}
