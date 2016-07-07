package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//System.out.println("submit 되나???");
	

	
		String a = request.getParameter("email");
		String b = request.getParameter("password");
		String c = request.getParameter("password2");
		String d = request.getParameter("email");
		int index = a.indexOf("@");
		
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			pstmt = conn.prepareStatement("select id :from user where id =?");
			pstmt.setString(1,a);
			rs = pstmt.executeQuery();
			if(a.equals("")){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('이메일을 입력하세요');");
				out.println("location= '/EggCho/index.jsp';");
				out.println("</script>");
			}
			if(rs.next()){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('중복된 이메일입니다.');");
				out.println("location= '/EggCho/index.jsp';");
				out.println("</script>");
			}
			if(index < 0) {
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('올바른 이메일 형식이 아닙니다.(@)');");
				out.println("location= '/EggCho/index.jsp';");
				out.println("</script>");
			}
			if(index>0){
				String len = a.substring(index,a.length());
				int index2 = len.indexOf(".");
				if(index2 < 4 && index > 6) {
					PrintWriter out = response.getWriter();
					out.println("<script language='javascript'>");
					out.println("alert('올바른 이메일 형식이 아닙니다.ex)test@naver.com');");
					out.println("location= '/EggCho/index.jsp';");
					out.println("</script>");
				}
			}

			if(b.equals("")){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('비밀번호을 입력하세요.');");
				out.println("location= '/EggCho/index.jsp';");
				out.println("</script>");
			}

			if(c.equals("")){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('비밀번호확인을 입력하세요.');");
				out.println("location= '/EggCho/index.jsp';");
				out.println("</script>");
			}
			
			
			if(!b.equals(c)){
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('비밀번호와 비밀번호확인이 다릅니다.');");
				out.println("location= '/EggCho/index.jsp';");
				out.println("</script>");
			}
			
			if(!a.equals("")&& b.equals(c)){
					System.out.println("abc");
					pstmt = null;
					pstmt = conn.prepareStatement("INSERT INTO user(id,pwd) values(?,?)");
					pstmt.setString(1,request.getParameter("email"));
					pstmt.setString(2,request.getParameter("password"));
					pstmt.executeUpdate();;
					PrintWriter out = response.getWriter();
					out.println("<script language='javascript'>");
					out.println("alert('회원가입이 완료 되었습니다.');");
					out.println("location= '/EggCho/index.jsp';");
					out.println("</script>");
			}
		}catch (Exception e){
			throw new ServletException(e);
		}finally{
			try{if (rs !=null) rs.close();}catch(Exception e){}
			try{if (pstmt !=null) pstmt.close();}catch(Exception e){}
		//	try{if (conn !=null) conn.close();}catch(Exception e){}
		}
		
	}

}
