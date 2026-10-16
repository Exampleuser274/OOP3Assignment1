
package utilities;

import java.util.Comparator;
import products.Products;

/**
 * Sorts an array of Products in descending order
 * using the Insertion Sort algorithm.
 */
public class InsertionSort {

    /**
     * Sorts the given product array in descending order.
     *
     * @param products The array of products to sort.
     * @param comparator Determines how products are compared.
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
