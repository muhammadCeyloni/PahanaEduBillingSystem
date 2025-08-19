<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration - Pahana Edu</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function validateRegisterForm() {
            let username = document.forms["registerForm"]["username"].value;
            let password = document.forms["registerForm"]["password"].value;

            if (username.trim().length < 4) {
                alert("⚠ Username must be at least 4 characters long");
                return false;
            }
            if (password.trim().length < 6) {
                alert("⚠ Password must be at least 6 characters long");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Register New User</h2>

    <!-- ✅ Error Message Block -->
    <% String errorMsg = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMsg != null) { %>
        <p style="color:red;"><%= errorMsg %></p>
    <% } %>

    <form action="RegisterServlet" method="post">
    Username: <input type="text" name="username" /><br>
    Password: <input type="password" name="password" /><br>
    Role: 
    <select name="role">
        <option value="admin">Admin</option>
        <option value="user">User</option>
    </select><br>
    <input type="submit" value="Register" />
</form>


    <br>
    <a href="login.jsp">Back to Login</a>
</body>
</html>
