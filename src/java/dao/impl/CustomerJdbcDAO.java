package dao.impl;

import dao.CustomerDAO;
import model.Customer;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerJdbcDAO implements CustomerDAO {

    @Override
    public boolean addCustomer(Customer customer) {
        // already in your code
        return false;
    }

    @Override
    public Customer getCustomerById(int accountNo) {
        // already in your code
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        // already in your code
        return new ArrayList<>();
    }

    // ✅ Implement update
    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name=?, address=?, phone=?, units=? WHERE account_no=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setInt(4, customer.getUnitsConsumed());
            stmt.setInt(5, customer.getAccountNo());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Implement delete
    @Override
    public boolean deleteCustomer(int accountNo) {
        String sql = "DELETE FROM customers WHERE account_no=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNo);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
