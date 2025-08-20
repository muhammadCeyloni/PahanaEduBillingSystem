package service;

import model.Bill;

public interface BillingService {
    Bill generateBill(int accountNo, int units);
}
