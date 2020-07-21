package binarytrees;

/**
 * 
 * Example
 * 
 *          -1
 *        /   \
 *       2     9
 *     /  \     \
 *   4    5      -1
 *              /  \
 *             -4   6
 * 
 * Output: {13=[6, -1, 9, -1]}  
 *  13 is maxsum from root node
 *  [6, -1, 9, -1] max sum path elements from root node
 *  
 *  Complexity: O(n) time | O(n) space
 * */
import java.util.*;

public class MaxSumPath
{
	public static void main(String[] args) {
		Node minusone1 = new Node(-1);
		Node minusone2 = new Node(-1);
		Node two = new Node(2);
		Node nine = new Node(9);
		Node four = new Node(4);
		Node five = new Node(5);
		Node minusfour = new Node(-4);
		Node six = new Node(6);
		
		minusone1.left = two;
		minusone1.right = nine;
		two.left = four;
		two.right = five;
		
		nine.right = minusone2;
		minusone2.left = minusfour;
		minusone2.right = six;
		
		System.out.println(maxSumPath(minusone1));
		
		
	}
	
	public static Map<Integer, List<Integer>> maxSumPath(Node root) {
	    if (root == null) {
	        return new HashMap<>();
	    }
	    Map<Integer, List<Integer>> leftPathMap = maxSumPath(root.left);
	    Map<Integer, List<Integer>> righPathMap = maxSumPath(root.right);
	    if (leftPathMap.isEmpty() && righPathMap.isEmpty()) {
	        List<Integer> list = new ArrayList<>();
	        list.add(root.val);
	        leftPathMap.put(root.val, list);
	        return leftPathMap;
	    } else if (!leftPathMap.isEmpty() && righPathMap.isEmpty()) {
	        return computeMaxSumMap(leftPathMap, root.val);
	    } else if (leftPathMap.isEmpty() && !righPathMap.isEmpty()) {
	        return computeMaxSumMap(righPathMap, root.val);
	    } else if (leftPathMap.keySet().iterator().next() > righPathMap.keySet().iterator().next()) {
	        return computeMaxSumMap(leftPathMap, root.val);
	    } else {
	        return computeMaxSumMap(righPathMap, root.val);
	    }
	}
	
	private static Map<Integer, List<Integer>> computeMaxSumMap(Map<Integer, List<Integer>> map, int rootvalue) {
        List<Integer> pathList = map.get(map.keySet().iterator().next());
        int max = map.keySet().iterator().next();
        pathList.add(rootvalue);
        map.remove(map.keySet().iterator().next());
        map.put(max + rootvalue, pathList);
        return map;
	}
	
	private static class Node {
	    int val;		
	    Node left;		
	    Node right;	
	    public Node(int val) {			
	        this.val = val;		
	    }	
	}

}

