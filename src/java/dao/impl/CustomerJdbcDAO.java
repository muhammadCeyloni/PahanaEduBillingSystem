package dao.impl;
import dao.CustomerDAO;
import model.Customer;
import util.DBConnectionUtil;
import java.sql.*;
import java.util.*;

public class CustomerJdbcDAO implements CustomerDAO {
  @Override public void save(Customer c) throws Exception {
    try (Connection con = DBConnectionUtil.getConnection()){
      PreparedStatement ps = con.prepareStatement(
        "INSERT INTO customers(account_no,name,address,phone,units_consumed) VALUES(?,?,?,?,?)");
      ps.setString(1,c.getAccountNo()); ps.setString(2,c.getName());
      ps.setString(3,c.getAddress()); ps.setString(4,c.getPhone());
      ps.setInt(5,c.getUnitsConsumed()); ps.executeUpdate();
    }
  }
  @Override public void update(Customer c) throws Exception {
    try (Connection con = DBConnectionUtil.getConnection()){
      PreparedStatement ps = con.prepareStatement(
        "UPDATE customers SET account_no=?,name=?,address=?,phone=?,units_consumed=? WHERE id=?");
      ps.setString(1,c.getAccountNo()); ps.setString(2,c.getName());
      ps.setString(3,c.getAddress()); ps.setString(4,c.getPhone());
      ps.setInt(5,c.getUnitsConsumed()); ps.setInt(6,c.getId()); ps.executeUpdate();
    }
  }
  @Override public void delete(int id) throws Exception {
    try (Connection con = DBConnectionUtil.getConnection()){
      PreparedStatement ps = con.prepareStatement("DELETE FROM customers WHERE id=?");
      ps.setInt(1,id); ps.executeUpdate();
    }
  }
  @Override public Customer findById(int id) throws Exception {
    try (Connection con = DBConnectionUtil.getConnection()){
      PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE id=?");
      ps.setInt(1,id);
      ResultSet rs = ps.executeQuery();
      if(rs.next()){
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setAccountNo(rs.getString("account_no"));
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setPhone(rs.getString("phone"));
        c.setUnitsConsumed(rs.getInt("units_consumed"));
        return c;
      }
      return null;
    }
  }
  @Override public List<Customer> findAll() throws Exception {
    List<Customer> list = new ArrayList<>();
    try (Connection con = DBConnectionUtil.getConnection()){
      ResultSet rs = con.prepareStatement("SELECT * FROM customers ORDER BY id DESC").executeQuery();
      while(rs.next()){
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setAccountNo(rs.getString("account_no"));
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setPhone(rs.getString("phone"));
        c.setUnitsConsumed(rs.getInt("units_consumed"));
        list.add(c);
      }
    }
    return list;
    }
}
