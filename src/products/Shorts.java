package products;

/**
 * Represents a pair of shorts.
 * Calculates the adjusted price difference using a factor of 0.3.
 */

public class Shorts extends Products{
	
	public Shorts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.3 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}