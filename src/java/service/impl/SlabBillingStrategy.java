package service.impl;
import service.BillingStrategy;

public class SlabBillingStrategy implements BillingStrategy {
  @Override public double calculate(int units){
    int remaining = units; double amount = 0;
    int slab = Math.min(remaining, 100); amount += slab * 10.0; remaining -= slab;
    if(remaining > 0){ slab = Math.min(remaining, 100); amount += slab * 15.0; remaining -= slab; }
    if(remaining > 0){ amount += remaining * 20.0; }
    return amount;
  }
}
