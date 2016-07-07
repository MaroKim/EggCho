package User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;



		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		try{

			String sqlresultid = null;
			String sqlresultpwd = null;

			String id = request.getParameter("email");
			String pwd = request.getParameter("password");


			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			pstmt = conn.prepareStatement("select id,pwd from user where id=? and pwd=?");
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			//
			String test = request.getParameter("email");
			System.out.println(test);
					//
			rs = pstmt.executeQuery();
			
			

			if(rs.next()){
				
				HttpSession session = request.getSession();
				session.setAttribute("email", request.getParameter("email"));
				//session.setAttribute("password", request.getParameter("password"));
				
				
				sqlresultid =rs.getString("id");       
				sqlresultpwd =rs.getString("pwd");
			 	
				
				
				
				if(id.equals(sqlresultid) && pwd.equals(sqlresultpwd) ){
					PrintWriter out = response.getWriter();
					out.println("<script language='javascript'>");
					out.println("alert('로그인 되었습니다.');");
					out.println("location= '/EggCho/index.jsp';");
					out.println("</script>");
				}
				//response.sendRedirect("/EggCho/index.jsp");    //여기 써야됨 그래야 로그아웃됨	
			
				
			  }else{
					PrintWriter out = response.getWriter();
					out.println("<script language='javascript'>");
					out.println("alert('아이디와 비밀번호를 확인해주세요.');");
					out.println("location= '/EggCho/index.jsp';");
					out.println("</script>");
				}

			}catch (Exception e){
			throw new ServletException(e);
		}finally{
			try{if (rs !=null) rs.close();}catch(Exception e){}
			try{if (pstmt !=null) pstmt.close();}catch(Exception e){}
		//	try{if (conn != null) conn.close();}catch(Exception e) {}
		}
			
	}

}
