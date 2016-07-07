<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/css.css" />

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %>
	<%@page import="java.sql.*" %>

  </head>

  <body>
  <%
	  
	session = request.getSession();
	String email = (String)session.getAttribute("email");
	String hideTag = "inline";
	String hideLogout ="none";
	String checkSession ="#login";
	
	if(email!=null){
		hideTag = "none";
		hideLogout = "inlne";
		checkSession="#myRequest";
	}
	%>
  <div class="navbar-fixed">
    <nav>
     <div class="nav-wrapper light-blue darken-1 ">
       <a href="#!" class="brand-logo center ">EggCho</a>
       <a href="#" data-activates="mobile-demo " class="button-collapse"><i class="material-icons ">menu</i></a>
       <ul class="right hide-on-med-and-down">
         <li><a href="#login" class="modal-trigger" data-target="login" style="display:<%=hideTag%>"id="testt">로그인</a></li>
         <li><a href="User/logout" style="display:<%=hideLogout%>">로그아웃</a></li>
         <li><a href="#signup" class="modal-trigger" data-target="signup"style="display:<%=hideTag%>">회원가입</a></li>
       </ul>
       <ul class="side-nav" id="mobile-demo">
         <li><a href="#login" class="modal-trigger" data-target="login">로그인</a></li>
         <li><a href="#signup" class="modal-trigger" data-target="signup">회원가입</a></li>
       </ul>
     </div>
   </nav>
</div>
   <div class="container">
