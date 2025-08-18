package dao;

import model.Customer;
import java.util.List;

public interface CustomerDAO {
    boolean addCustomer(Customer customer);
    Customer getCustomerById(int accountNo);
    List<Customer> getAllCustomers();

    // ✅ Add these two methods
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int accountNo);
}
