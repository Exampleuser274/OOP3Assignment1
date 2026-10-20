package utilities;

import java.util.Comparator;
import products.Products;

/** Sorts products in descending order using Heap Sort. */
public class HeapSort {
	
	/**
	 * Sorts an array of products in descending order
	 * using the Heap Sort algorithm.
	 *
	 * First organizes the products into a min-heap,
	 * where the smallest product is located at the root.
	 *
	 * The smallest product is repeatedly swapped with
	 * the last element of the remaining heap.
	 *
	 * After each swap, the heap is reduced in size and
	 * heapify() restores the min-heap property.
	 *
	 * @param products The array of products to be sorted.
	 * @param comparator Determines how products are compared
	 *                   when constructing and maintaining the heap.
	 */
	
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


	/**
	 * Restores the min-heap property within a subtree.
	 *
	 * Compares the current root with its left and right
	 * children to determine which contains the smallest
	 * product according to the supplied Comparator.
	 *
	 * If a child is smaller than the root, the two products
	 * are swapped and heapify() is called recursively on
	 * the affected subtree.
	 *
	 * @param products The array containing the heap.
	 * @param heapSize The number of elements currently
	 *                 included in the heap.
	 * @param root The index of the subtree's root.
	 * @param comparator Determines how products are compared
	 *                   when identifying the smallest element.
	 */

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