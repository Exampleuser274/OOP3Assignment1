package products;

/**
 * Represents a pair of jeans.
 * Calculates the adjusted price difference using a factor of 0.5.
 */

public class Jeans extends Products{
	
	public Jeans(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.5 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
