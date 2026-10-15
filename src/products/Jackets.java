package products;

/**
 * Represents a Jacket.
 * Calculates the adjusted price difference using a factor of 0.7.
 */

public class Jackets extends Products{
	
	public Jackets(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.7 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}