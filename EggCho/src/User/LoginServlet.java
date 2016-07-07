package User;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet("/User/login")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException, IOException{
		
		
		PrintWriter out = response.getWriter();
		out.println("되는지 확인이나 해보자 ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/study?characterEncoding=UTF-8","root","1234");
			stmt = conn.prepareStatement("select mname,pwd from members where mname=? and pwd=?");
			
			stmt.setString(1,request.getParameter("email"));
			stmt.setString(2, request.getParameter("password1"));
			
			out.println(request.getParameter("email"));
			out.println(request.getParameter("password1"));
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				HttpSession session = request.getSession();
				
				session.setAttribute("email", request.getParameter("email"));
				session.setAttribute("password1", request.getParameter("password1"));
				
				response.sendRedirect("/EggCho/index.jsp");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
