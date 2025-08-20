<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, util.DBConnectionUtil" %>
<!DOCTYPE html>
<html><head><title>Customers</title>
        <link rel="stylesheet" href="css/style.css">
<style>table,th,td{border:1px solid #333;border-collapse:collapse;padding:6px}</style>
</head>
<body>
<h2>Customer Accounts</h2>
<a href="customer_add.jsp">➕ Add Customer</a> | <a href="welcome.jsp">Dashboard</a><br><br>
<form action="CustomerDeleteServlet" method="post">
    <input type="hidden" name="accountNo" value="${customer.accountNo}" />
    <input type="submit" value="Delete" />
</form>

<table>
<tr><th>ID</th><th>Account No</th><th>Name</th><th>Phone</th><th>Units</th><th>Actions</th></tr>
<%
try(Connection con=DBConnectionUtil.getConnection()){
  PreparedStatement ps=con.prepareStatement("SELECT * FROM customers ORDER BY id DESC");
  ResultSet rs=ps.executeQuery();
  while(rs.next()){
%>
<tr>
  <td><%=rs.getInt("id")%></td>
  <td><%=rs.getString("account_no")%></td>
  <td><%=rs.getString("name")%></td>
  <td><%=rs.getString("phone")%></td>
  <td><%=rs.getInt("units_consumed")%></td>
  <td>
    <a href="customer_edit.jsp?id=<%=rs.getInt("id")%>">Edit</a> |
    <a href="CustomerDeleteServlet?id=<%=rs.getInt("id")%>">Delete</a> |
    <a href="bill_calculate.jsp?id=<%=rs.getInt("id")%>">Calc Bill</a>
  </td>
</tr>
<% }} catch(Exception e){ e.printStackTrace(); } %>
</table>
</body></html>
