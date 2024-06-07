package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnterMarksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Retrieve parameters from the request
        int rollNo = Integer.parseInt(request.getParameter("rollno"));
        int course1 = Integer.parseInt(request.getParameter("course1"));
        int course2 = Integer.parseInt(request.getParameter("course2"));
        int course3 = Integer.parseInt(request.getParameter("course3"));
        int course4 = Integer.parseInt(request.getParameter("course4"));
        int course5 = Integer.parseInt(request.getParameter("course5"));
        int course6 = Integer.parseInt(request.getParameter("course6"));

        PrintWriter out = response.getWriter();
        
        // Database connection and insert operation
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "Rsai");

            // Use REPLACE INTO to insert or update the marks
            String query = "REPLACE INTO marks (rollno, course1, course2, course3, course4, course5, course6) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, rollNo);
            ps.setInt(2, course1);
            ps.setInt(3, course2);
            ps.setInt(4, course3);
            ps.setInt(5, course4);
            ps.setInt(6, course5);
            ps.setInt(7, course6);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<html><body><h3>Marks inserted successfully!</h3></body></html>");
            } else {
                out.println("<html><body><h3>Failed to insert marks.</h3></body></html>");
            }
            
            ps.close();
            con.close();
        } catch (ClassNotFoundException e) {
            out.println("<html><body><h3>Failed to load database driver.</h3></body></html>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
