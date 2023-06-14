package competative.generalcoding.linkedlist;

import java.util.LinkedList;

public class BasicLinkedListQuestions {
    public static void main(String[] args) {

        Node n8 = new Node(8, null);
        Node n7 = new Node(7, n8);
        Node n6 = new Node(6, n7);
        Node n5 = new Node(5, n6);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);

        Node head = n1;

        print(head);
        //Node rev = reverseLinkedList(head);
        Node rev = reverseLLRecursive(head);
        print(rev);

    }

    public static Node reverseLinkedList(Node head){

        Node cur = head;
        Node next = null;
        Node prev = null;

        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public static Node reverseLLRecursive(Node head){

        if(head == null) return null;
        if(head.next == null) return head;

        Node n = reverseLLRecursive(head.next);
       // print(head);
        head.next.next=head;
        head.next = null;
        return n;
    }

    public static void print(Node head){
        Node temp = head;
        while(temp !=null){
            System.out.print(temp.val+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static Node reverseInKGroup(Node node, int k)
    {

        return node;
    }
}
