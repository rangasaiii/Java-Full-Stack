package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class EnterAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EnterAttendanceServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            // Retrieve parameters from the request
            String rollno = request.getParameter("rollno");
            int course1Attendance = Integer.parseInt(request.getParameter("course1"));
            int course2Attendance = Integer.parseInt(request.getParameter("course2"));
            int course3Attendance = Integer.parseInt(request.getParameter("course3"));
            int course4Attendance = Integer.parseInt(request.getParameter("course4"));
            int course5Attendance = Integer.parseInt(request.getParameter("course5"));
            int course6Attendance = Integer.parseInt(request.getParameter("course6"));

            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "Rsai");

            // Prepare SQL statement for insertion
            String query = "INSERT INTO attendance (rollno, course1, course2, course3, course4, course5, course6) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(rollno));
            ps.setInt(2, course1Attendance);
            ps.setInt(3, course2Attendance);
            ps.setInt(4, course3Attendance);
            ps.setInt(5, course4Attendance);
            ps.setInt(6, course5Attendance);
            ps.setInt(7, course6Attendance);

            // Execute the SQL statement
            int rowsAffected = ps.executeUpdate();

            // Send response based on the success of insertion
            if (rowsAffected > 0) {
                response.getWriter().println("<html><body><h3>Attendance records inserted successfully!</h3></body></html>");
            } else {
                response.getWriter().println("<html><body><h3>Failed to insert attendance records.</h3></body></html>");
            }

            // Close PreparedStatement and Connection
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        }
    }
}
