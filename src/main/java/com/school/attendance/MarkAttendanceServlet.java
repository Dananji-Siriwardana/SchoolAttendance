package com.school.attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MarkAttendanceServlet")
public class MarkAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(MarkAttendanceServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("doPost method called for MarkAttendanceServlet");

        int studentId;
        try {
            studentId = Integer.parseInt(request.getParameter("studentId"));
            LOGGER.info("Student ID: " + studentId);
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid Student ID: " + request.getParameter("studentId"));
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
                LOGGER.warning("Student ID does not exist: " + studentId);
                request.setAttribute("error", "Student ID does not exist");
                request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            LOGGER.severe("Database error during student check: " + e.getMessage());
            throw new ServletException("Database error", e);
        }

        String date = request.getParameter("date");
        LOGGER.info("Date: " + date);
        LocalDate inputDate;
        try {
            inputDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            LOGGER.warning("Invalid date format: " + date);
            request.setAttribute("error", "Invalid date format. Use YYYY-MM-DD.");
            request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
            return;
        }
        LocalDate today = LocalDate.now();
        LOGGER.info("Today: " + today + ", Input Date: " + inputDate);
        if (inputDate.isAfter(today)) {
            LOGGER.warning("Cannot mark attendance for a future date: " + date);
            request.setAttribute("error", "Cannot mark attendance for a future date");
            request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
            return;
        }

        String status = request.getParameter("status");
        LOGGER.info("Status: " + status);
        if (!status.equals("Present") && !status.equals("Absent")) {
            LOGGER.warning("Invalid status: " + status);
            request.setAttribute("error", "Invalid status");
            request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
            return;
        }

        String checkDuplicateQuery = "SELECT COUNT(*) FROM attendance WHERE studentID = ? AND date = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(checkDuplicateQuery)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, date);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                LOGGER.warning("Duplicate attendance found for studentId=" + studentId + ", date=" + date);
                request.setAttribute("error", "Attendance for this student on this date already exists");
                request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            LOGGER.severe("Database error during duplicate check: " + e.getMessage());
            throw new ServletException("Database error", e);
        }

        String query = "INSERT INTO attendance (studentID, date, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, date);
            stmt.setString(3, status);
            LOGGER.info("Inserting attendance: studentId=" + studentId + ", date=" + date + ", status=" + status);
            stmt.executeUpdate();
            LOGGER.info("Attendance inserted successfully");
        } catch (SQLException e) {
            LOGGER.severe("Failed to mark attendance: " + e.getMessage());
            request.setAttribute("error", "Failed to mark attendance: " + e.getMessage());
            request.getRequestDispatcher("ViewStudentsServlet").forward(request, response);
            return;
        }

        LOGGER.info("Redirecting to ViewStudentsServlet");
        response.sendRedirect("ViewStudentsServlet");
    }
}