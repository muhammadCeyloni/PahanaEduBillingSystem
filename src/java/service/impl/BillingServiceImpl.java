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

import java.util.Date;

public class BillingServiceImpl implements BillingService {

    private final BillingStrategy billingStrategy = new SlabBillingStrategy();
    private final CustomerDAO customerDAO = new CustomerJdbcDAO();
    private final BillDAO billDAO = new BillJdbcDAO();

    @Override
    public Bill generateBill(int accountNo, int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be positive");
        }

        Customer customer = customerDAO.getCustomerByAccountNo(accountNo);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found: " + accountNo);
        }

        double amount = billingStrategy.calculateBill(units);

        Bill bill = new Bill();
        bill.setCustomerId(accountNo);
        bill.setUnits(units);
        bill.setAmount(amount);
        bill.setBillDate(new Date());

        int newId = billDAO.saveBill(bill);
        if (newId <= 0) {
            throw new IllegalStateException("Failed to save bill");
        }
        bill.setBillId(newId);
        return bill;
    }
}
