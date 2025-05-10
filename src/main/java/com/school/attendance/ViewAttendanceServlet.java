package com.school.attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAttendanceServlet")
public class ViewAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static class Attendance {
        private int id;
        private String date;
        private String status;

        public Attendance(int id, String date, String status) {
            this.id = id;
            this.date = date;
            this.status = status;
        }

        public int getId() { return id; }
        public String getDate() { return date; }
        public String getStatus() { return status; }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId;
        try {
            studentId = Integer.parseInt(request.getParameter("studentId"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Student ID");
            request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
            return;
        }

        String checkStudentQuery = "SELECT COUNT(*) FROM student WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(checkStudentQuery)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                request.setAttribute("error", "Student ID does not exist");
                request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        List<Attendance> attendanceRecords = new ArrayList<>();
        String query = "SELECT a.attendanceID, a.date, a.status FROM attendance a WHERE a.studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                attendanceRecords.add(new Attendance(
                    rs.getInt("attendanceID"),
                    rs.getString("date"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        request.setAttribute("attendanceRecords", attendanceRecords);
        request.getRequestDispatcher("attendance.jsp").forward(request, response);
    }
}