package products;

/**
 * Represents a pair of Shoes.
 * Calculates the adjusted price difference using a factor of 0.4.
 */

public class Shoes extends Products{
	
	public Shoes(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.4 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}