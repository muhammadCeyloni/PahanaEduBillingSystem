<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration - Pahana Edu</title>
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

    <form name="registerForm" action="RegisterServlet" method="post" onsubmit="return validateRegisterForm()">
        <label>Username:</label>
        <input type="text" name="username" required><br><br>

        <label>Password:</label>
        <input type="password" name="password" required><br><br>

        <label>Role:</label>
        <select name="role">
            <option value="student">Student</option>
            <option value="admin">Admin</option>
        </select><br><br>

        <button type="submit">Register</button>
    </form>

    <br>
    <a href="login.jsp">Back to Login</a>
</body>
</html>
