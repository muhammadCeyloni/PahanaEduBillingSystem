<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, dao.DBConnection" %>
<%
String idStr = request.getParameter("id");
int id = Integer.parseInt(idStr);

String accountNo="", name="", address="", phone="";
int units=0;

try(Connection con=DBConnection.getConnection()){
  PreparedStatement ps=con.prepareStatement("SELECT * FROM customers WHERE id=?");
  ps.setInt(1, id);
  ResultSet rs=ps.executeQuery();
  if(rs.next()){
    accountNo=rs.getString("account_no");
    name=rs.getString("name");
    address=rs.getString("address");
    phone=rs.getString("phone");
    units=rs.getInt("units_consumed");
  }
} catch(Exception e){ e.printStackTrace(); }
%>
<!DOCTYPE html>
<html>
<head><title>Edit Customer</title></head>
<body>
<h2>Edit Customer</h2>
<form action="CustomerUpdateServlet" method="post">
    <input type="hidden" name="accountNo" value="${customer.accountNo}" />
    Name: <input type="text" name="name" value="${customer.name}" /><br>
    Address: <input type="text" name="address" value="${customer.address}" /><br>
    Phone: <input type="text" name="phone" value="${customer.phone}" /><br>
    Units: <input type="number" name="units" value="${customer.units}" /><br>
    <input type="submit" value="Update Customer" />
</form>

<br>
<a href="customer_list.jsp">Back to Customers</a>
</body>
</html>
