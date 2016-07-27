package cn.tf.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.SimpleAttributeSet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import cn.tf.util.GUIDUtil;

public class UploadServlet2 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//判断用户的请求内容是不是multipart/form-data
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException("error!");
		}
		
		//创建DiskFileItemFactory对象
		DiskFileItemFactory factory=new DiskFileItemFactory();

		//创建核心解析类ServlertFileUpload
		ServletFileUpload  sfu=new ServletFileUpload(factory);
		
		//解析请求对象
		List<FileItem> items=new ArrayList<FileItem>(0);
		
		try {
			items=sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(FileItem item:items){
			if(item.isFormField()){
				processFormField(item);
			}else{
				processUploadField(item);
			}
		}
		response.getWriter().write("sucess!");
		
	}


	private void processUploadField(FileItem item) {
		
		try {
			InputStream in=item.getInputStream();
			String filename=item.getName();
			
			//在服务器上找一个存放文件的地方
			String storeDirectoryRealPath=getServletContext().getRealPath("/WEB-INF/files");
			File storeDirectory=new File(storeDirectoryRealPath);
			
			if(!storeDirectory.exists()){
				storeDirectory.mkdirs();
			}
			
			//截取上传的文件名
			//filename=filename.substring(filename.lastIndexOf(File.separator)+1);
			
			if(filename!=null){
				filename=FilenameUtils.getName(filename);
			}
			
			String guidFilename=GUIDUtil.generateGUID()+"_"+filename;
			
			
			//按日期来区分存储目录
		//	String childDirectory=makeChileDirectory(storeDirectory);
			
			String childDirectory=makeChildDirectory(storeDirectory,guidFilename);
			
			//构建输出流
			OutputStream  out=new FileOutputStream(new File(storeDirectory,childDirectory+File.separator+guidFilename));
			
			int len = -1;
			byte buf[] = new byte[1024];
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private String makeChildDirectory(File storeDirectory, String guidFilename) {
		int hashCode = guidFilename.hashCode();
		int dir1 = hashCode&0xf;//  0~15
		int dir2 = (hashCode&0xf0)>>4;//0~15
		
		String s = dir1+File.separator+dir2;
		
		File f = new File(storeDirectory,s);
		if(!f.exists()){
			f.mkdirs();
		}
		return s;
		
	}


	private String makeChileDirectory(File storeDirectory) {
		Date now=new Date();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String sdate=df.format(now);
		File f=new File(storeDirectory,sdate);
		if(!f.exists()){
			f.mkdirs();
		}
		return sdate;
	}


	private void processFormField(FileItem item) {
		String fieldName=item.getFieldName();
		String fieldValue=item.getString();
		System.out.println(fieldValue+"="+fieldName);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}
}
