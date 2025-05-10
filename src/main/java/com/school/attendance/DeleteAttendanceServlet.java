package com.school.attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAttendanceServlet")
public class DeleteAttendanceServlet extends HttpServlet {
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

        String query = "DELETE FROM attendance WHERE attendanceID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, attendanceId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                request.setAttribute("error", "Attendance record not found");
                request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
                return;
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Failed to delete attendance: " + e.getMessage());
            request.getRequestDispatcher("ViewAttendanceServlet?studentId=" + studentId).forward(request, response);
            return;
        }

        response.sendRedirect("ViewAttendanceServlet?studentId=" + studentId);
    }
}