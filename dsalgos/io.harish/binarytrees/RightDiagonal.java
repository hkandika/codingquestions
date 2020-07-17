package binarytrees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *        
 *         
 *    Given a Binary Tree    
                1
              /   \
             2     3
            / \    / \
           4   7  5   6
                    /
                   8
                 /  \
               9     10
             /  \
           11
         /   \
       12    13 
       
       Right diagonals
       
       [
       		[1, 3, 6], 
       		[2, 7, 5, 8, 10], 
       		[4, 9], 
       		[11, 13], 
       		[12]
       ]
       
       

 * @author harish
  
 * @param args
 */
public class RightDiagonal {


     public static void main(String []args){
         /**
          *         1
                  /   \
                 2     3
                / \    / \
               4   7  5   6
                        /
                       8
                     /  \
                   9     10
                 /  \
               11
             /   \
           12    13 

          * */
         BinaryTree a = new BinaryTree(1);
         BinaryTree b = new BinaryTree(2);
         BinaryTree c = new BinaryTree(3);
         BinaryTree d = new BinaryTree(4);
         BinaryTree e = new BinaryTree(5);
         BinaryTree f = new BinaryTree(6);
         BinaryTree g = new BinaryTree(7);
         BinaryTree h = new BinaryTree(8);
         BinaryTree i = new BinaryTree(9);
         BinaryTree j = new BinaryTree(10);
         BinaryTree k = new BinaryTree(11);
         BinaryTree l = new BinaryTree(12);
         BinaryTree m = new BinaryTree(13);
         
         a.left = b;
         a.right = c;
         
         b.left = d;
         b.right = g;
         
         c.left = e;
         c.right = f;

         f.left = h;
         
         h.left = i;
         h.right = j;
         
         i.left = k;
         
         
         k.left = l;
         k.right = m;
        
        
        
        System.out.println("Output: " + getDiagonalElementsUsingQueueAndNullTrees(a)); // solution 1, uses null trees in the queue to identify end of diagonal 
        System.out.println("Output: " + getDiagonalElementsUsingQueueSize(a)); // solution 2, uses queue size to identify end of diagonal 
        System.out.println("Output: " + getDiagonalElementsUsingDistancesAndMap(a)); // solution 3, uses distances and map, root node is 0, all right childs has distance as root node and left childs distance is root nodes's distnace + 1 
            }
     
	 static class BinaryTree {
		    Integer val;
		    BinaryTree left;
		    BinaryTree right;
		 
		    BinaryTree(Integer val) {
		        this.val = val;
		    }
		    
		    public String toString() {
		        return "" + val;
		    }
	 }

     
	 /**
	  * Uses queue, inserts root first and NULL tree, NULL tree is to keep track on end of diagonal
	  * 
	  * Outer loop: on Queue
	  * Inner loop: on root node, root changes for each of OUTER loop
	  * 
	  * Each iteration of inner loop: Start from root and traverse through the depth of all right most childs on that root
	  * 							  Add right childs to list of diagonal elements including root and left childs to the queue, 
	  * 							  which will be used to construct next diagonal
	  * 
	  * In OUTER loop, before going to INNER loop we check for NULL tree in queue after dequeuing
	  * If NULL tree found it means we found all elements of one diagonal, add that to final result list 
	  * and re-intialize the diagonal list. 
	  * At this stage queue contains all nodes required to construct next diagonal because of previous INNER LOOP execution
	  * So insert NULL tree again to keep track of end of next diagonal
	  * deque immediately if we get NULL it means we have explored all diagonals so exit ELSE continue.
	  * 
	  * 
	  * O(N) time | O(N + log(N)) =  O(N) space, log(N) is frames on stack space due to recursion 
	  * N - number of nodes in the trees
	  * 
	  * @param root
	  * @return
	  */
     public static List<List<Integer>> getDiagonalElementsUsingQueueAndNullTrees(BinaryTree root) {

        List<List<Integer>> result = new ArrayList<>(); 
        Queue<BinaryTree> queue = new ArrayDeque<>();
        
        queue.add(root);
        queue.add(new BinaryTree(null));
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            //System.out.println(queue);
            root = queue.remove();
            if (root.val == null) {
                if (!list.isEmpty()) {
                    result.add(list);
                }
                queue.add(new BinaryTree(null));
                root = queue.remove();
                if (root == null) {
                    break;
                }
                list = new ArrayList<>();
            }
        
            while (root != null)  {
                if (root.left != null) {
                    queue.add(root.left);
                }
                list.add(root.val);
                root = root.right;
            }
            
            
        }

        return result;
     }
     
     /**
      * Uses queue size to indicate size of diagonal
      * O(N) time | O(N) space
      * N - number of nodes in the trees
      * @param root
      * @return
      */
     public static List<List<Integer>> getDiagonalElementsUsingQueueSize(BinaryTree root) {

         List<List<Integer>> result = new ArrayList<>(); 
         Queue<BinaryTree> queue = new ArrayDeque<>();
         
         queue.add(root);
         List<Integer> list = new ArrayList<>();
         while (!queue.isEmpty()) {
	         int loopSize = queue.size();
	         for (int i = 0; i < loopSize; i++) {
	        	 root = queue.remove();
	             while (root != null)  {
	                 if (root.left != null) {
	                     queue.add(root.left);
	                 }
	                 list.add(root.val);
	                 root = root.right;
	             }
	         }
	         result.add(list);
	         list = new ArrayList<>();
         }

         return result;
      }
     
     /**
      * Uses queue size to indicate size of diagonal
      * O(N + N) = O(N) time | O(N + log(N)) =  O(N) space, log(N) depth of tree, frames on stack space due to recursion 
      * N - number of nodes in the trees
      * @param root
      * @return
      */
     public static List<List<Integer>> getDiagonalElementsUsingDistancesAndMap(BinaryTree root) {
    	 
    	 Map<Integer, List<Integer>> nodesDiagonalDistanceMap = new HashMap<>();
    	 getDiagonals(root, nodesDiagonalDistanceMap, 0);
    	 return new ArrayList<List<Integer>>(nodesDiagonalDistanceMap.values());
     }
     
     public static void getDiagonals(BinaryTree root, 
    		 Map<Integer, List<Integer>> nodesDiagonalDistanceMap,
    		 int distance) {
    	 
    	 if (root == null) {
    		 return;
    	 }
    	 if (nodesDiagonalDistanceMap.containsKey(distance)) {
    		 nodesDiagonalDistanceMap.get(distance).add(root.val);
    	 } else {
    		 List<Integer> li = new ArrayList<>();
    		 li.add(root.val);
    		 nodesDiagonalDistanceMap.put(distance, li);
    	 }
    	 getDiagonals(root.right, nodesDiagonalDistanceMap, distance);
    	 getDiagonals(root.left, nodesDiagonalDistanceMap, distance + 1);
     }
}
