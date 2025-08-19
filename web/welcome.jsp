

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head><title>Welcome</title>
<link rel="stylesheet" href="css/style.css"></head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %> 🎉</h2>
    <a href="logout.jsp">Logout</a>
    <a href="customer_list.jsp">👥 Customer Accounts</a><br>
    <a href="item_list.jsp">📚 Item Management</a><br>
    <a href="help.jsp">❓ Help</a><br>
    <a href="logout.jsp">🚪 Exit (Logout)</a><br>
    <a href="student_list.jsp">📋 View Students</a><br>
    <a href="student_register.jsp">➕ Register Student</a><br>
    <a href="logout.jsp">🚪 Logout</a>
</body>
</html>
