package com.school.attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAttendanceServlet")
public class UpdateAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int attendanceId;
        try {
            attendanceId = Integer.parseInt(request.getParameter("attendanceId"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Attendance ID");
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + request.getParameter("studentId")).forward(request, response);
            return;
        }

        int studentId;
        try {
            studentId = Integer.parseInt(request.getParameter("studentId"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Student ID");
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + request.getParameter("studentId")).forward(request, response);
            return;
        }

        String date = request.getParameter("date");
        LocalDate inputDate;
        try {
            inputDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Invalid date format. Use YYYY-MM-DD.");
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
            return;
        }
        LocalDate today = LocalDate.now();
        if (inputDate.isAfter(today)) {
            request.setAttribute("error", "Cannot update attendance for a future date");
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
            return;
        }

        String status = request.getParameter("status");
        if (!status.equals("Present") && !status.equals("Absent")) {
            request.setAttribute("error", "Invalid status");
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
            return;
        }

        String query = "UPDATE attendance SET date = ?, status = ? WHERE attendanceID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, date);
            stmt.setString(2, status);
            stmt.setInt(3, attendanceId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                request.setAttribute("error", "Attendance record not found");
                request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Failed to update attendance: " + e.getMessage());
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
            return;
        }

        response.sendRedirect("ViewAttendanceServlet?studentId=" + studentId);
    }
}