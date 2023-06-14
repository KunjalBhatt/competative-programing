package competative.leetcode;

public class MaximalSquare221 {
    public static void main(String[] args) {

        MaximalSquare221 m = new MaximalSquare221();


    }

    public int maximalSquare(char[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;
        for(int i=0; i<n;i++){
            for(int j=0; j<m;j++){
                if(matrix[i][j] == '1'){
                    int len = 1;
                    boolean isSquare = true;
                    while(isSquare && i+len < n && j+len < m){
                        //row
                        for(int k =i;k <= i+len; k++){
                            if(matrix[k][j+len] != '1'){
                                isSquare = false;
                                break;
                            }
                        }

                        if(isSquare){
                            for(int k =j;k <= j+len; k++){
                                if(matrix[i+len][k] != '1'){
                                    isSquare = false;
                                    break;
                                }
                            }
                        }
                        if(isSquare) len++;

                    }

                    if(max < len){
                        max = len;
                    }
                }
            }
        }

        return max*max;
    }

    public int maximalSquareDp(char[][] matrix){

        int r = matrix.length;
        int c = matrix[0].length;

        int maxlen = 0;

        int[][] dp = new int[r+1][c+1];

        for(int i=1; i <=r; i++){
            for(int j=1; j <=c; j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                }
                if(maxlen < dp[i][j]){
                    maxlen = dp[i][j];
                }
            }
        }

        return maxlen*maxlen;

    }




}
