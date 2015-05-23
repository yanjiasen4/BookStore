<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%ResultSet rs = (ResultSet)request.getAttribute("data");%>
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="css/pure/menu.css">
<link rel="stylesheet" href="css/pure/p.css">
<link rel="stylesheet" href="css/pure/bb.css">
<link rel="stylesheet" href="css/pure/bookshop.css">
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/highcharts.js"></script>

<script>
$(document).ready(function () {
	$.ajax({
		url: "Statistics",
		type: "GET",
		dataType: "text",
		success: function(data)
		{
			console.debug(data);
			var a = eval('[' + data + ']');
			console.debug(data);
	
	
			var chart = new Highcharts.Chart({
        		chart: {
        			renderTo: 'container',
            		plotBackgroundColor: null,
            		plotBorderWidth: null,
           			plotShadow: false
        			},
       			 title: {
            		text: '网站图书分类占比图'
        			},
        		tooltip: {
           			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        			},
        		plotOptions: {
            		pie: {
                		allowPointSelect: true,
                		cursor: 'pointer',
                		dataLabels: {
                    		enabled: true,
                    		format: '<b>{point.name}</b>: {point.percentage:.1f} %',
               				style: {
                        		color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    			}
                			}
            			}
        		},
        		series: [{
            		type: 'pie',
            		name: 'Book Type',
            		data: a
        		}]
        		
    		});
		}
	});
});
</script>

<script>
$(document).ready(function () {
	$.ajax({
		url: "Statistics_User",
		type: "GET",
		dataType: "text",
		success: function(data)
		{
			console.debug(data);
			var a = eval('[' + data + ']');
			console.debug(data);
	
			var chart = new Highcharts.Chart({
			    chart: {
			      	renderTo: 'container_u',
			        type: 'column'
		        },
		        title: {
		            text: 'Users consume ranking'
		        },
		        subtitle: {
			        text: 'Source: E-Book database'
	            },
		        xAxis: {
		            type: 'category',
		            labels: {
			            style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
			            }
			        }
			    },
		        yAxis: {
		            min: 0,
		            title: {
		            	text: 'Cost (￥))'
			            }
			    },
			    legend: {
	                enabled: false
		        },
		        tooltip: {
			        pointFormat: 'consume: <b>{point.y:.1f} ￥</b>'
		        },
		        series: [{
		        	name: 'Consume',
			        data: a,
				    dataLabels: {
		                enabled: true,
			            rotation: -90,
			            color: '#FFFFFF',
			            align: 'right',
			            format: '{point.y:.1f}', // one decimal
			            y: 10, // 10 pixels down from the top
				        style: {
			                fontSize: '13px',
			                fontFamily: 'Verdana, sans-serif'
			            }
			        }
			    }]
		    });	
		}
	});
});
</script>

<script>
$(document).ready(function () {
	$.ajax({
		url: "Statistics_Single_User",
		type: "GET",
		dataType: "text",
		success: function(data)
		{
			console.debug(data);
			var a = eval('[' + data + ']');
			console.debug(data);
			
			var chart = new Highcharts.Chart({
        		chart: {
        			renderTo: 'container_su',
            		type: 'line'
        		},
        		title: {
           			text: 'Monthly consume'
        		},
        		subtitle: {
            		text: 'Source: E-Book database'
        		},
        		xAxis: {
            		categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        		},
        		yAxis: {
            		title: {
                		text: 'cost (￥)'
            		}
        		},
        		plotOptions: {
            		line: {
                		dataLabels: {
                    		enabled: true
                		},
                		enableMouseTracking: true
            		}
        		},
        		series: [{
            		name: 'Your cost in books',
            		data: a
        		}]
    		});
		}
	});
});
</script>

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
			            <li class="menu-item-divided"><a href="Index.jsp">首页</a></li>
			           
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
			           <li class="pure-menu-item"><a href="ShowCart">购物车</a></li>
			           <li class="pure-menu-item"><a href="Order.jsp">订单</a></li>
			           <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="Statistics.jsp">统计</a></li>
			           <li class="pure-menu-item"><a href="Intro.html">说明</a></li>
			 </ul>
        </div>
    </div>
</div>

<div id="main">
<div class="header">
	<h1>Statistics</h1>
    <div class="content">
    	<div id="container"></div>
    	<br>
    	<br>
    	<div id="container_u"></div>
    	<br>
    	<br>
    	<%if(session != null && session.getAttribute("userid") != null) {%>
    	<div id="container_su"></div>
    	<%} %>
	</div>
</div>
</div>
</body>
</html>