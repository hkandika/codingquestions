package sort;

import java.util.Arrays;

/**
 * In-memory heap sort
 * 
 * @author harish
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		
		int [] array = {20, 16, 1, 5, 9 ,13, 18, 9, 4, 9, 5};
		MaxHeapUtil.sort(array);
		System.out.println(Arrays.toString(array));

	}
	
	static class MaxHeapUtil {
		public static void sort(int [] array) {
			maxHeapify(array);
			for (int i = array.length - 1; i >= 0; i--) {
				int max = array[0];
				array[0] = array[i];
				shiftDown(array, 0, i);
				array[i] = max;
			}
		}
		public static void maxHeapify(int [] array) {
			int firstParentIdx = array.length - 2;
			for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
				shiftDown(array, currentIdx, array.length - 1);
			}
		}
	
		private static void swap(int []array, int oneIdx, int twoIdx) {
			int temp = array[oneIdx];
			array[oneIdx] = array[twoIdx];
			array[twoIdx] = temp;
		}
		
		private static void shiftDown(int [] array, int currentIdx, int endIdx) {
			while (currentIdx <= endIdx) {
				int childOneIdx = (2 * currentIdx) + 1;
				if (childOneIdx > endIdx) return;
				int childTwoIdx = (2 * currentIdx) + 2;
				int childIdxToSwap;
				if (childTwoIdx > endIdx) {
					childIdxToSwap = childOneIdx;
				} else {
					childIdxToSwap = array[childOneIdx] > array[childTwoIdx] ? childOneIdx : childTwoIdx;
				}
				if (array[currentIdx] < array[childIdxToSwap]) {
					swap(array, currentIdx, childIdxToSwap);
					currentIdx = childIdxToSwap;
				} else {
					return;
				}
			}
		}
		
	}
}
