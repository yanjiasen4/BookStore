package com.login;

import javax.servlet.http.HttpSession;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class Login
 */
/**@WebServlet(description = "logincheck", urlPatterns = { "/Login" })*/
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn= null;
  		Statement smt = null;
  		ResultSet rs = null;
  		
  		try{
	  		//接收用户名和密码
	  		String u = request.getParameter("Username");
	  		String p = request.getParameter("Password");
	  		
	  		//在数据库中验证用户
	  		String className="com.mysql.jdbc.Driver";
	  		String url = "jdbc:mysql://localhost:3306/sample_one";
	  		
	  		String userName = "root";
	  		String userPass ="asdffdsa";
	  		
	  		//加载驱动
	  		Class.forName(className);
	  		//得到连接
	  		conn = DriverManager.getConnection(url,userName,userPass);
	  		//创建Statement
	  		smt = conn.createStatement();
	  		
	  		rs = smt.executeQuery("select Password,ID from users where Name = '"+ u +"'");
	  		
	  		if(rs.next()){
	  			//c存在用户名
	  			//判断密码
	  			if(rs.getString(1).equals(p)){
	  				int id = rs.getInt("ID");
	  				HttpSession session = request.getSession();
	  				session.setAttribute("username",u);
	  				session.setAttribute("userid",id);
	  				response.sendRedirect("Index.jsp");
	  			}else{
	  			//密码不对返回到登陆
	  				response.sendRedirect("Login.jsp?errNo=1");
	  			}
	  			
	  		}else{
	  			response.sendRedirect("Login.jsp?errNo=2");
	  		}
	  		
  		}catch(Exception e){
  			e.printStackTrace();
  		}finally{
	  		if(null != rs){
	  			try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  		}
	  		if(null != smt){
	  			try {
					smt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  		}
  			if(null != conn){
  				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			}		
  		}	
	}

}
