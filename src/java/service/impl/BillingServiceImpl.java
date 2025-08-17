package service.impl;
import service.BillingService;
import service.BillingStrategy;

public class BillingServiceImpl implements BillingService {
  private final BillingStrategy strategy;
  public BillingServiceImpl(BillingStrategy strategy){ this.strategy = strategy; }
  @Override public double computeBill(int units){ return strategy.calculate(units); }
}
