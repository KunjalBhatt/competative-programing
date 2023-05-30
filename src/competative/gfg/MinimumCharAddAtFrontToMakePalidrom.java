package competative.gfg;

import java.util.Arrays;

public class MinimumCharAddAtFrontToMakePalidrom {

    public static void main(String[] args) {
      //  System.out.println(Arrays.toString(findLps("nuuhxn")));
       // System.out.println(minChar("aacacaaa"));

        String s1 = "nuuhxdgjclvntslbrrmzmuufyfblxgmaegaisvvyedhfoohrouybzwosuwfmuvofckkdhjczroapgahajaabacjtpyjconfhmdamcdumsjlskhmcaeyqtpgoaairftxqtwjwdqfxxcjflskgdqbjiwunrqewnbjjrqulwafguffcnoeidjhgscjmkidhpgssmqmybhvqfpnamyqswfemhqfeyfrnmwhlkhdjajenvxmnayzmgvkhnmnhszzferplpohmtgyrbdedwbszqwgvzpevjjmvdwqqbtcfijaolfgugdjndbbgimgusovppiifodqgjvlexcdgsvnrzvliigucillqntsuqultdntfvutzhliwmcdmvmfjoxqrbrru";
        String s2 = "ngakrrjrgghmnqlcrhcslbvvlhywzzpjpeyieemfnqzbnnzlucstnimzoqnggztpppibeteixcclixqfzuqgxhfhlsrvoqwhujsjttcatmnjbzhksvydbrehgyrpijoimvbsghysyqwrmjfhyebakrzzzxceefqwthdmzxbilzslvgiziuqtqsqnhkeuabzusyztmkegnksegtiocldgolkntwyjhklcbekdeszqbdcbbfubkkzhdlfbnwhlvfypsgkamepfvlxigluaypwsubejjfpnnubznrerkwqjxqkubomvrdryfiulzylrtmjzpuayolrrowxnxnaihatrruwegivkhalojghusuzgumczxfzlwbyzluycmvmlifgy";

        System.out.println(Arrays.toString(longestCommonPrefix(s1, s2)));
    }

    public static int[] longestCommonPrefix(String s1, String s2){
        //code here
        int[] lps = findLps(s1);
        int[] ans = new int[2];

        int j=0;
        int i=0;
        while(j < s2.length()){

           // System.out.println(i+" "+j+" "+s2.charAt(j) +" " +s1.charAt(i) );
            if(s2.charAt(j) == s1.charAt(i)){
                System.out.println(i+" "+j+" "+s2.charAt(j) +" " +s1.charAt(i) );
                ans[1] = Math.max(ans[1], i);
                j++;
                i++;

            }else{
              //  j++;
                if(i > 0){
                    i=lps[i-1];
                }else{
                    j++;
                }

            }

            if(i == s1.length()){
                ans[1] = s1.length() - 1;
                break;
            }


        }

        return ans;
    }

    public static int minChar(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String rev = sb.toString();
        sb = new StringBuilder();
        String mergedString = sb.append(str).append("$").append(rev).toString();

        int[] lps = findLps(mergedString);

        return str.length() - lps[lps.length - 1];



    }


    public static int[] findLps(String s){

        char[] carr = s.toCharArray();
        int[] lps = new int[carr.length];

        int i=1;
        lps[0]=0;
        int len = 0;

        while(i< carr.length){

            if(carr[i] == carr[len]){
                len++;
                lps[i] = len;
                i++;
            }else{
                if(len > 0){
                    len = lps[len -1];
                }else{
                    lps[i] = len;
                    i++;
                }
            }

        }

        System.out.println(Arrays.toString(lps));

        return lps;

    }


    static void computeLPSArrayFromGfg(String pat) {
        // length of the previous longest prefix suffix
        int[] lps = new int[pat.length()];
        int M = lps.length;
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                } else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
        System.out.println(Arrays.toString(lps));
    }
}
