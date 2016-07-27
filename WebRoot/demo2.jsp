<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
    <form action="${pageContext.request.contextPath}/servlet/UploadServlet3" method="post" enctype="multipart/form-data">
    	name:<input type="text" name="name"/><br/>
    	<div id="d1">
	    	<div>
	    	photo:<input type="file" name="photo"/><input type="button" value="继续上传" onclick="addFile()"/>
	    	</div>
    	</div>
    	<input type="submit" value="上传"/>
    </form>
    <script type="text/javascript">
    	function addFile(){
    		var d1 = document.getElementById("d1");
    		var oldInnerHtml = d1.innerHTML;
    		d1.innerHTML=oldInnerHtml+"<div>photo:<input type='file' name='photo'/><input type='button' value='删除' onclick='deleteOne(this)'/></div>";
    	}
    	function deleteOne(delBtn){
    		delBtn.parentNode.parentNode.removeChild(delBtn.parentNode);
    	}
    </script>
  </body>
</html>
