<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %>
<%@page import="java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String realFolder="/Users/maro/EggCho/EggCho/WebContent/Image";
	//String realFolder = request.getSession().getServletContext().getRealPath("/")+"upload";
	//String filename1="/Users/maro/Desktop/WebProgramming/apache-tomcat-8.0.35/webapps/upload";
	int maxSize=1024*1024*5;
	String encType="utf-8";
	String savefile="img";
		
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	Class.forName("com.mysql.jdbc.Driver");	
	String imgPath = "http://192.168.0.242:8080/EggCho/Image";
	
	String DB_URL = "jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf8";
	String DB_USER = "root";
	String DB_PASSWORD="1234";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		stmt = conn.createStatement();
		
		String query = "select * from uploadtest";
		rs = stmt.executeQuery(query);
		
		while(rs.next())
		{
			//System.out.println("path : "+realFolder);
			//System.out.println("file : "+rs.getString(2));
			%>
			<%=realFolder%>/<%=rs.getString(2)%>
			<img src="<%=imgPath %>/<%=rs.getString(2) %>" width="204.9" height="174.2">
			<%=rs.getInt(1)%>
			<%=rs.getString(3)%><br>
		<% }
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
%>

<!-- 파일업로드 하면 분명히 파일경로값을 가져갈껀데 거기서 경로는 자르고 이름만 데려간다.그래서 이름만 저장한다. -->
	<form action="upload" method="post" enctype="multipart/form-data">
		name : <input type="text" name="name"><br>
		file : <input type="file" name="file"><br>
		<input type="submit" name="upload"><br>
	</form>
</body>
</html>