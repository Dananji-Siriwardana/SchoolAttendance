<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students List</title>
    <link rel="stylesheet" type="text/css" href="CSS/student.css">
</head>
<body>
    <h2>Attendance Management System</h2>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <h3>Students</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Class</th>
            <th>Action</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.className}</td>
                <td>
                    <a href="ViewAttendanceServlet?studentId=${student.id}">View Attendance</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h3>Mark Attendance</h3>
    <form action="${pageContext.request.contextPath}/MarkAttendanceServlet" method="post">
        <label>Student ID:</label>
        <input type="number" name="studentId" required><br><br>
        <label>Date:</label>
        <input type="date" name="date" required><br><br>
        <label>Status:</label>
        <select name="status" required>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>
        <input type="submit" value="Mark Attendance">
    </form>
</body>
</html>