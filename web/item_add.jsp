<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><title>Add Item</title>
        <link rel="stylesheet" href="css/style.css">
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

<form action="ItemAddServlet" method="post">
    Item Name: <input type="text" name="itemName" /><br>
    Price: <input type="number" name="price" step="0.01" /><br>
    <input type="submit" value="Add Item" />
</form>


<br><a href="item_list.jsp">Back to Items</a>
</body></html>
