package products;

/**
 * Represents a Dress.
 * Calculates the adjusted price difference using a factor of 0.6.
 */

public class Dresses extends Products{
	
	public Dresses(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.6 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}