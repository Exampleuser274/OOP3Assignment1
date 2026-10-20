package products;

/**
 * Represents the TShirts Product Subclass
 * Calculates the adjusted price difference using a factor of 0.2.
 */
public class TShirts extends Products{
	
	/**
	 * Creates a TShirts product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the T-shirt.
	 * @param updatedPrice The updated price of the T-shirt.
	 */
	
	public TShirts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	/**
	 * Calculates the adjusted price difference for TShirts.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.2.
	 */
	
	@Override
	public double calcRealDiff() {
		return 0.2 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
