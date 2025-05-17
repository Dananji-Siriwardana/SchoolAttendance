# School Management System

A simple web-based application to manage student attendance, built using Java Servlets, JSP, and MySQL. The system allows users to view student lists, mark attendance and  update/delete attendance records.

## Features

- **View Students**: Display a list of students with their ID, name, and class.
- **Mark Attendance**: Record attendance for students with a date and status (Present/Absent).
- **View Attendance**: View attendance records for a specific student with options to filter by date range.
- **Update/Delete Attendance**: Modify or delete existing attendance records.
- **Responsive UI**: Styled with custom CSS for a clean and user-friendly interface.

## Tech Stack

- **Backend**: Java Servlets
- **Frontend**: JSP (JavaServer Pages), HTML, CSS, Javascript
- **Database**: MySQL wokbench
- **Server**: Apache Tomcat
- **Libraries**:
  - FontAwesome for icons
  - MySQL JDBC Driver for database connectivity

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 9.x
- MySQL Server 8.x
- MySQL JDBC Driver (`mysql-connector-java`)
- IDE (e.g., Eclipse, IntelliJ IDEA)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/SchoolManagementSystem.git
   cd SchoolManagementSystem

2. **Configure Database Connection**
   ```bash
   private static final String URL = "jdbc:mysql://localhost:3306/school_management?useSSL=false&serverTimezone=UTC";
   private static final String USER = "your-username";
   private static final String PASSWORD = "your-password";
   
3. **Add MySQL JDBC Driver**
   Download the MySQL JDBC driver (mysql-connector-java-8.x.x.jar) from [Maven Repository](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.33)
   Add it to your project:
      In Eclipse: Right-click project > Build Path > Add External Archives > Select the JAR.
      Alternatively, place it in Tomcat/lib.
   
5. **Deploy the Application**
   
7. **Open your browser and navigate to**
   ```bash
    http://localhost:8090/SchoolManagementSystem/home.jsp
   
8. **Usage** 
  View Students: Navigate to /ViewStudentsServlet to see the list of students.
  Mark Attendance: Click "Mark Attendance" to open a modal, enter student details, and submit.
  View Attendance: Click "View Attendance" for a student to see their records, with options to update/delete.
