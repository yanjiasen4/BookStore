package com.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class Register
 */
@WebServlet(asyncSupported = true, description = "RegisterServlet", urlPatterns = { "/Register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		ResultSet cnt = null;
  		
  		try{
	  		//接收用户名和密码
	  		String u = request.getParameter("Username");
	  		String p = request.getParameter("Password");
	  		String e = request.getParameter("Email");
	  		
	  		if(u==null||p==null||e==null)
	  			response.sendRedirect("Register?errNo=1.jsp");
	  		
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
	  		
	  		cnt = smt.executeQuery("select ID,Name from users");
	  		
	  		while(cnt.next())
	  		{
	  			System.out.println(cnt.getString("Name"));
	  			if(u.equals(cnt.getString("Name")))
	  			{
	  				if(null != rs){
	  		  			rs.close();
	  		  		}
	  		  		if(null != smt){
	  		  			smt.close();
	  		  		}
	  	  			if(null != conn){
	  	  				conn.close();
	  	  			}		
	  	  			if(null != cnt){
	  	  		 		conn.close();
	  	  			}
	  				response.sendRedirect("Register.jsp?errNo=1");
	  				return;
	  			}
	  		}
	  		cnt.last();
	  		int i =  cnt.getInt("ID") + 1;
	  		
	  		String sql = "insert into users values ('"+ i + "','" + u +"','"+ p +"','"+ e +"')";
	  		ps = conn.prepareStatement(sql);
	  		ps.executeUpdate();
	  		
	  
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
  		response.sendRedirect("Login.jsp");
	}

}
