<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Pahana Edu</title>
    <script>
        function validateLoginForm() {
            let username = document.forms["loginForm"]["username"].value;
            let password = document.forms["loginForm"]["password"].value;

            if (username.trim() === "") {
                alert("⚠ Username is required");
                return false;
            }
            if (password.trim() === "") {
                alert("⚠ Password is required");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Login</h2>

    <!-- ✅ Error Message Block -->
    <% String errorMsg = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMsg != null) { %>
        <p style="color:red;"><%= errorMsg %></p>
    <% } %>

    <form name="loginForm" action="LoginServlet" method="post" onsubmit="return validateLoginForm()">
        <label>Username:</label>
        <input type="text" name="username" required><br><br>

        <label>Password:</label>
        <input type="password" name="password" required><br><br>

        <button type="submit">Login</button>
    </form>

    <br>
    <a href="register.jsp">Create an Account</a>
</body>
</html>
