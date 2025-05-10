<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attendance Records</title>
    <link rel="stylesheet" type="text/css" href="CSS/attendance.css">
</head>
<body>
    <h2>Attendance Records</h2>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${empty attendanceRecords}">
        <p>No attendance records found.</p>
    </c:if>
    <table>
        <tr>
            <th>Attendance ID</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="record" items="${attendanceRecords}">
            <tr>
                <td>${record.id}</td>
                <td>${record.date}</td>
                <td>${record.status}</td>
                <td>
                    <form action="UpdateAttendanceServlet" method="post" style="display:inline;">
                        <input type="hidden" name="attendanceId" value="${record.id}">
                        <input type="hidden" name="studentId" value="${param.studentId}">
                        <input type="date" name="date" value="${record.date}" required>
                        <select name="status" required>
                            <option value="Present" ${record.status == 'Present' ? 'selected' : ''}>Present</option>
                            <option value="Absent" ${record.status == 'Absent' ? 'selected' : ''}>Absent</option>
                        </select>
                        <input type="submit" value="Update">
                    </form>
                    <form action="DeleteAttendanceServlet" method="post" style="display:inline;">
                        <input type="hidden" name="attendanceId" value="${record.id}">
                        <input type="hidden" name="studentId" value="${param.studentId}">
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="ViewStudentsServlet">Back to Students</a>
</body>
</html>