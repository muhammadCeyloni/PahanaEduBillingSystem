<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, dao.DBConnection" %>
<%
String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);

String title=""; double price=0;

try(Connection con=DBConnection.getConnection()){
  PreparedStatement ps=con.prepareStatement("SELECT * FROM items WHERE item_id=?");
  ps.setInt(1,id);
  ResultSet rs=ps.executeQuery();
  if(rs.next()){
    title=rs.getString("title");
    price=rs.getDouble("price");
  }
} catch(Exception e){ e.printStackTrace(); }
%>
<!DOCTYPE html>
<html>
<head><title>Edit Item</title></head>
<body>
<h2>Edit Item</h2>
<form action="ItemUpdateServlet" method="post">
    <input type="hidden" name="itemId" value="${item.itemId}" />
    Name: <input type="text" name="itemName" value="${item.itemName}" /><br>
    Price: <input type="number" name="price" value="${item.price}" step="0.01" /><br>
    <input type="submit" value="Update Item" />
</form>

<br>
<a href="item_list.jsp">Back to Items</a>
</body>
</html>
