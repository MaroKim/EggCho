package User;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.net.ftp.*;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/User/Apply")
public class UploadServlet extends HttpServlet{
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException {
		process(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException , IOException {
		
		process(request,response);
	}
	
	protected void process(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
		
		
		/* server의 위치를 가져오는 코드인데 저장될 위치를 따로 지정하고 있음 (tmpPath) 
		 * 따라서  root 와 pathName 은 필요가 없
		 *  */
		//String root = request.getSession().getServletContext().getRealPath("/");
		//String pathName =root;
		HttpSession session = request.getSession();
		String pfile = (String)session.getAttribute("email")+"up"+".jpg";
		
	
		/*
		 * 위에서 주석처리한 위치에 파일을 생성하는 코드이기 때문에 이것도 필요가 없
		 * */
		//File f = new File(pathName);
		//System.out.println("pathName : "+pathName);
		
		// 해당 경로에 폴더가 없으면 폴더를 생성해 주는 코드. 
		// 폴더가 없어지는 상황에 대비하여 필요... 할지는 모르겠으나 현재 사용하지 않
		//if(!f.exists())
		//	f.mkdirs();
		
		// 사진을 저장할 위치 - 서버 아래에 두어야 localhost가 아닌 외부에서 사진을 볼수 있다.
		String tmpPath ="/Users/maro/EggCho/EggCho/WebContent/Image";
		String encType = "UTF-8";
		int maxFilesize = 1024*1024*5;
		
		MultipartRequest multi = new MultipartRequest(request,tmpPath,maxFilesize,encType,new DefaultFileRenamePolicy());
		
		File file1 = multi.getFile("file");
		System.out.println("multi category : " +multi.getParameter("checkbox"));
		String[] categorys = multi.getParameterValues("checkbox");
		String cg = "";
		for(int i=0;i<categorys.length;i++)
		{
			System.out.println("category["+i+"] : "+categorys[i]);
			cg+=(i+"&");
		}
		
		
		File newFile = new File(tmpPath+"/"+pfile);
		System.out.println("newFile : "+newFile.toString());
		file1.renameTo(newFile);
		
		
		System.out.println("file: "+file1);
		
		String filePath= multi.getFile("file").toString();
		System.out.println("filePath : "+filePath);
		ImageIOMain itm = new ImageIOMain(tmpPath,pfile);
		itm.setSize(100,100);
		//String uploadPath=filePath;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL="jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf8";
			//String DB_URL="jdbc:mysql://localhost:3306/Project1?useUnicode=true&characterEncoding=utf8";
			String DB_USER ="root";
			//String DB_PASSWORD="1234";
			String DB_PASSWORD="dprmch123";
			
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			stmt =conn.prepareStatement("insert into uploadtest value(1,?,?)");
			//stmt =conn.prepareStatement("insert into POST(ID,category,CONTENTS,WDAY) values(?,?,?,now())");
				
			/* 서버 데이터베이스 : Project1
			 * 실제로 업로드 할떄 사용하는 테이블 : POST
			 * 필요한 컬럼 
			 * PNUMBER(사진넘버) auto_increment처리됨
			 * ID : 사용자 아이디 삽입 session 값을 이용해서 가져오면 됨 
			 * category : category 여러개 삽입  multi.getparameterValues("checkbox");
			 * CONTENTS : 의뢰자가 남길말 multi.getParameter("textarea1");
			 * WDAY : 등록일자로 now() 사용하면 됨 
			 * 
			 * 사진 저장소는 다른 테이블에 있음 : IMAGE
			 * 사용되는 컬럼 
			 * IMAGECODE : auto_increment  처리됨
			 * PNUMBER : POST table에서 외래키로가져옴 
			 * IMAGENAME ~4 : image 4장까지 저장 현재 사용자 id+up 으로 처리되어 있음. 수정필
			 */
			
			stmt.setString(1, pfile);
			stmt.setString(2,cg);
			
			stmt.executeUpdate();
			
			/*
			stmt2 = conn.prepareStatement("insert into IMAGE(PNUMBER,IMAGENAME) values(?,?)");
			//post pnumber 가져오기 : POST table의 ID 와 세션의ID  
			
			stmt2.setInt(1,2);
			stmt2.setString(2,pfile);
			*/
			
			
			
			
			
			
			
			response.sendRedirect("/EggCho/index.jsp");
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	/* 
	 * 이미지 저장 클래스 : 파일을 업로드 하면서 저장하는 이미지를 다시 불러서 리사이즈 하여 파일 크기 및 사이즈를 줄인다.
	 * 현재 이미지를 100*100으로 설정하게 되어 있다. 이부분은 나중에 비율로 줄이는 방식으로 변경이 필요하다.
	 * 
	 * */
	public class ImageIOMain extends Frame{
		BufferedImage bi = null;
			
		public ImageIOMain(String savePath,String fileName)
		{
			try{
				bi = bufferedImage(savePath,fileName);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//isLoading class 생성 , progress bar
		private BufferedImage bufferedImage(String savePath,String fileName) throws Exception{
			File file = new File(savePath+"/"+fileName);
			BufferedImage bi = ImageIO.read(file);
			
			Image resizeImage = bi.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			BufferedImage newImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
			
			Graphics g = newImage.getGraphics();
			g.drawImage(resizeImage, 0, 0, this);
			g.dispose();
			
			
			File file1 = new File(savePath+"/min-"+fileName);
			ImageIO.write(newImage, "jpg", file1);
			
			
			return bi;
			
		}
		
		public void paint(Graphics g)
		{
			if(this.bi!=null)
				g.drawImage(this.bi,0,20,this);
		}
		
		
	}

}
