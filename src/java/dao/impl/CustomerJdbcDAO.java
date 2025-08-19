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
        String sql = "INSERT INTO customers (account_no, name, address, phone, units_consumed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getAccountNo());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getUnitsConsumed());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name=?, address=?, phone=?, units_consumed=? WHERE account_no=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setInt(4, customer.getUnitsConsumed());
            stmt.setInt(5, customer.getAccountNo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int accountNo) {
        String sql = "DELETE FROM customers WHERE account_no=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer getCustomerByAccountNo(int accountNo) {
        String sql = "SELECT account_no, name, address, phone, units_consumed FROM customers WHERE account_no=?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("account_no"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getInt("units_consumed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT account_no, name, address, phone, units_consumed FROM customers";
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("account_no"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getInt("units_consumed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
