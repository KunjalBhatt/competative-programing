package competative.generalcoding.binartrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void main(String[] args) {

    }

    static ArrayList<Integer> levelOrder(Node node)
    {
        ArrayList<Integer> ls = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();

        queue.add(node);

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            ls.add(cur.data);

            if(cur.left != null) queue.add(cur.left);
            if(cur.right!=null) queue.add(cur.right);
        }



        return ls;
    }

    static int height(Node node){
        // code here

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height =  0;
        while(!queue.isEmpty()) {
            int size =queue.size();
            while(size-- > 0){
                Node cur = queue.poll();
                if(cur.left != null) {
                    queue.add(cur.left);
                   }
                if(cur.right!=null) {
                    queue.add(cur.right);
                }
            }
            height++;
        }
        return height;
    }
}
