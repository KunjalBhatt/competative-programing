package competative.gfg;

import java.util.Arrays;

public class BusConductor {
    public static void main(String[] args) {
        int[] chairs = {2,2,6,6};
        int[] passengers = {1,3,2,6};
        int n= chairs.length;

        System.out.println(findMoves(n,chairs,passengers));
    }

    public static int findMoves(int n, int[] chairs, int[] passengers) {

        Arrays.sort(chairs);
        Arrays.sort(passengers);

        int ans = 0;

        for(int i=0; i<chairs.length;i++){
            ans += Math.abs(chairs[i] - passengers[i]);
        }

        return ans;
    }
}
