<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance Records</title>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/attendence.css" rel="stylesheet">
    <!-- FontAwesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <!-- Include Header -->
    <%@ include file="components/header.jsp" %>

    <div class="container">
        <h2>Attendance Records</h2>
        <c:if test="${not empty error}">
            <p class="text-danger">${error}</p>
        </c:if>
        <c:if test="${empty attendanceRecords}">
            <p class="text-muted">No attendance records found.</p>
        </c:if>
        <table class="table table-striped">
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
                        <form action="${pageContext.request.contextPath}/UpdateAttendanceServlet" method="post" style="display:inline-block; margin-right: 10px;">
                            <input type="hidden" name="attendanceId" value="${record.id}">
                            <input type="hidden" name="studentId" value="${param.studentId}">
                            <input type="date" name="date" value="${record.date}" class="form-control-sm" required style="display:inline-block; width: auto;">
                            <select name="status" class="form-select-sm" required style="display:inline-block; width: auto;">
                                <option value="Present" ${record.status == 'Present' ? 'selected' : ''}>Present</option>
                                <option value="Absent" ${record.status == 'Absent' ? 'selected' : ''}>Absent</option>
                            </select>
                            <button type="submit" class="btn btn-warning btn-sm" title="Update">
                                <i class="fas fa-edit"></i>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/DeleteAttendanceServlet" method="post" style="display:inline-block;">
                            <input type="hidden" name="attendanceId" value="${record.id}">
                            <input type="hidden" name="studentId" value="${param.studentId}">
                            <button type="submit" class="btn btn-danger btn-sm" title="Delete">
                                <i class="fas fa-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/ViewStudentsServlet" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Back to Students</a>
    </div>

    <!-- Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/script/header.js"></script>
    <script src="${pageContext.request.contextPath}/script/attendance.js"></script>
</body>
</html>