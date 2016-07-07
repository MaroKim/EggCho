package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/User/SignUp")
public class MemberAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println("submit 되나???");
		
		String a = request.getParameter("email");
		String b = request.getParameter("password");
		String c = request.getParameter("password2");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL="jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf8";
			//String DB_URL="jdbc:mysql://localhost:3306/Project1?useUnicode=true&characterEncoding=utf8";
			String DB_USER ="root";
			//String DB_PASSWORD="1234";
			String DB_PASSWORD="1234";
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			pstmt =conn.prepareStatement("select * from members where email=?");
			pstmt.setString(1, a);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('이미 있는 아이디입니다.');");
				out.println("location= '/EggCho/index-07-05.jsp';");
				out.println("</script>");
			}
			else{
				
				System.out.println("abc");
				pstmt = null;
				pstmt = conn.prepareStatement("INSERT INTO members(email,pwd,mname,cre_date,mod_date) values(?,?,'ha',now(),now())");
				pstmt.setString(1,request.getParameter("email"));
				pstmt.setString(2,request.getParameter("password"));
				//pstmt.setString(3,request.getParameter("password2"));
				pstmt.executeUpdate();;
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('회원가입이 완료 되었습니다.');");
				out.println("location= '/EggCho/index-07-05.jsp';");
				out.println("</script>");
				
			}
			
			
			/*
			while(rs.next()){
				if(a.equals(rs.getString("email"))){
						a = "";
						
						break;
				}
			}
			if(a.equals("")){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('이미 있는 아이디입니다.');");
				out.println("location= '/Eggcho/index-07-05.jsp';");
				out.println("</script>");
			}
			
			if(!b.equals(c)){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('비밀번호와 비밀번호확인이 다릅니다.');");
				out.println("location= '/Eggcho/index-07-05.jsp';");
				out.println("</script>");
			}
			
			if(!a.equals("")&& b.equals(c)){
					System.out.println("abc");
					pstmt = null;
					pstmt = conn.prepareStatement("INSERT INTO members(email,pwd) values(?,?)");
					pstmt.setString(1,request.getParameter("email"));
					pstmt.setString(2,request.getParameter("password"));
					//pstmt.setString(3,request.getParameter("password2"));
					pstmt.executeUpdate();;
					PrintWriter out = response.getWriter();
					out.println("<script language='javascript'>");
					out.println("alert('회원가입이 완료 되었습니다.');");
					out.println("location= '/Eggcho/index-07-05.jsp';");
					out.println("</script>");
			}
			*/
		}catch (Exception e){
			throw new ServletException(e);
		}finally{
			try{if (rs !=null) rs.close();}catch(Exception e){}
			try{if (pstmt !=null) pstmt.close();}catch(Exception e){}
		//	try{if (conn !=null) conn.close();}catch(Exception e){}
		}
		
	}

}