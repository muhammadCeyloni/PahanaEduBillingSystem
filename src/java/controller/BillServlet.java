package controller;

import dao.DBConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
    

  private double calculateAmount(int units){
      
    double amount=0;
    int remaining=units;

    int slab = Math.min(remaining, 100);
    amount += slab * 10.0;
    remaining -= slab;

    if(remaining>0){
      slab = Math.min(remaining, 100);
      amount += slab * 15.0;
      remaining -= slab;
    }
    if(remaining>0){
      amount += remaining * 20.0;
    }
    return amount;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String custIdStr = req.getParameter("customer_id");
    String unitsStr = req.getParameter("units");
    if(custIdStr==null || unitsStr==null){ res.sendRedirect("customer_list.jsp"); return; }

    int customerId, units;
    try{
      customerId = Integer.parseInt(custIdStr);
      units = Integer.parseInt(unitsStr);
      if(units<0) throw new NumberFormatException();
    }catch(NumberFormatException e){
      req.setAttribute("errorMessage","Units must be a non-negative number.");
      req.getRequestDispatcher("customer_list.jsp").forward(req,res);
      return;
    }

    double amount = calculateAmount(units);
    
    try(Connection con=DBConnection.getConnection()){
      // save bill record
      PreparedStatement ps=con.prepareStatement(
        "INSERT INTO bills(customer_id,units,amount) VALUES(?,?,?)",
        Statement.RETURN_GENERATED_KEYS
      );
      ps.setInt(1, customerId);
      ps.setInt(2, units);
      ps.setDouble(3, amount);
      ps.executeUpdate();

      // fetch bill_id for printing
      ResultSet keys = ps.getGeneratedKeys();
      int billId = 0; if(keys.next()) billId = keys.getInt(1);

      res.sendRedirect("bill_print.jsp?bill_id="+billId);
    } catch(SQLException ex){
      ex.printStackTrace();
      req.setAttribute("errorMessage","Database error while saving bill.");
      req.getRequestDispatcher("customer_list.jsp").forward(req,res);
    }
  }
}
