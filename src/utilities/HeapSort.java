package utilities;

import java.util.Comparator;
import products.Products;

/** Sorts products in descending order using Heap Sort. */
public class HeapSort {
	public static void sort(
            Products[] products,
            Comparator<Products> comparator) {

        int n = products.length;

        // Build a min-heap.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(products, n, i, comparator);
        }

        // Move the smallest element to the end each time.
        // This produces descending order.
        for (int end = n - 1; end > 0; end--) {

            Products temp = products[0];
            products[0] = products[end];
            products[end] = temp;

            heapify(products, end, 0, comparator);
        }
    }

    /** Restores the min-heap property in the given subtree. */
    private static void heapify(
            Products[] products,
            int heapSize,
            int root,
            Comparator<Products> comparator) {

        int smallest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize
                && comparator.compare(
                        products[left], products[smallest]) < 0) {
            smallest = left;
        } if (right < heapSize
                && comparator.compare(
                        products[right], products[smallest]) < 0) {
            smallest = right;
        } if (smallest != root) {
            Products temp = products[root];
            products[root] = products[smallest];
            products[smallest] = temp;

            heapify(products, heapSize, smallest, comparator);
        }
    }
}

//banana