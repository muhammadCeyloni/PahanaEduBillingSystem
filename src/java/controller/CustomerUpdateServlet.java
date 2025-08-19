package controller;

import util.DBConnectionUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String idStr=req.getParameter("id");
    String accountNo=req.getParameter("account_no");
    String name=req.getParameter("name");
    String address=req.getParameter("address");
    String phone=req.getParameter("phone");
    String unitsStr=req.getParameter("units");

    int id=Integer.parseInt(idStr);
    int units=Integer.parseInt(unitsStr);

    try(Connection con=DBConnectionUtil.getConnection()){
      PreparedStatement ps=con.prepareStatement(
        "UPDATE customers SET account_no=?,name=?,address=?,phone=?,units_consumed=? WHERE id=?");
      ps.setString(1, accountNo);
      ps.setString(2, name);
      ps.setString(3, address);
      ps.setString(4, phone);
      ps.setInt(5, units);
      ps.setInt(6, id);

      int r=ps.executeUpdate();
      if(r>0) res.sendRedirect("customer_list.jsp");
      else {
        req.setAttribute("errorMessage","Failed to update customer.");
        req.getRequestDispatcher("customer_edit.jsp?id="+id).forward(req,res);
      }
    } catch(SQLException e){
      e.printStackTrace();
      req.setAttribute("errorMessage","Database error.");
      req.getRequestDispatcher("customer_edit.jsp?id="+id).forward(req,res);
    }
  }
}
