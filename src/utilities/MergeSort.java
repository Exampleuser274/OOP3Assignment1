package utilities;

import java.util.Comparator;

import products.Products;

/**
 * Implements the Merge Sort algorithm to arrange Products
 * objects in descending order.
 *
 * Uses a divide-and-conquer approach to recursively
 * divide the array into smaller sections and merge
 * them back together in sorted order.
 *
 * A Comparator determines whether products are sorted
 * by original price, updated price, or calculated
 * real difference.
 */

public class MergeSort {
	
	/**
	 * Sorts an array of products in descending order
	 * using the Merge Sort algorithm.
	 *
	 * Uses a divide-and-conquer approach by recursively
	 * splitting the array into smaller sections until
	 * each section contains only one product.
	 *
	 * The smaller sections are then merged back together
	 * in descending order using the supplied Comparator.
	 *
	 * @param products The array of products to be sorted.
	 * @param comparator Determines how products are compared
	 *                   during the sorting process.
	 */

	public static void sort(Products[] products, Comparator<Products> comparator) {
		
		//assigns length of the array to an int (saves on writing code)
		int n = products.length;
		//checks if the array is one item long as a signle item is already sorted
		if (n < 2) {
			return;
		}//end of length check
		
		//creates 2 arrays with half the size of the input and populates them with one half of the objects
		int mid = n / 2;
		Products[] l = new Products[mid];
		Products[] r = new Products[n-mid];
		for (int i = 0; i < mid; i++) {
	        l[i] = products[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = products[i];
	    }
	    //runs the split again with each half, until each array is only one item
	    sort(l,comparator);
	    sort(r,comparator);
	    //calls merge to rebuild the original array, sorted
	    merge(products,l,r,comparator);
	}//end of sort method
	
	/**
	 * Merges two sorted product arrays into one array
	 * in descending order.
	 *
	 * Compares the next available product from each array
	 * and places the greater product into the original array.
	 *
	 * Once one array has been fully processed, any remaining
	 * products from the other array are copied over.
	 *
	 * @param original The array that receives the merged products.
	 * @param l The sorted left portion of the array.
	 * @param r The sorted right portion of the array.
	 * @param comparator Determines how products are compared
	 *                   when merging the two arrays.
	 */

	public static void merge(Products[] original, Products[] l,Products[] r, Comparator<Products> comparator) {
		int i = 0, j = 0, k = 0,left = l.length,right=r.length;
		/*while both smaller arrays still have unsorted values, compares the first unsorted value of each array
		 * and adds the larger one back to the "full" array, then moves up one in that array(the value has been sorted)
		 */
		while (i < left && j < right) {
			if (comparator.compare(l[i], r[j]) >= 0) {
				original[k++] = l[i++];
			}else {
				original[k++] = r[j++];
			}
		}
		//if one array is one item longer than the other, add the last item to the full array (prevents losing data in the sort)
		 while (i < left) {
		        original[k++] = l[i++];
		    }
		    while (j < right) {
		        original[k++] = r[j++];
		    }
	}//end of merge method
}//end of class
