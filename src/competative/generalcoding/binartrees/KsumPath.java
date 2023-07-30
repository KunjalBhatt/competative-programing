package competative.generalcoding.binartrees;

import java.util.*;

public class KsumPath {

    public static void main(String[] args) {


    }
    int paths = 0;
    public int sumK(Node root,int k)
    {
        List<Integer> list = new ArrayList<>();
        countPath(root, k, list);

        return paths;
    }

    void countPath(Node root,int k, List<Integer> list){

        if(root == null) return;

        list.add(root.data);

        countPath(root.left, k, list);
        countPath(root.right, k, list);

        int sum = 0;
        for(int i= list.size() - 1; i>=0; i--){
            sum+=list.get(i);
            if(sum == k){
                paths++;
            }
        }
        list.remove(list.size() - 1);

    }
}
