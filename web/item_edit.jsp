<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, util.DBConnectionUtil" %>

<!DOCTYPE html>
<html>
<head><title>Edit Item</title>
<link rel="stylesheet" href="css/style.css"></head>
<body>
<h2>Edit Item</h2>
<form action="ItemUpdateServlet" method="post">
    <input type="hidden" name="itemId" value="${item.itemId}">
    Title: <input type="text" name="title" value="${item.title}">
    Author: <input type="text" name="author" value="${item.author}">
    Price: <input type="text" name="price" value="${item.price}">
    Quantity: <input type="text" name="quantity" value="${item.quantity}">
    <button type="submit">Update</button>
</form>

<% String error = (String) request.getAttribute("errorMessage"); 
   if (error != null) { %>
   <p style="color:red;"><%= error %></p>
<% } %>

<% String ok = (String) request.getAttribute("successMessage"); 
   if (ok != null) { %>
   <p style="color:green;"><%= ok %></p>
<% } %>

<%
String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);

String title=""; double price=0;

try(Connection con=DBConnectionUtil.getConnection()){
  PreparedStatement ps=con.prepareStatement("SELECT * FROM items WHERE item_id=?");
  ps.setInt(1,id);
  ResultSet rs=ps.executeQuery();
  if(rs.next()){
    title=rs.getString("title");
    price=rs.getDouble("price");
  }
} catch(Exception e){ e.printStackTrace(); }
%>
<br>
<a href="item_list.jsp">Back to Items</a>
</body>
</html>
