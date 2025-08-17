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
  <input type="hidden" name="id" value="<%=id%>">
  Account No: <input name="account_no" value="<%=accountNo%>" required><br><br>
  Name: <input name="name" value="<%=name%>" required><br><br>
  Address: <input name="address" value="<%=address%>" required><br><br>
  Phone: <input name="phone" value="<%=phone%>" required><br><br>
  Units Consumed: <input type="number" name="units" value="<%=units%>" required><br><br>
  <button type="submit">Update</button>
</form>
<br>
<a href="customer_list.jsp">Back to Customers</a>
</body>
</html>
