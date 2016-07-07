package User;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/User/failTest")
public class FailTest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException , IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
		
		 //  out.println("location='/EggCho/index-07-05.jsp'");
		   out.println("window.onload = function(){ console.log('y1') }");
		   out.println("console.log('y1')");
		  out.println("$('#testt').last().trigger('click');");
		  out.println("console.log('y2')");
		 out.println("alert('User or password incorrect');");
		 out.println("location='/EggCho/index-07-05.jsp'");
		   out.println("</script>");
		   
		  
		
		
		
	}
	
}
