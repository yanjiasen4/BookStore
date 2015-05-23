<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="css/pure/menu.css">
<link rel="stylesheet" href="css/pure/p.css">
<link rel="stylesheet" href="css/pure/bb.css">
<link rel="stylesheet" href="css/pure/bookshop.css">
<title>Insert title here</title>
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
</div>
	

</body>
</html>