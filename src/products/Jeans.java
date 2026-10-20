package products;

/**
 * Represents the Jeans Product Subclass
 * Calculates the adjusted price difference using a factor of 0.5.
 */
public class Jeans extends Products{
	
	/**
	 * Creates a Jeans product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the jeans.
	 * @param updatedPrice The updated price of the jeans.
	 */
	
	public Jeans(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	
	/**
	 * Calculates the adjusted price difference for Jeans.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.5.
	 */

	@Override
	public double calcRealDiff() {
		return 0.5 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
