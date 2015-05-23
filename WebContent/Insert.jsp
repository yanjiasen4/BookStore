<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="css/pure/menu.css">
<link rel="stylesheet" href="css/pure/p.css">
<link rel="stylesheet" href="css/pure/bb.css">
<link rel="stylesheet" href="css/pure/bookshop.css">
<meta charset="UTF-8">
<title>Insert Book</title>
</head>
<body>

<div id="main">

<div class="header">
	<h1>E-book</h1>
	
	<h2 class="marketing-header">增加图书成功</h2>
	<div class="pure-1">
	<h4>您向数据库中增加了<font color="#222"><%=request.getAttribute("bookname")%>图书</font></h4>
	</div>
	<div class="pure-1">
	<a href="Index.jsp">去首页</a>
	</div>
</div>

</div>

</body>
</html>