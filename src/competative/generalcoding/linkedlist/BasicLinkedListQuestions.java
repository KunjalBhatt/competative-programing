package competative.generalcoding.linkedlist;

import competative.generalcoding.sorting.MergeSort;

import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

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

        //n3.next = n1;

        //print(middle(null));

        Node digit3 = new Node(9, null);
        Node digit2 = new Node(2, digit3);
        Node digit1 = new Node(4, digit2);

        Node d3 = new Node(9, null);
        Node d2 = new Node(4, d3);
        Node d1 = new Node(1, d2);

        Node head = n1;

        print(head);
        System.out.println("method start");
        Node ans = segregateLLOddEven(head);
        System.out.println("Ans ");
        print(ans);

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

    public static void print(Node... head){
        for(Node n : head){
            print(n);
        }
    }

    public static Node reverseInKGroup(Node node, int k)
    {
        if(node == null) return null;
        Node cur  = node;
        Node prev = null;
        Node next = null;
        Node ans = null;
        int count = 0;

        boolean isFirst = true;
        while(cur != null && count < k){

            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            count++;

        }

        if(next != null){
            node.next = reverseInKGroup(next,k);
        }

        return prev;
    }

    public static boolean detectLoop(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }


    public static Node startPointLoop(Node head){
        Node slow = head;
        Node fast = head;
        Node prev  = null;

        boolean isLoop = false;

        while(fast != null && fast.next != null){
            slow = slow.next;
            prev = fast.next;
            fast = fast.next.next;

            if(slow == fast){
                isLoop = true;
                break;
            }
        }

        if(isLoop){
            slow = head;
            while(slow != fast){
                slow = slow.next;
                prev = fast;
                fast = fast.next;

            }
            prev.next = null;
            return prev;
        }

        return null;
    }

    //Function to remove duplicates from sorted linked list.
    public static Node removeSortedDuplicates(Node head){
        Node cur = head;
        Node prev = null;
        while(cur != null){
            if(prev != null && cur.val == prev.val){
                prev.next = cur.next;
            }else{
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static Node removeDuplicates(Node head){
        Node cur = head;
        Node prev = null;
        Set<Integer> set = new HashSet<>();
        while(cur != null){
            if(set.contains(cur.val)){
                prev.next = cur.next;
            }else{
                set.add(cur.val);
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static Node moveLastElementToFirst(Node head){
        if(head == null) return null;
        Node cur = head;
        Node prev = null;
        while(cur.next != null) {
            prev = cur;
            cur = cur.next;
        }
        if(prev == null) return head;

        prev.next = null;
        cur.next = head;

        return cur;

    }

    public static Node addOne(Node head) {
        if(head == null) return null;
        head = reverseLinkedList(head);

        Node cur = head;
        Node prev = null;
        while(cur != null && cur.val == 9){
            cur.val = 0;
            prev = cur;
            cur = cur.next;
        }

        if(cur == null){
            prev.next = new Node(1,null);
        }else{
            cur.val +=1;
        }


        head = reverseLinkedList(head);
        return head;
    }

    static Node addTwoLists(Node first, Node second){
        first = reverseLinkedList(first);
        second = reverseLinkedList(second);

        Node head = null;
        Node prev = null;
        int carry = 0;
        while(first != null && second != null){
            int ans = first.val + second.val + carry;

            carry = ans / 10;
            ans %= 10;

            Node temp = new Node(ans, null);
            if(prev == null){
                head = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
            first = first.next;
            second = second.next;

        }

        while(first != null){
            int ans = first.val + carry;
            carry = ans / 10;
            ans %= 10;

            Node temp = new Node(ans, null);
            if(prev == null){
                head = temp;
            }else{
                prev.next = temp;
            }

            prev = temp;
            first = first.next;
        }

        while(second != null){
            int ans = second.val + carry;

            carry = ans / 10;
            ans %= 10;

            Node temp = new Node(ans, null);
            if(prev == null){
                head = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
            second = second.next;
        }
        if(carry > 0){
            Node temp = new Node(carry, null);
            prev.next = temp;
        }

        head = reverseLinkedList(head);

        return head;
    }

    public static Node findIntersectionSortedLL(Node head1, Node head2) {
        Node head = null;
        Node prev = null;

        while(head1 != null && head2 != null){
            Node temp = null;
            if(head1.val == head2.val){
                temp = new Node(head1.val, null);
                if(prev == null){
                    head = temp;
                }else{
                    prev.next = temp;
                }
                prev = temp;
                head1 = head1.next;
                head2 = head2.next;
            }else if(head1.val < head2.val){
                head1 = head1.next;
            }else{
                head2 = head2.next;
            }


        }

        return head.next;
    }

    public static Node mergeSort(Node head){

        if(head == null || head.next == null) return head;

        Node mid = getMiddle(head);
        Node head2 = mid.next;
        mid.next = null;
       // print(head);
        Node left = mergeSort(head);
        Node right = mergeSort(head2);


        return merge(left, right);
    }

    public static Node merge(Node head1, Node head2){
        Node head = null;
        Node prev = null;

//        if (head1 == null)
//            return head2;
//        if (head2 == null)
//            return head1;

        while(head1 != null && head2 != null){
            Node temp = null;
            if(head1.val <= head2.val){
                temp = head1;
                head1 = head1.next;
            }else{
                temp  = head2;
                head2 = head2.next;
            }
            if(prev == null){
                head = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
        }
        while(head1 != null){
            Node temp = head1;
            if(prev == null){
                head = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
            head1 = head1.next;
        }
        while(head2 != null){
            Node temp = head2;
            if(prev == null){
                head = temp;
            }else{
                prev.next = temp;
            }
            prev = temp;
            head2 = head2.next;
        }
      //  print(head);
        return head;
    }


    @Deprecated
    public static Node middle(Node head){
        if (head == null)
            return head;
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node getMiddle(Node head)
    {
        if (head == null)
            return head;

        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static void divideCircular(Node head){

        breakCircular(head);
        print(head);
        Node mid = middleV2(head);
        Node head2 = mid.next;
        mid.next = null;


        print(head,head2);

    }

    static void breakCircular(Node head){

        Node slow = head;
        Node fast = head;
        Node prev = null;
        boolean isLoop = false;
        while(fast != null && fast.next != null){
            slow = slow.next;
            prev = fast.next;
            fast = fast.next.next;
            if(slow == fast){
                isLoop = true;
                break;
            }
        }

        if(isLoop){
            slow = head;
            while(slow != fast){
                slow = slow.next;
                prev = fast;
                fast = fast.next;
            }
            prev.next = null;
        }

    }

    static Node middleV2(Node head){

        if(head == null) return null;

        Node slow = head;
        Node fast = head;


        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    static boolean isPalidromWithStack(Node head){

        Stack<Node> stack = new Stack<>();

        Node cur = head;

        while(cur != null){
            stack.add(cur);
            cur = cur.next;
        }
        cur = head;
        while(!stack.isEmpty()){

            if(cur.val == stack.peek().val){

                System.out.println(cur.val + " matched "+stack.peek().val);
                stack.pop();
                cur = cur.next;
            }else{
                System.out.println(cur.val + " "+stack.peek().val);
                return false;
            }
        }

        return true;
    }


    static boolean isPalidrom(Node head){
        Node mid = middleV2(head);
        Node head2 = mid.next;
        mid.next = null;
        head2 = reverseLinkedList(head2);

        Node temp1 = head;
        Node temp2 = head2;

       boolean isPalidrom = true;
       while(temp1 != null && temp2 != null){
            if(temp1.val != temp2.val){
                isPalidrom = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        head2 = reverseLinkedList(head2);
        mid.next = head2;

        return isPalidrom;
    }

    public static long multiplyTwoLists(Node l1,Node l2){
        long ans = 0;

        l1 = reverseLinkedList(l1);
        l2 = reverseLinkedList(l2);
        Node head2 = l2;
        int base = 1;
        while(l1 != null){
            int base2 = 1;
            int sum = 0;
            l2 = head2;
            while(l2 != null){
                int l2Digit = l1.val * l2.val * base2;
                sum  = (sum + l2Digit)% 1000000007;
            //    System.out.println(l1.val +" "+l2.val+" "+ base2 + " " + l2Digit +" " +sum);
                base2*=10;
                l2 = l2.next;
            }
            sum*=base;
            base*=10;
          //  System.out.println("sum = "+sum);
            ans = (ans + sum) % 1000000007;
            l1 = l1.next;
        }
        return ans;
    }

    public static Node segregateLLOddEven(Node head){


        Node oddTail = new Node(-1,null);
        Node evenTail = new Node(-1, null);
        Node oddHead = oddTail;
        Node evenHead = evenTail;
        Node cur = head;
        while(cur != null){

            if(cur.val % 2 == 0){

                evenTail.next = cur;
                evenTail  = cur;
            }else{
               oddTail.next = cur;
               oddTail = cur;
            }
            cur = cur.next;
        }

        evenTail.next = oddHead.next;
        oddTail.next = null;
        return evenHead.next;
    }

}
