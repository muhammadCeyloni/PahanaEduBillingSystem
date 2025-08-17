package controller;

import dao.DBConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ItemUpdateServlet")
public class ItemUpdateServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String idStr=req.getParameter("id");
    String title=req.getParameter("title");
    String priceStr=req.getParameter("price");

    int id=Integer.parseInt(idStr);
    double price=Double.parseDouble(priceStr);

    try(Connection con=DBConnection.getConnection()){
      PreparedStatement ps=con.prepareStatement("UPDATE items SET title=?,price=? WHERE item_id=?");
      ps.setString(1,title);
      ps.setDouble(2,price);
      ps.setInt(3,id);
      int r=ps.executeUpdate();
      if(r>0) res.sendRedirect("item_list.jsp");
      else {
        req.setAttribute("errorMessage","Failed to update item.");
        req.getRequestDispatcher("item_edit.jsp?id="+id).forward(req,res);
      }
    } catch(SQLException e){
      e.printStackTrace();
      req.setAttribute("errorMessage","Database error.");
      req.getRequestDispatcher("item_edit.jsp?id="+id).forward(req,res);
    }
  }
}