<div class="row">
<%
	//POST 와 IMAGE table을 join 하여 사진과 컨텐츠를 불러오기 
	Class.forName("com.mysql.jdbc.Driver");	
	String imgPath = "http://222.239.250.157:8080/EggCho/upload"; // upload 폴더안에 사진이 저장될 예정 
	
	String DB_URL = "jdbc:mysql://localhost/Project1?useUnicode=true&characterEncoding=utf8";
	String DB_USER = "root";
	String DB_PASSWORD="dprmch123";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try{
		conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		stmt = conn.createStatement();
		
		String query = "select POST.PNUMBER,id,category,contents,wday,IMAGE.IMAGENAME from POST inner join IMAGE on POST.PNUMBER = IMAGE.PNUMBER";
		rs = stmt.executeQuery(query);
		
		while(rs.next())
		{	
			//1.전체 2.상의 3.하의 4.외투 5.기타 
			String temp = rs.getString("category");
			if(temp.contains("1"))
				temp = temp.replace("1", "전체");
			if(temp.contains("2"))
				temp = temp.replace("2", "상의");
			if(temp.contains("3"))
				temp = temp.replace("3","하의");
			if(temp.contains("4"))
				temp = temp.replace("4","외투");
			if(temp.contains("5"))
				temp = temp.replace("5","기타");
			
				
			StringTokenizer st = new StringTokenizer(temp,"$");
			String categorys = "";
			int i=0;
			while(st.hasMoreTokens())
			{
				categorys += st.nextToken()+" ";
			}
			
		%>
		
			<!-- 현재 Card 형식의 포맷이 이 곳에 들어가야 합니다.-->
			<div class="col s12 m6 l3">
     <div class="card medium hoverable">
  <div class="card-image waves-effect waves-block waves-light">
    <img class="activator" src="<%=imgPath %>/<%=rs.getString("IMAGENAME")%>">
  </div>
  <div class="card-content">
    <span class="card-title grey-text text-darken-4"><%=categorys %></span>
    <p><a href="#"><%=rs.getString("id") %></a></p>
  </div>
  <div class="card-reveal">
    <span class="card-title grey-text text-darken-4"><%=categorys %><i class="material-icons right">close</i></span>
    <div class="col s12">
<div class="cardContens">
      <img class="col s4" src ="<%=imgPath %>/<%=rs.getString("IMAGENAME")%>" />
      <span>2016.07.03</span>
    <p>< <%=rs.getString("contents") %> ></p>
</div>
    <div class="category">
    
      <div class="chip"><img src="img/contents/2.jpg" alt="Contact Person">상의</div>
      <div class="chip"><img src="img/contents/3.jpg" alt="Contact Person">하의</div>
      <div class="chip"><img src="img/contents/4.jpg" alt="Contact Person">악세사리</div>
      
    </div>
    <div class="cardButton">
    <a href="Board/Delete?pnum=디비에서 얻어온 PNUMBER값을 넣어주면 되겠습니다." class="waves-effect waves-light btn red accent-3" name="deleteboard"><i class="material-icons left">delete</i>삭제</a>
    <a href="#itProduct" class="waves-effect waves-light btn red accent-3 modal-trigger" data-target="login"><i class="material-icons left">comment</i>상품 추천하기</a>
    </div>

    <ul class="collection">
    <li class="collection-item avatar">
      <img src="img/contents/8.jpg" alt="" class="circle">
      <span class="title">정준모샵</span>
      <p>상품명 : 삼선슬리퍼</p>
      <p>가격 : 240,000원</p>
      <p>구매: <a href="http://naver.com">구매하기 </a></p>
      <!-- <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a> -->
    </li>
    <li class="collection-item avatar">
      <img src="img/contents/8.jpg" alt="" class="circle">
      <span class="title">정준모샵</span>
      <p>상품명 : 삼선슬리퍼</p>
      <p>가격 : 240,000원</p>
      <p>구매: <a href="http://naver.com">구매하기 </a></p>
      <!-- <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a> -->
    </li><li class="collection-item avatar">
      <img src="img/contents/8.jpg" alt="" class="circle">
      <span class="title">정준모샵</span>
      <p>상품명 : 삼선슬리퍼</p>
      <p>가격 : 240,000원</p>
      <p>구매: <a href="http://naver.com">구매하기 </a></p>
      <!-- <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a> -->
    </li>
  </ul>
  </div>
  </div>
</div>
</div>
			
			
	<%	}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
%>

  <!-- Card1 Start -->

<!-- Card1 End -->





  </div>
</div>


<!-- SignUp Modal Structure Start -->
<form action="User/Add" method="post">
  <div id="signup" class="modal">
    <div class="modal-content">
      <h4>회원가입</h4>
      <div class="input-field col s12">
          <input name='email' id="email" type="text" class="validate">
          <label for="email">Email</label>
        </div>
        <div class="input-field col s12">
          <input name='password' id="password1" type="password" class="validate">
          <label for="password">Password</label>
        </div>
        <div class="input-field col s12">
          <input name='password2'id="password2" type="password" class="validate">
          <label for="password">Password</label>
        </div>
    </div>
    <div class="modal-footer">
      <input type="submit" class=" modal-action modal-close waves-effect waves-green btn-flat" value="확인">
    </div>
  </div>
</form>
  <!-- signUp Modal Structure End -->



  <!-- login Modal Structure Start -->
<form action="User/login" method="post">
    <div id="login" name="login" class="modal">
      <div class="modal-content">
        <h4>로그인</h4>
        <div class="input-field col s12">
            <input name='email' id="email" type="text" class="validate">
            <label for="email">Email</label>
          </div>
          <div class="input-field col s12">
            <input name='password'id='password' type="password" class="validate">
            <label for="password">Password</label>
          </div>
      </div>
      <div class="modal-footer">
        <input type="submit"  class=" modal-action modal-close waves-effect waves-green btn-flat" value="확인">
      </div>
    </div>
</form>  

    <!-- 코디신청 Modal Structure Start -->
    <form action="#" method="post" encType="multipart/form-data">
      <div id="myRequest" class="modal">
        <div class="modal-content">
          <h4>코디 신청</h4>
<!-- form 시작   -->
          
              <!-- 카테고리 선택 시작-->
              <input type="checkbox" name="checkbox"id="total" checked="checked" value="1"/>
              <label for="total">전체</label>

              <input type="checkbox" name="checkbox"id="topWear" value="2"/>
              <label for="topWear">상의</label>

              <input type="checkbox" name="checkbox"id="bottomWear"  value="3"/>
              <label for="bottomWear">하의</label>

              <input type="checkbox" name="checkbox"id="outterWear" value="4"/>
              <label for="outterWear">외투</label>

              <input type="checkbox" name="checkbox"id="etcWear" value="5"/>
              <label for="etcsWear">기타</label>
              <!-- 카테고리 선택 끝-->

            <!-- 내용 입력 시작 -->
            <div class="input-field col s12">
              <textarea name='textarea1' id="textarea1" class="materialize-textarea" length="1000"></textarea>
              <label for="textarea1">내용을 입력해주세요.(최대 1000자)</label>
            </div>
            <!-- 내용 입력 끝 -->

            <!-- 이미지 첨부 시작 -->

            <div class="input-field col s6">
                  <div class="file-field input-field">
                    <div class="btn">
                      <span>이미지 첨부</span>
                      <input type="file" onchange="upImg1(this)">
                    </div>
                    <div class="file-path-wrapper">
                      <input class="file-path validate" type="text" >
                    </div>
                  </div>
                
          </div>
          
          <!-- 이미지 첨부 끝 -->

        <!-- form 끝 -->

        </div>
        <div class="modal-footer">
          <input type="submit" class=" modal-action modal-close waves-effect waves-green btn-flat" value="확인">
        </div>
      </div>
    </form>
      <!-- 코디 신청 Modal Structer End -->

      <!-- 상품추천  Modal Structure Start -->
        <div id="itProduct" class="modal">
          <div class="modal-content">
            <h4>상품추천</h4>
            <form action="#">
            <div class="input-field col s12">

              <!-- 카테고리 선택 시작-->
              <input type="checkbox" id="itTopWear" />
              <label for="itTopWear">상의</label>

              <input type="checkbox" id="itBottomWear" />
              <label for="itBottomWear">하의</label>

              <input type="checkbox" id="itOutterWear" />
              <label for="itOutterWear">외투</label>

              <input type="checkbox" id="itEtcWear" />
              <label for="itEtcWear">기타</label>
              <!-- 카테고리 선택 끝-->

              </div>
              <!-- 이미지 첨부 시작 -->
              <div class="input-field col s6">
                <div class="file-field input-field">
                <div class="btn light-blue">
            <span>상품 이미지 첨부하기</span>
            <input type="file" onchange="upImg1(this)" >
            </div>
            <div class="file-path-wrapper">
            <input class="file-path validate" type="text" placeholder="이미지를 첨부해주세요. (여러개 가능)">
            </div>
            </div>
            </div>
            <!-- 이미지 첨부 끝 -->

              <!-- 내용 입력 시작 -->
              <div class="input-field col s12">
                <textarea id="itTextfield" class="materialize-textarea" length="1000"></textarea>
                <label for="textarea1">내용을 입력해주세요.(최대 1000자)</label>
              </div>
              <!-- 내용 입력 끝 -->


              <!-- 상품 URL 입력 시작 -->
              <div class="input-field col s12">
                <input placeholder="상품 URL을 입력해주세요." id="first_name" type="text" class="validate">
                <label for="itUrl">상품 URL</label>
              </div>
              <!-- 상품 URL 입력 끝 -->
            </form>
          </div>
          <div class="modal-footer">
            <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">확인</a>
          </div>
        </div>
        <!-- 상품추천 Modal Structure End -->

      <!-- Menu Start -->
      <div class="fixed-action-btn horizontal" style="bottom: 45px; right: 24px;">
          <a href="<%=checkSession %>" class="btn-floating btn-large blue modal-trigger" data-target="myRequest"><i class="large material-icons">mode_edit</i>
          </a>
          <!-- <ul>
            <li><a class="btn-floating red"><i class="material-icons">insert_chart</i></a></li>
            <li><a class="btn-floating yellow darken-1"><i class="material-icons">format_quote</i></a></li>
            <li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
            <li><a href="#myRequest" class="btn-floating blue modal-trigger" data-target="myRequest">
              <i class="material-icons" >attach_file</i></a></li>
          </ul> -->
        </div>
        <!-- Menu End -->


</div>
</div>

<!-- PageNavigator Start-->

<!-- PageNavigator End-->
<div class="center-align ">
<ul class="pagination">
    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
    <li class="active light-blue darken-1"><a href="#!">1</a></li>
    <li class="waves-effect"><a href="#!">2</a></li>
    <li class="waves-effect"><a href="#!">3</a></li>
    <li class="waves-effect"><a href="#!">4</a></li>
    <li class="waves-effect"><a href="#!">5</a></li>
    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
  </ul>
</div>

<!-- Footer Start -->
<footer class="page-footer light-blue darken-1">
         <div class="container">
           <div class="row">
             <div class="col l6 s12">
               <h5 class="white-text">Egg Cho</h5>
               <p class="grey-text text-lighten-4">왜 계란말이에 초고추장을 뿌려가지고 온통 EggCho가 되었나...</p>
             </div>
             <div class="col l4 offset-l2 s12">
               <h5 class="white-text">About</h5>
               <ul>
                 <li><a class="grey-text text-lighten-3" href="#!">What</a></li>
                 <li><a class="grey-text text-lighten-3" href="#!">Who</a></li>
                 <li><a class="grey-text text-lighten-3" href="#!">When</a></li>
                 <li><a class="grey-text text-lighten-3" href="#!">Why</a></li>
               </ul>
             </div>
           </div>
         </div>
         <div class="footer-copyright">
           <div class="container">
           © 2016 Copyright EggCho
           <a class="grey-text text-lighten-4 right" href="#!">Contact</a>
           </div>
         </div>
       </footer>
<!-- Footer End -->
  </body>

  <!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/imagesloaded.pkgd.js"></script>
<script type="text/javascript" src="js/eggcho.js"></script>
</html>

