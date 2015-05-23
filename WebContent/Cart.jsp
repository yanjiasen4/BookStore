<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%if(session==null)
{
	response.sendRedirect("toLogin.html");
} else if(session.getAttribute("userid")==null) 
{
	response.sendRedirect("toLogin.html");
}
%>
<html>
<head>

<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="css/pure/menu.css">
<link rel="stylesheet" href="css/pure/p.css">
<link rel="stylesheet" href="css/pure/bb.css">
<link rel="stylesheet" href="css/pure/bookshop.css">

<title>BookStore-Cart</title>
</head>
<body>
<div id="layout" style="padding-left:143px">
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
			           <li class="pure-menu-item"><a href="Manage.jsp">管理</a></li>
			           <li class="menu-item-divided pure-menu-selected"><a href="ShowCart">购物车</a></li>
			           <li class="pure-menu-item"><a href="Order.jsp">订单</a></li>
			           <li class="menu-item-divided"><a href="Statistics.jsp">统计</a></li>
			           <li class="pure-menu-item"><a href="Intro.html">说明</a></li>
			 </ul>
        </div>
    </div>

<div id="main">
	<div class="header">
	<h1>Cart</h1>
	</div>
	
    <div class="content">
    <%if(session == null || session.getAttribute("username") == null){ %>
    <H3>请先登录</H3>
    
    <%} else{
    
    if((int)request.getAttribute("emptycart")==1)
    { %>
	    <p>购物车是空的，去看看有什么自己喜欢的书吧！</p>
      <% 
    }else{
    ResultSet rs = null;
    rs = (ResultSet)request.getAttribute("data");
    int total_price=Integer.parseInt(request.getAttribute("price").toString()); 
    while(rs.next())
    {  %>
    	<div class="box">
    			<!--<img alt="图片加载失败" height="160" width="160" src="${row.Pic}">  -->
    			<h2>书名：<%=rs.getString("B_Name") %></h2>
   				<h4 class="price">单价：<%=rs.getInt("Price")%></h4> 
   				<h4>数量：<%=rs.getInt("Num")%></h4>
        </div>  	
    <%} 
    %>
    <div style="clear:both;"></div>
    <div>
    <h2>合计：<%=total_price%>￥ </h2>
    <a id="buy" class="pure-button pure-button-primary" href="Buy">立即购买</a>
    <a id="clear" class="pure-button pure-button-primary" href="ClearCart">清空购物车</a>
    </div>
    <% }
    } %>
    </div>
    

</div>
</div>

</body>
</html>