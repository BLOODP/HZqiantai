<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <a href="./servlet/TestServlet?user_name=何光勤&pass=123">kick it</a>
    <form action="./servlet/GetAllNewsIdAndTitleServlet" method="post">
    	姓名：<input name="user_name" type="text">
    	密码:<input name="pass" type="text">
    	<input type="submit" value="提交">
    </form>
    
    <form action="./servlet/GetNewsByIdServlet" method="post">
    	姓名：<input name="news_id" type="text">
    	<input type="submit" value="提交">
    </form>
    
    <form action="./servlet/GetImageByTypeServlet" method="post">
    	图片类型：<input name="image_type" type="text">
    	<input type="submit" value="提交">
    </form>
    
    <form action="./servlet/UploadImageServlet" method="post" enctype="MULTIPART/FORM-DATA">
    	名称:<input name="image_name" type="text"><br>
    	类别：<input name="image_type" type="text"><br>
    	描述:<input name="image_description" type="text"><br>
    	图片：<input type="file" name="image"><br>
    	<input type="submit" value="上传图片">
    </form>
  </body>
</html>
