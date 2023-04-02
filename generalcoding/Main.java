
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main{
	
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for(int i=0; i < t; i++){
            int n = in.nextInt();
            int x = in.nextInt();
            int arr[] = new int[n];
            for(int j=0; j <n ; j++){
			    arr[j] = in.nextInt();
            }

            Arrays.sort(arr);

            int pre[] = new int[n];
            pre[0] = arr[0];
            for(int j=1; j < n; j++){
			    pre[j] = arr[j] + pre[j-1] ;
            }

			
			if(pre[n-1] == x){
                System.out.println("NO");
            }else{
                System.out.println("YES");
                for(int j=0; j < n; j++){
                    if(arr[j] == x){
                        int temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
                    System.out.print(arr[j]+" ");
                    x-=arr[j];
                }
                
                System.out.println();
            }
		
		}
		in.close();
	}
	
}
 
