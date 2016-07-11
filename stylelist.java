package User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class stylelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String realFolder = "";
		String filename1 = "";
		int maxSize = 1024*1024*5;
		String encType = "utf-8";
		String savefile = "C://Users/lg/workspace/Project/WebContent/img";
		ServletContext scontext = getServletContext();
//		realFolder = scontext.getRealPath(savefile);
		realFolder = savefile;
		System.out.println(realFolder);
		
	
		MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		Enumeration<?> files = multi.getFileNames();
		String file1 = (String)files.nextElement();
		filename1 = multi.getFilesystemName(file1).toString();
		//String filename2 = multi.getFilesystemName("file");
		System.out.println(filename1);
		String fullpath = realFolder + "/" + filename1;
		
		String[] arry = multi.getParameterValues("checkbox");
		String a=null;
		String b = multi.getParameter("contents");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("email");
		
		
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			pstmt = conn.prepareStatement("INSERT INTO POST(id,category,CONTENTS,WDAY,IMAGENAME) values(?,?,?,NOW(),?)");
			for(int i=0;i<arry.length;i++){			
				a = arry[i];
				if(i<arry.length-1)
					a += "&";
			}
			
			pstmt.setString(1,id);
			pstmt.setString(2,a);
			pstmt.setString(3,b);
			pstmt.setString(4,filename1);
			pstmt.executeUpdate();
			
			
			
			pstmt2 = conn.prepareStatement("INSERT INTO IMAGE(IMAGENAME) values(?)");
			pstmt2.setString(1,filename1);
			pstmt2.executeUpdate();
			response.sendRedirect("/Project/index.jsp");
			
		}catch (Exception e){
			throw new ServletException(e);
		}finally{
			try{if (rs !=null) rs.close();}catch(Exception e){}
			try{if (pstmt !=null) pstmt.close();}catch(Exception e){}
		//	try{if (conn !=null) conn.close();}catch(Exception e){}
		}
		
	}

}



