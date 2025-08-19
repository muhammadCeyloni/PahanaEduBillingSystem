<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, dao.DBConnection" %>
<!DOCTYPE html>
<html><head><title>Calculate Bill</title>
    <link rel="stylesheet" href="css/style.css">
    </head>
<body>
<%
String id = request.getParameter("id"); // customer_id
int customerId = Integer.parseInt(id);
String accountNo="", name="";
int currentUnits=0;

try(Connection con=DBConnection.getConnection()){
  PreparedStatement ps=con.prepareStatement("SELECT * FROM customers WHERE id=?");
  ps.setInt(1, customerId);
  ResultSet rs=ps.executeQuery();
  if(rs.next()){
    accountNo = rs.getString("account_no");
    name = rs.getString("name");
    currentUnits = rs.getInt("units_consumed");
  }
} catch(Exception e){ e.printStackTrace(); }
%>

<h2>Bill Calculation</h2>
<p><b>Account No:</b> <%=accountNo%> | <b>Name:</b> <%=name%></p>

<form action="BillServlet" method="post">
    Customer ID: <input type="text" name="customerId" /><br>
    Units Consumed: <input type="number" name="units" /><br>
    <input type="submit" value="Generate Bill" />
</form>


<br><a href="customer_list.jsp">Back to Customers</a>
</body></html>
