<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students List</title>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/student.css" rel="stylesheet">
    <!-- FontAwesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <!-- Include Header -->
    <%@ include file="components/header.jsp" %>

    <div class="container">
        <h3>Students</h3>
        <c:if test="${not empty error}">
            <p class="text-danger">${error}</p>
        </c:if>
        <div class="table-container">
            <table class="table table-striped">
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
                            <a href="${pageContext.request.contextPath}/ViewAttendanceServlet?studentId=${student.id}" class="btn btn-info btn-sm"><i class="fas fa-eye"></i> View</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!-- Mark Attendance Button -->
            <button class="btn btn-success mark-attendance-btn" onclick="document.getElementById('attendanceModal').showModal()">Mark Attendance</button>
        </div>

        <!-- Popup Modal for Marking Attendance -->
        <dialog id="attendanceModal" class="modal">
            <form action="${pageContext.request.contextPath}/MarkAttendanceServlet" method="post" class="row g-3">
                <div class="modal-header">
                    <h3>Mark Attendance</h3>
                    <button type="button" class="close-btn" onclick="document.getElementById('attendanceModal').close()">×</button>
                </div>
                <div class="modal-body">
                    <div class="col-md-4">
                        <label for="studentId" class="form-label">Student ID:</label>
                        <input type="number" name="studentId" id="studentId" class="form-control" required>
                    </div>
                    <div class="col-md-4">
                        <label for="date" class="form-label">Date:</label>
                        <input type="date" name="date" id="date" class="form-control" required>
                    </div>
                    <div class="col-md-4">
                        <label for="status" class="form-label">Status:</label>
                        <select name="status" id="status" class="form-select" required>
                            <option value="Present">Present</option>
                            <option value="Absent">Absent</option>
                        </select>
                    </div>
                    <div class="col-12">
                        <input type="submit" value="Mark Attendance" class="btn btn-primary">
                    </div>
                </div>
            </form>
        </dialog>
    </div>

    <!-- Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/script/header.js"></script>
    <script src="${pageContext.request.contextPath}/script/students.js"></script>
</body>
</html>