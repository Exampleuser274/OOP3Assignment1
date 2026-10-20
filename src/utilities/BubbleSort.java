package utilities;

import java.util.Comparator;
import products.Products;

/**
 * Implements the Bubble Sort algorithm to arrange Products
 * objects in descending order.
 *
 * Uses a Comparator to determine how products are compared,
 * allowing sorting by original price, updated price,
 * or calculated real difference.
 */

public class BubbleSort {

	/**
	 * Sorts an array of products in descending order
	 * using the Bubble Sort algorithm.
	 *
	 * Repeatedly compares adjacent products and swaps them
	 * when the product on the left is smaller than the
	 * product on the right.
	 *
	 * Each completed pass moves the smallest remaining
	 * product toward its correct position at the end
	 * of the unsorted portion of the array.
	 *
	 * The process continues until the array is sorted.
	 *
	 * @param products The array of products to be sorted.
	 * @param comparator Determines how products are compared
	 * 		  during the sorting process.
	 */
	
    public static void sort(
            Products[] products,
            Comparator<Products> comparator) {
    	for (int i = 0; i < products.length - 1; i++) {
    		for (int j = 0; j < products.length - 1 - i; j++) {
    			if (comparator.compare(products[j], products[j + 1]) < 0) {
    				
    				Products temp = products[j];
    				products[j] = products[j + 1];
    				products[j + 1] = temp;
    				
    			}

    		}
    	}
    	

    }
}