package cn.tf.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String guidFilename = request.getParameter("filename");//get方式提交的
		guidFilename = new String(guidFilename.getBytes("ISO-8859-1"),"UTF-8");
		//计算存放路径
		File storeDirectory = new File(getServletContext().getRealPath("/WEB-INF/files"));
		String childDirectory = makeChildDirecotry(storeDirectory, guidFilename);//  13/1
		//构建输入流
		InputStream in = new FileInputStream(new File(storeDirectory,childDirectory+File.separator+guidFilename));
		
		//用响应对象的输出流输出：下载的方式
		String oldFileName = guidFilename.substring(guidFilename.indexOf("_")+1);
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(oldFileName,"UTF-8"));//不适用火狐
		response.setContentType("application/octet-stream");
		OutputStream out = response.getOutputStream();
		
		int len = -1;
		byte buf[] = new byte[1024];
		while((len=in.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		in.close();
		
		
	}
	private String makeChildDirecotry(File storeDirectory, String guidFilename) {
		
		int hashCode = guidFilename.hashCode();
		int dir1 = hashCode&0xf;
		int dir2 = (hashCode&0xf0)>>4;
		
		String s = dir1+File.separator+dir2;
		
		File f = new File(storeDirectory,s);
		if(!f.exists()){
			f.mkdirs();
		}
		return s;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
