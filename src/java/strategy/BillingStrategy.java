package strategy;

/**
 * Strategy interface for billing calculation.
 */
public interface BillingStrategy {
    double calculateBill(int units);
}
