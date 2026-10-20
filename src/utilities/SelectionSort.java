package utilities;

import java.util.Comparator;
import products.Products;

/** Sorts products in descending order using Selection Sort. */
public class SelectionSort {

	/**
	 * Sorts an array of products in descending order
	 * using the Selection Sort algorithm.
	 *
	 * Searches the unsorted portion of the array to find
	 * the largest product according to the Comparator.
	 *
	 * The largest product is then swapped into the next
	 * available position in the sorted portion.
	 *
	 * This process repeats until the entire array is sorted.
	 *
	 * @param products The array of products to be sorted.
	 * @param comparator Determines how products are compared
	 *        when finding the largest value.
	 */

    public static void sort(
            Products[] products,
            Comparator<Products> comparator) {
        for (int i = 0; i < products.length - 1; i++) {
            int maxIndex = i;

            // Find the largest product in the unsorted portion.
            for (int j = i + 1; j < products.length; j++) {
                if (comparator.compare(
                        products[j], products[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            // Move the largest product to its correct position.
            if (maxIndex != i) {
                Products temp = products[i];
                products[i] = products[maxIndex];
                products[maxIndex] = temp;
            }
        }
    }
}

// banana