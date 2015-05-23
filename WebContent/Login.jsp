<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mysql.jdbc.Driver" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="css/pure/menu.css">
<link rel="stylesheet" href="css/pure/p.css">
<link rel="stylesheet" href="css/pure/bb.css">
<link rel="stylesheet" href="css/pure/bookshop.css">

<title>BookStore</title>
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
			           <li class="menu-item-divided pure-menu-selected"><a href="Login.jsp">登录</a></li>
			           <li class="pure-menu-item"><a href="Register.jsp">注册</a></li>
			           <li class="pure-menu-item"><a href="Manage.jsp">管理</a></li>
			           <li class="pure-menu-item"><a href="ShowCart">购物车</a></li>
			           <li class="pure-menu-item"><a href="Order.jsp">订单</a></li>
			           <li class="menu-item-divided"><a href="Statistics.jsp">统计</a></li>
			           <li class="pure-menu-item"><a href="Intro.html">说明</a></li>
			 </ul>
        </div>
    </div>
</div>

<div id="main">
<div class="header">
	<h1>Login</h1>
    <div class="content">
    <form class="pure-form pure-form-aligned" action="Login" method="POST">
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Username</label>
            <input id="name" type="text" name="Username">
        </div>

        <div class="pure-control-group">
            <label for="password">Password</label>
            <input id="password" type="password" name="Password">
        </div>
        
        <div class="pure-controls">
            <label for="cb" class="pure-checkbox">
                <input id="cb" type="checkbox"> Remember me!
            </label>

            <button type="submit" class="pure-button pure-button-primary">Submit</button>
            <%
            if(request.getParameter("errNo") != null){
            String errID = request.getParameter("errNo");
            if (errID.equals("1")) 
            { System.out.println(request.getParameter("errNo"));
            %>
            <p><font color="red">wrong password!</font></p>
            <%
            } else if(errID.equals("2"))
            {
            %>
            <p><font color="red">username not exist!</font></p>
            <%
            }}
            %>
        </div>
    </fieldset>
	</form>
	</div>
</div>
</div>
</body>
</html>