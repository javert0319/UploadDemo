<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>My JSP 'demo1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
    
    系统默认方式
    <form action="${pageContext.request.contextPath}/servlet/UploadServlet"  method="post"  >
    	姓名: <input  type="text"  name="name" /><br />
    	照片: <input  type="file"  name="photo" /><br />
    
		<input type="submit"  value="提交" />
     </form>
    
    multipart/form-data
        <form action="${pageContext.request.contextPath}/servlet/UploadServlet"  method="post"  enctype="multipart/form-data" >
    	姓名: <input  type="text"  name="name" /><br />
    	照片: <input  type="file"  name="photo" /><br />
    
			<input type="submit"  value="提交" />
    </form>
    
    UploadServlet2方式上传
     <form action="${pageContext.request.contextPath}/servlet/UploadServlet2"  method="post"  enctype="multipart/form-data" >
    	姓名: <input  type="text"  name="name" /><br />
    	照片: <input  type="file"  name="photo" /><br />
    
			<input type="submit"  value="提交" />
    </form>
    
    限制文件大小
      <form action="${pageContext.request.contextPath}/servlet/UploadServlet3"  method="post"  enctype="multipart/form-data" >
    	姓名: <input  type="text"  name="name" /><br />
    	照片1: <input  type="file"  name="photo" /><br />
    	照片2: <input  type="file"  name="photo" /><br />
			<input type="submit"  value="提交" />
    </form>
    
  </body>
</html>
