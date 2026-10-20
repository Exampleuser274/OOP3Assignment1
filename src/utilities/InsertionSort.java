
package utilities;

import java.util.Comparator;
import products.Products;

/**
 * Implements the Insertion Sort algorithm to arrange Products
 * objects in descending order.
 *
 * Uses a Comparator to determine how products are compared,
 * allowing sorting by original price, updated price,
 * or calculated real difference.
 */

public class InsertionSort {

	/**
	 * Sorts an array of products in descending order
	 * using the Insertion Sort algorithm.
	 *
	 * Begins with the second element, treating the first
	 * element as already sorted.
	 *
	 * Each product is compared against the sorted portion
	 * of the array. Smaller products are shifted one position
	 * to the right until the correct position is found.
	 *
	 * The selected product is then inserted into that position.
	 * This process continues until the entire array is sorted.
	 *
	 * @param products The array of products to be sorted.
	 * @param comparator Determines how products are compared
	 *        during the sorting process.
	 */
	
    public static void sort(
            Products[] products,
            Comparator<Products> comparator) {

        // Start at index 1, since index 0 is considered sorted.
        for (int i = 1; i < products.length; i++) {

            // Save the product we're trying to insert.
            Products key = products[i];

            // Start comparing with the previous product.
            int j = i - 1;

            // Shift smaller products one position to the right.
            // A negative comparison means products[j] is smaller.
            while (j >= 0 &&
                    comparator.compare(products[j], key) < 0) {

                products[j + 1] = products[j];
                j--;
            }

            // Insert the saved product into the correct position.
            products[j + 1] = key;
        }
    }
}
