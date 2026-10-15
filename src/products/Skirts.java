package products;

/**
 * Represents a skirt.
 * Calculates the adjusted price difference using a factor of 0.1.
 */

public class Skirts extends Products {
	
	public Skirts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.1 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

} 