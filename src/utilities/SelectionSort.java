package utilities;

import java.util.Comparator;
import products.Products;

/** Sorts products in descending order using Selection Sort. */
public class SelectionSort {
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