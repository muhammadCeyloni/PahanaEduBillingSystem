package dao;

import dao.impl.CustomerJdbcDAO;
import model.Customer;

public class TestCustomerDAO {
    public static void main(String[] args) {
        CustomerJdbcDAO dao = new CustomerJdbcDAO();

        // Create a customer
        Customer c1 = new Customer(1001, "John Doe", "123 Main Street", "0771234567", 50);

        // Test insert
        boolean added = dao.addCustomer(c1);
        System.out.println("✅ Add Customer: " + added);

        // Test fetch
        Customer fetched = dao.getCustomerByAccountNo(1001);
        if (fetched != null) {
            System.out.println("✅ Fetched Customer: " + fetched.getName() + " | Units: " + fetched.getUnitsConsumed());
        } else {
            System.out.println("❌ Failed to fetch customer");
        }
    }
}
