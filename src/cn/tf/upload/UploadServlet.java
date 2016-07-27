package cn.tf.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String name=request.getParameter("name");
			System.out.println(name);
		
			//输入流获取请求正文的内容
			InputStream in = request.getInputStream();
			int len = -1;
			byte b[] = new byte[1024];
			while((len=in.read(b))!=-1){
				System.out.println(new String(b,0,len));
			}
			in.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
