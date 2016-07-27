package cn.tf.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//显示/WEB-INF/file目录下的所有文件
public class ShowAllFilesServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//key:GUID文件名  value：old文件名
		Map<String, String> map = new HashMap<String, String>();
		//获取/WEB-INF/files的真实路径
		String rootDirectoryRealPath = getServletContext().getRealPath("/WEB-INF/files");
		//递归遍历找出所有的文件
		System.out.println(rootDirectoryRealPath);
		File rootDirectory = new File(rootDirectoryRealPath);
		treeWalk(rootDirectory,map);
		//存到请求范围中，转发给jsp显示
		request.setAttribute("map", map);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}
	//递归遍历找出所有的文件,把文件名高出来
	public void treeWalk(File file, Map<String, String> map) {
		if(file.isFile()){
			String guidFileName = file.getName();
			String oldFileName = guidFileName.substring(guidFileName.indexOf("_")+1);
			map.put(guidFileName, oldFileName);
		}else{
			//目录
			File[] childFiles = file.listFiles();
			for(File f:childFiles){
				treeWalk(f, map);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
