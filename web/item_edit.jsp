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
  <input type="hidden" name="id" value="<%=id%>">
  Title: <input name="title" value="<%=title%>" required><br><br>
  Price: <input name="price" type="number" step="0.01" value="<%=price%>" required><br><br>
  <button type="submit">Update</button>
</form>
<br>
<a href="item_list.jsp">Back to Items</a>
</body>
</html>
