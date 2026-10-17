package utilities;

import java.util.Comparator;
import products.Products;

/**
 * Sorts products in descending order using Bubble Sort.
 */
public class BubbleSort {

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