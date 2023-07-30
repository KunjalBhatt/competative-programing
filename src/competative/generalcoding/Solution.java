package competative.generalcoding;

import java.util.*;
import java.util.regex.*;


// Write your code here. DO NOT use an access modifier in your class declaration.

class Parser {

    public boolean isBalanced(String s) {
        char[] carr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : carr) {
            if (c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.pop() != c) {
                    return false;
                }
            }

        }

        return true;
    }

}

class Solution {

    public static void main(String[] args) {
      /*  Parser parser = new Parser();

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            System.out.println(parser.isBalanced(in.next()));
        }

        in.close();*/


        int[] arr = {1,2,3,4,5,6};

        for(int i=0; i< arr.length;i++){
            for(int j = i; j < arr.length;j++){
                for(int k = i ; k <= j;k++){
                    System.out.print(arr[k]+" ");
                }
                System.out.println();
            }
           // System.out.println();
        }
    }
}
