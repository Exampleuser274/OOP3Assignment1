package utilities;

import java.util.Comparator;

import products.Products;

public class MergeSort {
	//this method splits the input array into smaller arrays until each only contains one product, then builds back up and calls merge each time to sort the smaller arrays
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
	
	//this method merges two of the smaller arrays back together and sorts them based on the supplied comparator
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
