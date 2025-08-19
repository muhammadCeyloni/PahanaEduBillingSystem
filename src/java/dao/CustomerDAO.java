package dao;

import model.Customer;
import java.util.List;

public interface CustomerDAO {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int accountNo);
    Customer getCustomerByAccountNo(int accountNo);
    List<Customer> getAllCustomers();
}
