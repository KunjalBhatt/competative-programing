package competative.leetcode;

public class CollectCherries {

    public int cherryPickup(int[][] grid) {
        return getRobotMax(grid, 0, 0, true);
    }

    int getRobotMax(int[][] grid, int i, int j, boolean isFirst){
        if(isFirst && i == grid.length - 1 && j >=0 && j < grid[i].length ){
            return grid[i][j] + getRobotMax(grid, 0, grid[i].length - 1, false);
        }
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length){
            return 0;
        }
        int val = grid[i][j];
        grid[i][j] = 0;
        int firstVal = getRobotMax(grid,i+1,j-1,isFirst);
        int secondVal = getRobotMax(grid,i+1,j,isFirst);
        int thirdVal = getRobotMax(grid,i+1,j+1,isFirst);
        int res;
        if(firstVal > secondVal && firstVal > thirdVal){
            res = firstVal;
            System.out.print((isFirst ? "Robot 1 " : "Robot 2 ") + (i+1)+"-"+(j-1));
        }else if(secondVal > firstVal && secondVal > thirdVal ){
            res = secondVal;

        }else{
            res = thirdVal;
        }

        grid[i][j] = val;
        return res + val;
    }

    public static void main(String[] args) {
        int[][] grid = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        CollectCherries cc = new CollectCherries();
        System.out.println(cc.cherryPickup(grid));
    }
}
