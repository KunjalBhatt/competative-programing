package competative.generalcoding.binarysearch;

import competative.generalcoding.PointG;

import java.util.Arrays;
import java.util.Scanner;

public class KthSmallestNumber {

   /* public static void main(String[] args) {
        PointG<Long>[] parr = new PointG[4];
        parr[0] = new PointG<Long>(1L,4L);
        parr[1] = new PointG<Long>(8L,10L);
        parr[2] = new PointG<Long>(12L,20L);
        parr[3] = new PointG<Long>(40L,50L);

        System.out.println(findUsingBS(parr,1));
    }*/

    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here


        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while(t-- > 0){
            int n = s.nextInt();
            int q = s.nextInt();

            PointG<Long>[] parr = new PointG[n];

            for(int i=0; i<n; i++){
                parr[i] = new PointG<Long>(s.nextLong(),s.nextLong());
            }

            Arrays.sort(parr, (p1, p2) -> {
                if(p1.x < p2.x){
                    return -1;
                }else if (p1.x > p2.x){
                    return 1;
                }else{
                    return 0;
                }
            });
            int index = 0;
            while(q-- > 0){
                long orinialk = s.nextLong();
                long k = orinialk;


                System.out.println("Ans="+findUsingBS(parr,k));

               /* long prev = parr.length > 0 ? parr[0].x : 0;
                boolean isFound = false;
                StringBuffer  sb = new StringBuffer();
                for(PointG<Long> p : parr){
                    long start = Math.max(prev, p.x);
                    long end = p.y;

                    long len = (end - start) +1;
                    if(len < 1) continue;
                    sb.append("len =").append(len).append(" x =").append(p.x).append(", y=").append(p.y).append(", taken x = ").append(start).append(", taken y = ").append(end).append("\n");
                    if(k > len){
                        k-=len;
                        prev = end+1;
                    }else{
                        if(end >= (start + (k-1))){
                            System.out.println(start+(k-1));
                            if(orinialk == 940){
                                System.out.println("k is ="+orinialk);
                                System.out.println(Arrays.toString(parr));
                                System.out.println(sb.toString());
                            }
                            isFound = true;
                        }
                        break;
                    }
                }
                if(!isFound){
                    System.out.println(-1);
                }*/

            }

        }


    }

    static long findUsingBS(PointG<Long>[] range, long value){

        System.out.println("-----------------------------------------");
        int i = 0;
        int j = 0;
        while(j<range.length){
            if(range[i].y >= range[j].x){
                range[i].y = Math.max(range[i].y,range[j].y);
            }else{
                i++;
                range[i] = range[j];
            }
            j++;
        }
        int validRange = i+1;


        long[] ps = new long[validRange+1];
        for(int k=0; k<validRange;k++){
            ps[k+1] = (range[k].y - range[k].x) + 1 + ps[k];
        }

        if(ps[validRange] < value) return -1;
        System.out.println(Arrays.toString(range));
        System.out.println(value+" "+Arrays.toString(ps));

        int start = 0;
        int end = validRange;

        while(start < end){

            int mid = start + (end-start)/2;

            if(ps[mid] < value){
                start=mid+1;
            }else{
                end = mid;
            }

        }
        System.out.println(ps[start]);
        start--;

        return  (range[start].x - 1) + (value-ps[start]);


    }
}


