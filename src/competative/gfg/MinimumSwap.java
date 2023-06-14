package competative.gfg;

import competative.generalcoding.Point;

import java.util.Arrays;

public class MinimumSwap {
    public static void main(String[] args) {
        MinimumSwap m1 = new MinimumSwap();
        int[] arr = {2, 8, 5, 4};
        System.out.println(m1.minSwaps(arr));
    }

    //Function to find the minimum number of swaps required to sort the array.
    public int minSwaps(int[] nums)
    {

        Point[] parr = new Point[nums.length];

        for(int i=0; i<nums.length;i++){
            parr[i] = new Point(nums[i], i);
        }

        Arrays.sort(parr,(a, b)->a.x-b.x);

        boolean[] visited = new boolean[nums.length];

        int ans = 0;

        for(Point p : parr){

            int cycle = 0;
            while(!visited[p.y]){
                System.out.println(p.x+" to "+parr[p.y].x);
                visited[p.y] = true;
                p = parr[p.y];
                cycle++;
            }
            if(cycle > 0) ans+=cycle-1;
        }

        return ans;
    }
}
