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
<title>Register</title>
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
			           <li><a href="Index.jsp">首页</a></li>
			           <% if(request.getSession().getAttribute("username") == null)
            				{
            			%>
			           <li><a href="Login.jsp">登录</a></li>
			           <%  } else 
			               {
			            %>
			           <li><a href="User.jsp">${sessionScope.username}</a></li>
			           <%  } %>
			           <li class="menu-item-divided pure-menu-selected"><a href="Register.jsp">注册</a></li>
			           <li class="pure-menu-item"><a href="Manage.jsp">管理</a></li>
			           <li class="pure-menu-item"><a href="ShowCart">购物车</a></li>
			           <li class="pure-menu-item"><a href="Order.jsp">订单</a></li>
			           <li class="pure-menu-item menu-item-divided"><a href="Statistics.jsp">统计</a></li>
			           <li class="pure-menu-item"><a href="Intro.html">说明</a></li>
			 </ul>
        </div>
    </div>
</div>

<div id="main">
<div class="header">
	<h1>Register</h1>
    <div class="content">
    <form class="pure-form pure-form-aligned" action="Register" method="POST">
    <fieldset>
        <div class="pure-control-group">
            <label for="name">Username</label>
            <input id="Name" type="text" name="Username">
        </div>

        <div class="pure-control-group">
            <label for="password">Password</label>
            <input id="Password" type="password" name="Password">
        </div>
        
        <div class="pure-control-group">
            <label for="email">Email</label>
            <input id="Email" type="text" name="Email">
        </div>
        
        <div class="pure-controls">
            <label for="cb" class="pure-checkbox">
                <input id="cb" type="checkbox"> I've read the terms and conditions
            </label>

            <button type="submit" class="pure-button pure-button-primary">Submit</button>
            <%if(request.getParameter("errNo") != null)
            	{
            %>
            <p><font color="red">username has been used</font></p>
            <%  } 
            %>
        </div>
    </fieldset>
	</form>
	</div>
</div>
</div>

</body>
</html>