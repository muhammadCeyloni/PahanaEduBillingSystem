package controller;

import dao.DBConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ItemDeleteServlet")
public class ItemDeleteServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String idStr=req.getParameter("id");
    int id=Integer.parseInt(idStr);

    try(Connection con=DBConnection.getConnection()){
      PreparedStatement ps=con.prepareStatement("DELETE FROM items WHERE item_id=?");
      ps.setInt(1,id);
      ps.executeUpdate();
    } catch(SQLException e){ e.printStackTrace(); }

    res.sendRedirect("item_list.jsp");
  }
}
