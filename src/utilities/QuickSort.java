package utilities;

import java.util.Comparator;
import products.Products;

/**
 * Implements the Quick Sort algorithm using Median-of-Three
 * pivot selection.
 *
 * Sorts arrays in descending order according to the
 * supplied Comparator.
 *
 * Uses recursion to divide and sort smaller partitions
 * of the original array.
 */

public class QuickSort {
	
	/**
     * Starts the sorting process for the entire array.
     *
     * @param array The array of objects to sort
     * @param comparator Determines how objects are compared
     */
	
	public static void sort(Products[] array, Comparator<Products> comparator) {
		
		if (array.length > 1) {
			quickSort(array, 0, array.length - 1, comparator);
		}
	}
	
	/**
	 * Recursively sorts sections of the array in descending order
	 * using the Quick Sort algorithm.
	 *
	 * Divides the array around a selected pivot and recursively
	 * sorts the elements on either side of that pivot.
	 *
	 * Recursion stops when the current section contains
	 * zero or one element.
	 *
	 * @param array The array containing the elements to sort.
	 * @param low The starting index of the current section.
	 * @param high The ending index of the current section.
	 * @param comparator Determines how elements are compared
	 *                   during sorting.
	 */
	
	private static void quickSort(Products[] array, int low, int high, Comparator<Products> comparator) {
		
		// Stops when the section has 0 or one element
		if (low < high) {
			
			// Partition the array and find the pivots final index
			int pivotIndex = partition(array, low, high, comparator);
			
			// Recursively sort everything to the left of the pivot
			quickSort(array, low, pivotIndex - 1, comparator);
			
			// Recursively sort everything to the right of the pivot
			quickSort(array, pivotIndex + 1, high, comparator);
		}
	}
	

	/**
	 * Selects the median of three elements to use as the Quick Sort pivot.
	 *
	 * Compares the first, middle, and last elements of the current
	 * array section using the supplied Comparator.
	 *
	 * The three elements are arranged in descending order,
	 * placing the median at the middle index.
	 *
	 * The median is then swapped with the last element so
	 * that the partition method can use it as the pivot.
	 *
	 * @param array The array containing the elements.
	 * @param low The starting index of the current section.
	 * @param high The ending index of the current section.
	 * @param comparator Determines how elements are compared.
	 */

	private static void medianOfThree(Products[] array, int low, int high, Comparator<Products> comparator) {
		
		// Find the middle index without adding low and high directly.
		int middle = low + (high - low) / 2;
		
		// Arrange the three candidates in descending order.
		if (comparator.compare(array[low], array[middle]) < 0) {
			swap(array, low, middle);
		}
		
		if (comparator.compare(array[low], array[high]) < 0) {
			swap(array, low, high);
		}
		
		if (comparator.compare(array[middle], array[high]) < 0) {
			swap(array, middle, high);
		}
		
		// The median is now at the middle index.
        // Move it to high so partition() can use it as the pivot.
		swap(array, middle, high);
	}
	
	/**
	 * Partitions a section of the array around a pivot selected
	 * using Median-of-Three when at least three elements exist.
	 *
	 * Places elements greater than the pivot on the left
	 * and smaller or equal elements on the right.
	 *
	 * @param array The array being partitioned.
	 * @param low The starting index of the partition.
	 * @param high The ending index of the partition.
	 * @param comparator Determines the ordering of elements.
	 * @return The final index of the pivot after partitioning.
	 */
	
	private static int partition(Products[] array, int low, int high, Comparator<Products> comparator) {
		
		// Use Median-of-Three when there are at least 3 elements.
		if (high - low >= 2) {
		    medianOfThree(array, low, high, comparator);
		}
        
        Products pivot = array[high];
        
        // Tracks the boundary of values greater than the pivot.
        int i = low - 1;

        // Examine each value before the pivot.
        for (int j = low; j < high; j++) {

            // A positive comparison means array[j] is greater.
            // Greater values belong on the left for descending order.
            if (comparator.compare(array[j], pivot) > 0) {
                i++;
                swap(array, i, j);
            }
        }

        // Place the pivot into its correct sorted position.
        swap(array, i + 1, high);

        // Return the pivot's final index.
        return i + 1;
    }
    
	/**
	 * Swaps two elements within an array.
	 *
	 * Uses a temporary variable to preserve the first element
	 * while exchanging the values at the specified indexes.
	 *
	 * @param array The array containing the elements.
	 * @param first The index of the first element.
	 * @param second The index of the second element.
	 */
	
    private static void swap(Products[] array, int first, int second) {

    	// Save the first element in a temporary Variable
        Products temp = array[first];
        
        // Copy the second element into the first elements position
        array[first] = array[second];
        
        // Place the original first element into the second elements position
        array[second] = temp;
    }
}

