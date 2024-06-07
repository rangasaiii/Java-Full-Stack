package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FacultyLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform authentication here (e.g., check credentials against database)

        // For demonstration purposes, let's assume a hardcoded credential for simplicity
        if ("facultyuser".equals(username) && "password123".equals(password)) {
            response.sendRedirect("faculty_index.html"); // Redirect to enter marks page upon successful login
        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Invalid username or password</h3></body></html>");
        }
    }
}
