package utilities;

import java.util.Comparator;
import products.Products;

/**
 * Compares Products objects based on their updated prices.
 * Implements the Comparator interface for use in sorting.
 */

public class UpdatedPriceComparator implements Comparator<Products> {
	
	/**
     * Compares the updated price of two products.
     *
     * @param p1 The first product.
     * @param p2 The second product.
     * @return A negative number if p1 is smaller, zero if equal, or positive if p1 is greater.
     */
	
	@Override
	public int compare(Products p1, Products p2) {
		
		return Double.compare(p1.getUpdatedPrice(), p2.getUpdatedPrice());
	}

}
