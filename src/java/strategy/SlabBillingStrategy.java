package strategy;

/**
 * Concrete implementation of BillingStrategy
 * Uses a simple slab-based calculation for demonstration.
 */
public class SlabBillingStrategy implements BillingStrategy {

    @Override
    public double calculateBill(int units) {
        double amount = 0.0;

        if (units <= 100) {
            amount = units * 5.0; // Rs.5 per unit for first 100
        } else if (units <= 300) {
            amount = (100 * 5.0) + (units - 100) * 7.0; // Rs.7 per unit for 101-300
        } else {
            amount = (100 * 5.0) + (200 * 7.0) + (units - 300) * 10.0; // Rs.10 per unit above 300
        }

        return amount;
    }
}
