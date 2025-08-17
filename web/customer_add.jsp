<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><title>Add Customer</title>
<script>
function validateCustomer(){
  const acc = document.forms["custForm"]["account_no"].value.trim();
  const name = document.forms["custForm"]["name"].value.trim();
  const phone = document.forms["custForm"]["phone"].value.trim();
  if(acc.length < 3){ alert("Account No must be at least 3 chars"); return false; }
  if(name === ""){ alert("Name is required"); return false; }
  if(!/^\+?\d{7,15}$/.test(phone)){ alert("Invalid phone number"); return false; }
  return true;
}
</script>
</head>
<body>
<h2>Add New Customer</h2>

<% String err=(String)request.getAttribute("errorMessage");
   if(err!=null){ %><p style="color:red;"><%=err%></p><% } %>

<form name="custForm" action="CustomerAddServlet" method="post" onsubmit="return validateCustomer()">
  Account No: <input name="account_no" required><br><br>
  Name: <input name="name" required><br><br>
  Address: <input name="address" required><br><br>
  Phone: <input name="phone" required><br><br>
  Units Consumed: <input type="number" name="units" value="0" min="0" required><br><br>
  <button type="submit">Save</button>
</form>

<br>
<a href="customer_list.jsp">Back to Customer List</a>
</body></html>
