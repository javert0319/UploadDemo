<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
    全部资源如下:<br />
    
   <c:forEach items="${map}" var="me">
    	<c:url value="/servlet/DownLoadServlet" var="url">
    		<c:param name="filename" value="${me.key}"></c:param>
    	</c:url>
    	${me.value}&nbsp;&nbsp;<a href="${url}">下载</a><br/>
    </c:forEach>
    
    
  </body>
</html>
