package controller;

import util.DBConnectionUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String idStr=req.getParameter("id");
    int id=Integer.parseInt(idStr);

    try(Connection con=DBConnectionUtil.getConnection()){
      PreparedStatement ps=con.prepareStatement("DELETE FROM customers WHERE id=?");
      ps.setInt(1,id);
      ps.executeUpdate();
    } catch(SQLException e){ e.printStackTrace(); }

    res.sendRedirect("customer_list.jsp");
  }
}
