package products;

/**
 * Represents the Skirts Product Subclass
 * Calculates the adjusted price difference using a factor of 0.1.
 */
public class Skirts extends Products {
	
	/**
	 * Creates a Skirts product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the skirt.
	 * @param updatedPrice The updated price of the skirt.
	 */
	
	public Skirts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	/**
	 * Calculates the adjusted price difference for Skirts.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.1.
	 */
	
	@Override
	public double calcRealDiff() {
		return 0.1 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

} 