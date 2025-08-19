package dao.impl;

import dao.BillDAO;
import model.Bill;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillJdbcDAO implements BillDAO {

    @Override
    public int saveBill(Bill bill) {
        String sql = "INSERT INTO bills (customer_id, units, amount, bill_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, bill.getCustomerId());
            stmt.setInt(2, bill.getUnits());
            stmt.setDouble(3, bill.getAmount());
            stmt.setTimestamp(4, new Timestamp(bill.getBillDate().getTime()));

            int affected = stmt.executeUpdate();
            if (affected == 0) return -1;

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT bill_id, customer_id, units, amount, bill_date FROM bills";
        try (Connection conn = DBConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bill b = new Bill(
                        rs.getInt("bill_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("units"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("bill_date")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
