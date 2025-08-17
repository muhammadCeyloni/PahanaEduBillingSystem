<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, dao.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bill</title>
  <style>
    .bill{width:420px;border:1px solid #333;padding:12px}
    .hdr{font-weight:bold;margin-bottom:10px}
  </style>
</head>
<body>
<%
String billIdStr = request.getParameter("bill_id");
int billId = Integer.parseInt(billIdStr);

String accountNo="", name="";
int units=0;
double amount=0;

try(Connection con=DBConnection.getConnection()){
  PreparedStatement ps=con.prepareStatement(
    "SELECT b.units,b.amount,c.account_no,c.name FROM bills b JOIN customers c ON b.customer_id=c.id WHERE b.bill_id=?");
  ps.setInt(1,billId);
  ResultSet rs=ps.executeQuery();
  if(rs.next()){
    units = rs.getInt("units");
    amount = rs.getDouble("amount");
    accountNo = rs.getString("account_no");
    name = rs.getString("name");
  }
} catch(Exception e){ e.printStackTrace(); }
%>

<div class="bill" id="bill">
  <div class="hdr">Pahana Edu – Customer Bill</div>
  <p><b>Bill No:</b> <%=billId%></p>
  <p><b>Account No:</b> <%=accountNo%></p>
  <p><b>Name:</b> <%=name%></p>
  <p><b>Units:</b> <%=units%></p>
  <p><b>Amount:</b> Rs. <%=String.format("%.2f", amount)%></p>
  <p><i>Generated on:</i> <%= new java.util.Date() %></p>
</div>

<br>
<button onclick="window.print()">🖨️ Print</button>
<br><br>
<a href="customer_list.jsp">Back to Customers</a>
</body>
</html>
