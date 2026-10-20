package products;

/**
 * Represents the Dress Product Subclass
 * Calculates the adjusted price difference using a factor of 0.6.
 */

public class Dresses extends Products{
	
	/**
	 * Creates a Dresses product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the dress.
	 * @param updatedPrice The updated price of the dress.
	 */
	
	public Dresses(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	/**
	 * Calculates the adjusted price difference for Dresses.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.6.
	 */
	
	@Override
	public double calcRealDiff() {
		return 0.6 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}