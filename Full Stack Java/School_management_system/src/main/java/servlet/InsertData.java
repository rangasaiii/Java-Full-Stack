package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
/**
 * Servlet implementation class InsertData
 */
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try{ 
    	    Class.forName("com.mysql.jdbc.Driver"); 
    	    Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","Rsai"); 
    	    PreparedStatement st = con.prepareStatement("insert into student values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    	     st.setInt(1, Integer.valueOf(request.getParameter("rollno")));
    	     st.setString(2, request.getParameter("name"));
    	     st.setString(3, request.getParameter("section"));
    	     st.setString(4, request.getParameter("Gender"));
    	     st.setString(5, request.getParameter("CurriculumPlan"));
    	     st.setString(6,request.getParameter("Department"));
    	     st.setInt(7, Integer.valueOf(request.getParameter("Cgpa")));
    	     st.setInt(8, Integer.valueOf(request.getParameter("Semester")));
    	     st.setString(9, request.getParameter("CourseName"));
    	     st.setString(10, request.getParameter("College"));
    	     st.setString(11,request.getParameter("Address"));
    	     st.executeUpdate();
    	     st.close();
    	     con.close(); 
    	     PrintWriter out = response.getWriter();
    	     out.println("<html><head><style>");
    	     out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f4f4f9; margin: 0; }");
    	     out.println(".container { background-color: #fff; border: 2px solid #4CAF50; padding: 30px; border-radius: 10px; box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1); text-align: center; max-width: 400px; }");
    	     out.println(".message { font-size: 24px; font-weight: bold; color: #4CAF50; margin: 0; padding: 10px; border: 2px solid #4CAF50; border-radius: 5px; display: inline-block; }");
    	     out.println(".marquee-container { margin-top: 20px; }");
    	     out.println("marquee { font-size: 18px; color: #333; }");
    	     out.println("</style></head><body>");
    	     out.println("<div class='container'><p class='message'>Successfully Inserted</p>");
    	     out.println("<div class='marquee-container'><marquee>Thank you</marquee></div></div>");
    	     out.println("</body></html>"); 

    	}catch(Exception e){
    	      System.out.println(e);} 
    	}
    	}
