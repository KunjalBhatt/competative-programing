package competative.generalcoding.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxMeetings {
    public static void main(String[] args) {
        int[]  start = {1,3,0,5,8,5};
        int[]  end = {2,4 ,6, 7, 9, 9};
        System.out.println(maxMeetings(start,end,start.length));
    }

    public static int maxMeetings(int start[], int end[], int n)
    {
        if(n < 1) return 0;
        int ans = 1;
        List<Meeting> ls = new ArrayList<>();
        for(int i=0; i<n;i++){
            ls.add(new Meeting(start[i],end[i]));
        }

        ls.sort((m1, m2) -> Integer.compare(m1.end, m2.end));

        Meeting prev = ls.get(0);
        for(int i=1; i<n;i++){
            if(ls.get(i).start > prev.end){
                ans++;
                prev = ls.get(i);
            }

        }
        return ans;
    }

    static class Meeting{
        int start;
        int end;

        Meeting(int start, int end){
            this.start= start;
            this.end = end;
        }
    }
}
