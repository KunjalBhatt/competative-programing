package competative.generalcoding.backtrack;

import java.util.ArrayList;

public class ShortestSafeRouteInFieldWithLandmines {
    public static void main(String[] args) {

    }


    static int min = Integer.MAX_VALUE;
    public static int shortestPath(ArrayList<ArrayList<Integer>> field)
    {
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        for(int i=0; i<field.size(); i++){
            for(int j=0; j<field.get(i).size(); j++){
                if (field.get(i).get(j) == 0)
                {

                    for(int k = 0; k < dx.length; k++){
                        int x = i+dx[k];
                        int y = j+dy[k];
                        if(isValid(x, y, field) && field.get(x).get(y) == 1){
                            field.get(x).set(y, -1);
                        }
                    }
                }
            }
        }
       /* for(ArrayList<Integer> row : field){
            System.out.println(row);
        }*/
        min = Integer.MAX_VALUE;
        boolean[][] isVisted = new boolean[field.size()][field.get(0).size()];
        for(int i=0; i<field.size(); i++){
            shortestPathHelper(i, 0, 0, field,isVisted);
        }
        return min ==Integer.MAX_VALUE ? -1 : min ;
    }

    static void shortestPathHelper(int i, int j, int steps, ArrayList<ArrayList<Integer>> field,boolean[][] isVisted){
        if(i < 0 || i >= field.size() || j < 0 || j >= field.get(i).size()) return;
        if(steps > min) return;
        if(field.get(i).get(j) == 1){

            if( j == field.get(i).size() - 1){
                if(min > steps){
                    min = steps;
                }
            }
            if(isVisted[i][j]) return;

            isVisted[i][j]=true;
            shortestPathHelper(i+1, j, steps+1, field,isVisted);
            shortestPathHelper(i-1, j, steps+1, field,isVisted);
            shortestPathHelper(i, j+1, steps+1, field,isVisted);
            shortestPathHelper(i, j-1, steps+1, field,isVisted);
            isVisted[i][j]=false;
        }

    }

    static boolean isValid(int i, int j, ArrayList<ArrayList<Integer>> field){
        if(i < 0 || i >= field.size() || j < 0 || j >= field.get(i).size()) return false;
        return true;
    }
}
