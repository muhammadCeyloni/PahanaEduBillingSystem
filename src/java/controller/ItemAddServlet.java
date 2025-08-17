package controller;

import dao.DBConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ItemAddServlet")
public class ItemAddServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String title = req.getParameter("title");
    String priceStr = req.getParameter("price");

    if(title==null || title.trim().length()<2 || priceStr==null){
      req.setAttribute("errorMessage","Fill fields correctly.");
      req.getRequestDispatcher("item_add.jsp").forward(req,res);
      return;
    }

    double price;
    try{ price = Double.parseDouble(priceStr); if(price<=0) throw new NumberFormatException(); }
    catch(NumberFormatException e){
      req.setAttribute("errorMessage","Price must be a positive number.");
      req.getRequestDispatcher("item_add.jsp").forward(req,res);
      return;
    }

    try(Connection con=DBConnection.getConnection()){
      PreparedStatement ps=con.prepareStatement("INSERT INTO items(title,price) VALUES(?,?)");
      ps.setString(1, title.trim());
      ps.setDouble(2, price);
      int r=ps.executeUpdate();
      if(r>0) res.sendRedirect("item_list.jsp");
      else { req.setAttribute("errorMessage","Failed to save item."); req.getRequestDispatcher("item_add.jsp").forward(req,res); }
    } catch(SQLException ex){
      ex.printStackTrace();
      req.setAttribute("errorMessage","Database error.");
      req.getRequestDispatcher("item_add.jsp").forward(req,res);
    }
  }
}
