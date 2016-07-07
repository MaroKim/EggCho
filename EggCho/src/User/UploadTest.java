package User;
import java.io.*;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/User/test")
public class UploadTest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException , IOException{
		
		
		
		String cg = request.getParameter("cg1");
		System.out.println("category: "+cg);
		//System.out.println("category length : "+cg.length);
		
		
	}

}
