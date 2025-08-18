<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, dao.DBConnection" %>
<!DOCTYPE html>
<html><head><title>Items</title>
<style>table,th,td{border:1px solid #333;border-collapse:collapse;padding:6px}</style>
</head>
<body>
<h2>Items</h2>
<a href="item_add.jsp">➕ Add Item</a> | <a href="welcome.jsp">Dashboard</a><br><br>
<form action="ItemDeleteServlet" method="post">
    <input type="hidden" name="itemId" value="${item.itemId}" />
    <input type="submit" value="Delete" />
</form>

<table>
<tr><th>ID</th><th>Title</th><th>Price</th><th>Actions</th></tr>
<%
try(Connection con=DBConnection.getConnection()){
  ResultSet rs = con.prepareStatement("SELECT * FROM items ORDER BY item_id DESC").executeQuery();
  while(rs.next()){
%>
<tr>
  <td><%=rs.getInt("item_id")%></td>
  <td><%=rs.getString("title")%></td>
  <td><%=rs.getDouble("price")%></td>
  <td>
    <a href="item_edit.jsp?id=<%=rs.getInt("item_id")%>">Edit</a> |
    <a href="ItemDeleteServlet?id=<%=rs.getInt("item_id")%>">Delete</a>
  </td>
</tr>
<% }} catch(Exception e){ e.printStackTrace(); } %>
</table>
</body></html>
