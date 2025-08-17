package controller;

import dao.DBConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/CustomerAddServlet")
public class CustomerAddServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String accountNo = req.getParameter("account_no");
    String name = req.getParameter("name");
    String address = req.getParameter("address");
    String phone = req.getParameter("phone");
    String unitsStr = req.getParameter("units");

    // Server-side validation
    if(accountNo==null || accountNo.trim().length()<3 ||
       name==null || name.trim().isEmpty() ||
       address==null || address.trim().isEmpty() ||
       phone==null || phone.trim().isEmpty() ||
       unitsStr==null) {
      req.setAttribute("errorMessage","Please fill all fields correctly.");
      req.getRequestDispatcher("customer_add.jsp").forward(req,res);
      return;
    }

    int units = 0;
    try { units = Integer.parseInt(unitsStr); if (units<0) throw new NumberFormatException(); }
    catch(NumberFormatException e){
      req.setAttribute("errorMessage","Units must be a non-negative number.");
      req.getRequestDispatcher("customer_add.jsp").forward(req,res);
      return;
    }

    try(Connection con = DBConnection.getConnection()){
      String sql = "INSERT INTO customers(account_no,name,address,phone,units_consumed) VALUES(?,?,?,?,?)";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, accountNo.trim());
      ps.setString(2, name.trim());
      ps.setString(3, address.trim());
      ps.setString(4, phone.trim());
      ps.setInt(5, units);
      int r = ps.executeUpdate();
      if(r>0) res.sendRedirect("customer_list.jsp");
      else {
        req.setAttribute("errorMessage","Failed to save customer.");
        req.getRequestDispatcher("customer_add.jsp").forward(req,res);
      }
    } catch(SQLException ex){
      ex.printStackTrace();
      req.setAttribute("errorMessage","Database error (maybe Account No already exists).");
      req.getRequestDispatcher("customer_add.jsp").forward(req,res);
    }
  }
}
