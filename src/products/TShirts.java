package products;

/**
 * Represents a TShirt.
 * Calculates the adjusted price difference using a factor of 0.2.
 */

public class TShirts extends Products{
	
	public TShirts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}
	
	@Override
	public double calcRealDiff() {
		return 0.2 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

}
