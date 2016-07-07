package User;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/EggCho/index.jsp");
		
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/EggCho/index.jsp");
	}
	

}
