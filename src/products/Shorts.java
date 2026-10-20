package products;

/**
 * Represents the Shorts Product Subclass
 * Calculates the adjusted price difference using a factor of 0.3.
 */
public class Shorts extends Products{
	
	/**
	 * Creates a Shorts product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the shorts.
	 * @param updatedPrice The updated price of the shorts.
	 */
	
	public Shorts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	/**
	 * Calculates the adjusted price difference for Shorts.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.3.
	 */
	
	@Override
	public double calcRealDiff() {
		return 0.3 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}