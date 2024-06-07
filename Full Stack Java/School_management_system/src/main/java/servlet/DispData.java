package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DispData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DispData() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String rno = request.getParameter("rollno");
            int rn = Integer.parseInt(rno);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "Rsai");

            String query = "SELECT student.rollno, student.name, student.department, student.Cgpa, marks.course1, marks.course2, marks.course3, marks.course4, marks.course5, marks.course6 " +
                           "FROM student " +
                           "JOIN marks ON student.rollno = marks.rollno " +
                           "WHERE student.rollno = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, rn);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int course1 = rs.getInt("course1");
                int course2 = rs.getInt("course2");
                int course3 = rs.getInt("course3");
                int course4 = rs.getInt("course4");
                int course5 = rs.getInt("course5");
                int course6 = rs.getInt("course6");

                String status = (course1 >= 35 && course2 >= 35 && course3 >= 35 && course4 >= 35 && course5 >= 35 && course6 >= 35) ? "Pass" : "Fail";

                out.println("<html>");
                out.println("<head>");
                out.println("<title>Student Details and Marks</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 20px; }");
                out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; background-color: #ffffff; }");
                out.println("th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }");
                out.println("th { background-color: #00796b; color: #ffffff; }");
                out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
                out.println("tr:hover { background-color: #e0f7fa; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Student Details and Marks</h1>");
                out.println("<table>");
                out.println("<tr><th>Roll No</th><td>" + rs.getInt("rollno") + "</td></tr>");
                out.println("<tr><th>Name</th><td>" + rs.getString("name") + "</td></tr>");
                out.println("<tr><th>Department</th><td>" + rs.getString("department") + "</td></tr>");
                out.println("<tr><th>fees</th><td>" + rs.getDouble("Cgpa") + "</td></tr>");
                out.println("<tr><th>Course 1 Marks</th><td>" + course1 + "</td></tr>");
                out.println("<tr><th>Course 2 Marks</th><td>" + course2 + "</td></tr>");
                out.println("<tr><th>Course 3 Marks</th><td>" + course3 + "</td></tr>");
                out.println("<tr><th>Course 4 Marks</th><td>" + course4 + "</td></tr>");
                out.println("<tr><th>Course 5 Marks</th><td>" + course5 + "</td></tr>");
                out.println("<tr><th>Course 6 Marks</th><td>" + course6 + "</td></tr>");
                out.println("<tr><th>Status</th><td>" + status + "</td></tr>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html><body><h3>No student found with the given Roll No.</h3></body></html>");
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            out.close();
        }
    }
}
