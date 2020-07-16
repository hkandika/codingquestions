package LinkedLists;

import java.util.Arrays;
import java.util.List;

/**
 * Reversing a linked list between start end end index of an input linked list
 * 
 * Example
 *  
 * Input: 
 * 1 -> 2 -> 3 -> 4 -> 5
 * startIdx = 2
 * endIdx = 4
 * 
 * Output: 1 -> 4 -> 3 -> 2 -> 5
 * 
 * 
 * @author harish
 *
 */
public class ReverseLinkedListBetweenNodes {

	public static void main(String[] args) {
		//test run
		LinkedList one = new LinkedList(1);
		LinkedList two = new LinkedList(2);
		LinkedList three = new LinkedList(3);
		LinkedList four = new LinkedList(4);
		LinkedList five = new LinkedList(5);
		
		//testcase 1
		one.next =two;
		two.next = three;
		three.next = four;
		four.next = five;
		print("Output: " , reverseBetween(one, 2 ,4)); //reverse list from second to 4th element
		System.out.println();

		//testcase 2
		one.next =two;
		two.next = three;
		three.next = four;
		four.next = five;
		print("Output: " , reverseBetween(one, 2 ,2)); //nothing to reverse as start and end are same
		System.out.println();
		
		//testcase 3
		one.next =two;
		two.next = three;
		three.next = four;
		four.next = five;
		print("Output: " , reverseBetween(one, 1 ,5)); //reverse whole list
		System.out.println();
	}
	static class LinkedList {
		public int val;
		public LinkedList next;
		LinkedList(int x) { val = x; next = null; }
		 
	}
    public static LinkedList reverseBetween(LinkedList head, int startIdx, int endIdx) {
    	if (startIdx == endIdx) return head;
        print("HEAD: ", head); 
        LinkedList listBeforeStartIdx = null;
        LinkedList listAfterEndIdx = null;
        LinkedList node = head;
        LinkedList prev = null;
        LinkedList reversingList =  null;
        LinkedList result =  null;
        int i=1;
        while (node != null) {
            if (startIdx == i) {
                listBeforeStartIdx = prev;
                if (listBeforeStartIdx != null) {
                    listBeforeStartIdx.next = null;
                }
                reversingList = node;
            } else if (endIdx == i) {
                listAfterEndIdx = node.next;
                node.next = null;
            }
            prev = node;
            node = node.next;
            i++;
        }
        print("listBeforeStartIdx: ", listBeforeStartIdx);
        print("listAfterEndIdx: ", listAfterEndIdx);
        List<LinkedList> nodes = reverseLinkedList(reversingList, null);
        
        if (listBeforeStartIdx != null) {
            listBeforeStartIdx.next = nodes.get(0);
            result = head;
        } else {
            result = nodes.get(0);
        }
        if (listAfterEndIdx != null) {
        	nodes.get(1).next = listAfterEndIdx;
        }
        return result;
    }
    
    public static void print(String str, LinkedList node) {
    	System.out.print(str + " ");
        while(node != null) {
            System.out.print(node.val + " -> "); //ignore last append of ->
            node = node.next;
        }
        
        System.out.println();
    }
    
    //core logic of reversing linked list
    public static List<LinkedList> reverseLinkedList(LinkedList node, LinkedList prev) {
        LinkedList p1 = null;
        LinkedList p2 = node;
        LinkedList tail = node;
        while (p2 != null) {
            LinkedList p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        return Arrays.asList(p1, tail);
    }

}
