<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>School Attendance Management System</title>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/CSS/home.css" rel="stylesheet">
    <!-- FontAwesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
</head>
<body>
    <!-- Include Header -->
    <%@ include file="components/header.jsp" %>

    <!-- Main Content -->
    <div class="container">
        <h1 class="page-title">Welcome to School Attendance System</h1>
        <div class="card-container">
            <!-- View Students Card -->
            <div class="card text-center">
                <div class="card-body">
                    <i class="fas fa-users card-icon"></i>
                    <h5 class="card-title">View Students</h5>
                    <p class="card-text">Check all students</p>
                    <a href="${pageContext.request.contextPath}/ViewStudentsServlet" class="btn btn-primary">Go to Students</a>
                </div>
            </div>

            <!-- Mark Attendance Card -->
            <div class="card text-center">
                <div class="card-body">
                    <i class="fas fa-check-square card-icon"></i>
                    <h5 class="card-title">Mark Attendance</h5>
                    <p class="card-text">Record attendance for students on a specific date.</p>
                    <a href="${pageContext.request.contextPath}/ViewStudentsServlet" class="btn btn-success">Mark Attendance</a>
                </div>
            </div>

            <!-- View Attendance Card -->
            <div class="card text-center">
                <div class="card-body">
                    <i class="fas fa-calendar-alt card-icon"></i>
                    <h5 class="card-title">View Attendance</h5>
                    <p class="card-text">View attendance records for individual students.</p>
                    <a href="${pageContext.request.contextPath}/ViewAttendanceServlet?studentId=1" class="btn btn-info">View Attendance</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/script/header.js"></script>
    <script src="${pageContext.request.contextPath}/script/home.js"></script>
</body>
</html>