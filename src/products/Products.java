package products;

/**
 * Abstract parent class representing a product.
 *
 * Stores the original and updated prices and provides
 * a common structure for all product subclasses.
 *
 * Implements Comparable to compare products by their
 * calculated real price difference.
 */

public abstract class Products implements Comparable<Products> {

    // Prices shared by all types of products.
	// We make these private because we dont want other external classes to modify these accidentally 
    private double originalPrice;
    private double updatedPrice;

    /**
     * Constructor used by product subclasses.
     *
     * @param originalPrice The product's original price.
     * @param updatedPrice The product's updated price.
     * 
     * we use this.originalPrice to specifically reference this objects instance of the variable
     * while the other instance is the constructor parameter that we make
     */
    
    public Products(double originalPrice, double updatedPrice) {
        this.originalPrice = originalPrice;
        this.updatedPrice = updatedPrice;
    }

    /**
     * Returns the original price.
     *
     * @return The original price.
     */
    
    public double getOriginalPrice() {
        return originalPrice;
    }

    /**
     * Returns the updated price.
     *
     * @return The updated price.
     */
    
    public double getUpdatedPrice() {
        return updatedPrice;
    }

    /**
     * Calculates the adjusted absolute price difference.
     * * This is abstract because each product will use its 
     * own method to calculate the difference between products
     * 
     * @return The calculated real difference.
     */
    
    public abstract double calcRealDiff();

    /**
     * Compares this product against another product
     * using their calculated real differences.
     *
     * @param other is the product to compare against.
     * @return Negative if this product is smaller, zero if equal, positive if greater.
     * 
     */
    
    @Override
    public int compareTo(Products other) {
        return Double.compare(this.calcRealDiff(),
                              other.calcRealDiff());
    }
}
