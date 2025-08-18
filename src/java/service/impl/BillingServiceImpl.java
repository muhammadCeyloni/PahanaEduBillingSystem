package service.impl;

import dao.BillDAO;
import dao.CustomerDAO;
import dao.impl.BillJdbcDAO;
import dao.impl.CustomerJdbcDAO;
import model.Bill;
import model.Customer;
import service.BillingService;
import strategy.BillingStrategy;
import strategy.SlabBillingStrategy;

public class BillingServiceImpl implements BillingService {
    private final CustomerDAO customerDAO = new CustomerJdbcDAO();
    private final BillDAO billDAO = new BillJdbcDAO();
    private final BillingStrategy billingStrategy = new SlabBillingStrategy();

    @Override
    public Bill generateBill(int customerId, int unitsConsumed) {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("❌ Customer not found.");
        }

        double amount = billingStrategy.calculateBill(unitsConsumed);

        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        bill.setUnits(unitsConsumed);
        bill.setAmount(amount);

        // save bill in DB
        billDAO.saveBill(bill);

        return bill;
    }
}
