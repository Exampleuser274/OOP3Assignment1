package products;

/**
 * Represents the Jacket Product Subclass
 * Calculates the adjusted price difference using a factor of 0.7.
 */
public class Jackets extends Products{
	
	/**
	 * Creates a Jackets product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the jacket.
	 * @param updatedPrice The updated price of the jacket.
	 */
	
	public Jackets(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	/**
	 * Calculates the adjusted price difference for Jackets.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.7.
	 */
	
	@Override
	public double calcRealDiff() {
		return 0.7 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}