package Board;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/Board/Delete")
public class DeleteBoardServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException , IOException {
		
		PrintWriter out = response.getWriter();
		
		
		out.println(" 아 제발 출력되라 부탁이다 아 ㅇㄴ러ㅣㅏㄴㅁ얼짐더래쟏러ㅐ야");
		
		response.sendRedirect("/EggCho/index.jsp");
		
		
	}

}
