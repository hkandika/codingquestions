package arrays;

import java.util.ArrayList;
import java.util.List;
/**
 *  Spiral Traversal of a matrix
 *  
 * @author harish
 *
 */
public class SpiralTraverseMatrix {

	public static void main(String[] args) {
	
		//input 
		int [][] matrix = new int [][] {
			{1,2 ,3 ,4},
			{12,13 ,14 ,5},
			{11,16 ,15 ,6},
			{10,9 ,8 ,7},
			{10,9 ,8 ,7}
		};

		System.out.println(spiralTraverse(matrix));
		//output [1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 8, 9, 16]
	}
	
	/**
	 *           SC      EC
          1  2  3  4
 SR  	  12 13 14 5
 ER   	  11 16 15 6
          10 9  8  7


          SC  EC
       
SR   	     13 14 
ER    	   16 15 

	 * @param matrix
	 */
	public static List<Integer> spiralTraverse(int [][] matrix) {
		  List<Integer> result = new ArrayList<>();
		  int startRow = 0;
		  int endRow = matrix.length - 1;
		  int startCol = 0;
		  int endCol = matrix[0].length - 1;
		  
		  
		  while (startRow < endRow && startCol < endCol) {
		  	
		    for (int col = startCol; col <= endCol; col++) {
		    	result.add(matrix[startRow][col]);
		    }
		    
		    for (int row = startRow + 1; row <= endRow; row++) {
		    	result.add(matrix[row][endCol]);
		    }
		  
		    for (int col = endCol - 1; col >= startCol; col--) {
		    	result.add(matrix[endRow][col]);
		    }
		  
		    for (int row = endRow - 1; row >= startRow + 1; row--) {
		    	result.add(matrix[row][startCol]);
		    }
		    
		    startRow++;
		    endRow--;
		    startCol++;
		    endCol--;
		  }
		  
		  return result;

		
	}
}
