<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="css/pure/menu.css">
<link rel="stylesheet" href="css/pure/p.css">
<link rel="stylesheet" href="css/pure/bb.css">
<link rel="stylesheet" href="css/pure/bookshop.css">

<title>Bookshop Delete Manage</title>
</head>
<body>
<div id="layout">
	<a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

	<div id="menu">
	<div class="pure-menu pure-menu-open">
            <a class="pure-menu-heading" href="#">e-book</a>
            <ul>
			           <li class="pure-menu-item"><a href="Index.jsp">首页</a></li>
			           <% if(request.getSession().getAttribute("username") == null)
            				{
            			%>
			           <li class="pure-menu-item"><a href="Login.jsp">登录</a></li>
			           <%  } else 
			               {
			            %>
			           <li class="pure-menu-item"><a href="User.jsp">${sessionScope.username}</a></li>
			           <%  } %>
			           <li class="pure-menu-item"><a href="Register.jsp">注册</a></li>
			           <li class="menu-item-divided pure-menu-selected"><a href="Manage.jsp">管理</a></li>
			           <li class="pure-menu-item"><a href="ShowCart">购物车</a></li>
			           <li class="pure-menu-item"><a href="Order.jsp">订单</a></li>
			           <li class="menu-item-divided"><a href="Statistics.jsp">统计</a></li>
			           <li class="pure-menu-item"><a href="Intro.html">说明</a></li>
			 </ul>
        </div>
    </div>
    
    <div id="mana" class="content">
	<div class="layout-item-sreenshot content pure-u-1 u-sm-1-1">
		<p>
		<a class="pure-button" href="ManageI.jsp" style="font-size: 100%;">添加图书</a>
		<a class="pure-button" href="ManageD.jsp" style="font-size: 100%;">删除图书</a>
		<a class="pure-button" href="ManageC.jsp" style="font-size: 100%;">修改图书</a>
		</p>
	</div>
	</div>
    
    <div id="mana" class="pure-u-2">
    <br/>
    <br/>
    <br/>
    <br/>
 	<sql:query var="rs" dataSource="jdbc/sample">
	select BookName, Author, Price, Type from Book_list
	</sql:query>
	
	<div>
	<form action="DeleteBook" method="get">
	<table class="pure-table">
    <thead>
        <tr>
            <th>Book</th>
            <th>Author</th>
            <th>Price</th>
            <th>Type</th>
            <th></th>
        </tr>
    </thead>

    <tbody>
   
        <c:forEach var="row" items="${rs.rows}">
        	<tr>
        		<td>${row.BookName}</td>
        		<td>${row.Author}</td>
        		<td>${row.Price}</td>
        		<td>${row.Type}</td>
    			<td>
    				<label for="choice" class="pure-checkbox">
            			<input id="choice" type="checkbox" name="choice" value="${row.BookName}"> 
       				</label>

        </c:forEach>
    
    </tbody>
	</table>
	
	<button type="submit" class="pure-button pure-button-primary" style="margin-top:20px; margin-left:300px; margin-right:300px; margin-bottom:400px;">Submit</button>
	
	</form>
	</div>
	  
	</div>
	</div>

</body>
</html>