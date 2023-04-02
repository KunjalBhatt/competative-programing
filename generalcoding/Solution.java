import java.util.*;
import java.util.regex.*;


// Write your code here. DO NOT use an access modifier in your class declaration.

class Parser {
    
    public boolean isBalanced(String s){
        char[] carr = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for(char c : carr){
            if(c == '{' || c== '('){
                stack.push(c);
            }else{
                if(stack.pop() != c){
                    return false;
                }
            }
            
        }
        
        return true;
    }
    
}

class Solution {
	
	public static void main(String[] args) {
		Parser parser = new Parser();
        
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			System.out.println(parser.isBalanced(in.next()));
		}
        
		in.close();
	}
}
