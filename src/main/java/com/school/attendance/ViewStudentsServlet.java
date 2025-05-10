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

@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static class Student {
        private int id;
        private String name;
        private String className;

        public Student(int id, String name, String className) {
            this.id = id;
            this.name = name;
            this.className = className;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getClassName() { return className; }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Student> students = new ArrayList<>();
            String query = "SELECT * FROM student";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                        rs.getInt("studentID"),
                        rs.getString("name"),
                        rs.getString("class")
                    ));
                }
            }
            request.setAttribute("students", students);
            request.getRequestDispatcher("students.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}