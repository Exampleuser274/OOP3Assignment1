package products;

/**
 * Represents the Shoes Product Subclass
 * Calculates the adjusted price difference using a factor of 0.4.
 */
public class Shoes extends Products{
	
	/**
	 * Creates a Shoes product with its original and updated prices.
	 *
	 * @param originalPrice The original price of the shoes.
	 * @param updatedPrice The updated price of the shoes.
	 */
	
	public Shoes(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	/**
	 * Calculates the adjusted price difference for Shoes.
	 *
	 * @return The absolute difference between the original
	 *         and updated prices multiplied by 0.4.
	 */
	
	@Override
	public double calcRealDiff() {
		return 0.4 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}