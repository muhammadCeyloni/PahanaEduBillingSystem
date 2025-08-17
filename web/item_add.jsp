<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><title>Add Item</title>
<script>
function validateItem(){
  const title = document.forms["itemForm"]["title"].value.trim();
  const price = parseFloat(document.forms["itemForm"]["price"].value);
  if(title.length<2){ alert("Title too short"); return false; }
  if(isNaN(price) || price<=0){ alert("Price must be positive"); return false; }
  return true;
}
</script>
</head>
<body>
<h2>Add Item</h2>
<% String err=(String)request.getAttribute("errorMessage");
   if(err!=null){ %><p style="color:red;"><%=err%></p><% } %>

<form name="itemForm" action="ItemAddServlet" method="post" onsubmit="return validateItem()">
  Title: <input name="title" required><br><br>
  Price: <input name="price" type="number" step="0.01" required><br><br>
  <button type="submit">Save</button>
</form>

<br><a href="item_list.jsp">Back to Items</a>
</body></html>
