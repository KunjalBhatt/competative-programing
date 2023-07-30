package competative.generalcoding.binartrees;

import java.util.ArrayList;
import java.util.List;

public class LeastCommonAccesstor {

    Node lca(Node root, int n1,int n2){

        if(root == null) return null;
        if(root.data  == n1 || root.data == n2) return root;

        Node left = lca(root.left, n1, n2);
        Node right = lca(root.right, n1, n2);

        if(left != null && right != null){
            return root;
        }else if(left != null){
            return left;
        }else if(right != null){
            return right;
        }else return null;
    }


    Node lcaBasic(Node root, int n1,int n2)
    {
        List<Node> ls1 = new ArrayList<>();
        List<Node> ls2 = new ArrayList<>();
        getPath(root, n1, ls1);


        getPath(root, n2, ls2);

	   /* System.out.println("In Main Method");
	    for(Node n : ls1){
	        System.out.print(n.data+" ");
	    }
	    System.out.println();

	    for(Node n : ls2){
	        System.out.print(n.data+" ");
	    }
	    System.out.println();*/

        int i=0, j=0;
        Node ans = null;
        while(i<ls1.size() && j < ls2.size()){
            if(ls1.get(i).data == ls2.get(j).data){
                ans = ls1.get(i);
            }
            i++;j++;
        }
        return ans;
    }


    boolean getPath(Node root, int val, List<Node> ls){


        if(root == null) return false;

        if(root.data == val) {
            //System.out.println(root.data);
            ls.add(root);
            /*for(Node n : ls){
    	        System.out.print(n.data+" ");
    	    }
    	    System.out.println();*/
            return true;
        }


        ls.add(root);

        boolean  l = getPath(root.left, val, ls);
        boolean  r = getPath(root.right, val, ls);

        if(!(l||r)){
            ls.remove(ls.size() - 1);
            return false;
        }else{
            return true;
        }

    }
}
