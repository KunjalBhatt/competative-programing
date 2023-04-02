package generalcoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DPBoxStacking {

    public static void main(String[] args) {

        int n = 3;
        int[] height = {1, 4, 3};
        int[] width = {2, 5, 4};
        int[] length = {3, 6, 1};

        System.out.println("Ans = " + maxHeight(height, width, length, n));
    }

    public static int maxHeight(int[] height, int[] width, int[] length, int n) {


        List<Box> listOfBox = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            listOfBox.add(new Box(height[i], width[i], length[i]));
            listOfBox.add(new Box(height[i], length[i], width[i]));
            listOfBox.add(new Box(width[i], height[i], length[i]));
            listOfBox.add(new Box(width[i], length[i], height[i]));
            listOfBox.add(new Box(length[i], width[i], height[i]));
            listOfBox.add(new Box(length[i], height[i], width[i]));

        }

        listOfBox.sort((o1, o2) -> o2.area - o1.area);


        int[] dp = new int[listOfBox.size()];
       // int[] boxLink = new int[listOfBox.size()];


        for (int i = 1; i < listOfBox.size(); i++) {
            dp[i] = listOfBox.get(i).height;
        }


        int maxHeight = 0;
        //   int maxHeightIndex = 0;

        for (int i = 1; i < listOfBox.size(); i++) {
            for (int j = 0; j < i; j++) {

                if (listOfBox.get(i).width < listOfBox.get(j).width &&
                        listOfBox.get(i).length < listOfBox.get(j).length && dp[i] < dp[j] + listOfBox.get(i).height) {
                    dp[i] = dp[j] + listOfBox.get(i).height;
                //   boxLink[i] = j;

                    maxHeight = Math.max(maxHeight, dp[i]);
                    //  maxHeightIndex = i;
                }
            }
        }

        //  System.out.println(Arrays.toString(boxLink));

       /* System.out.println(maxHeight);
        while(maxHeightIndex != 0){
            System.out.println(listOfBox.get(maxHeightIndex)); 
            maxHeightIndex = boxLink[maxHeightIndex];
        }*/


        return maxHeight;
    }


}

class Box {
    int height;
    int width;
    int length;

    int area;

    public Box(int a, int b, int c) {
        height = a;
        width = b;
        length = c;

        area = length * width;
    }

    public String toString() {
        return "h = " + height + ", w = " + width + ", l = " + length + ", area = " + area + "\n";
    }

}