package dao.impl;

import dao.BillDAO;
import model.Bill;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillJdbcDAO implements BillDAO {

    @Override
    public boolean saveBill(Bill bill) {
        String sql = "INSERT INTO bills (customerId, units, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bill.getCustomer().getId());
            stmt.setInt(2, bill.getUnits());
            stmt.setDouble(3, bill.getAmount());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bill.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Bill getBillById(int id) {
        String sql = "SELECT * FROM bills WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setUnits(rs.getInt("units"));
                bill.setAmount(rs.getDouble("amount"));
                // NOTE: you can join with customer table to fetch customer data if needed
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bill> getBillsByCustomerId(int customerId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE customerId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setUnits(rs.getInt("units"));
                bill.setAmount(rs.getDouble("amount"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setUnits(rs.getInt("units"));
                bill.setAmount(rs.getDouble("amount"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
