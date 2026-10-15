package utilities;

import java.util.Comparator;

public class QuickSort {
	
	/**
     * Starts the sorting process for the entire array.
     *
     * @param array The array of objects to sort
     * @param comparator Determines how objects are compared
     */
	
	public static <T> void sort(T[] array, Comparator<? super T> comparator) {
		
		if (array.length > 1) {
			quickSort(array, 0, array.length - 1, comparator);
		}
	}
	
	// Recursively sorts the left and right partitions
	private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
		
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
     * Selects the median of the first, middle and last elements.
     * Places the selected pivot at the end of the section.
     */
	private static <T> void medianOfThree(T[] array, int low, int high, Comparator<? super T> comparator) {
		
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
     * Partitions the array in descending order.
     *
     * @return The final position of the pivot
     */
	
	private static <T> int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
		
		// Use Median-of-Three when there are at least 3 elements.
		if (high - low >= 2) {
		    medianOfThree(array, low, high, comparator);
		}
        
        T pivot = array[high];
        
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
     * Swaps two elements in an array.
     *
     * @param array The array containing the elements.
     * @param first Index of the first element.
     * @param second Index of the second element.
     */
    private static <T> void swap(T[] array, int first, int second) {

    	// Save the first element in a temporary Variable
        T temp = array[first];
        
        // Copy the second element into the first elements position
        array[first] = array[second];
        
        // Place the original first element into the second elements position
        array[second] = temp;
    }
}

