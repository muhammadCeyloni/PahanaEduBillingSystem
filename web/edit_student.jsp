
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student - Pahana Edu</title>
</head>
<body>
    <h2>Edit Student</h2>
    
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, dao.DBConnection" %>

    <%
        String id = request.getParameter("id");
        String name = "", email = "", course = "";
        double fee = 0.0;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
                course = rs.getString("course");
                fee = rs.getDouble("fee");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <form action="UpdateStudentServlet" method="post">
        <input type="hidden" name="student_id" value="<%= id %>">

        <label>Name:</label>
        <input type="text" name="name" value="<%= name %>" required><br><br>

        <label>Email:</label>
        <input type="email" name="email" value="<%= email %>" required><br><br>

        <label>Course:</label>
        <input type="text" name="course" value="<%= course %>" required><br><br>

        <label>Fee:</label>
        <input type="number" step="0.01" name="fee" value="<%= fee %>" required><br><br>

        <button type="submit">Update Student</button>
    </form>

    <br>
    <a href="student_list.jsp">Back to Student List</a>
</body>
</html>
