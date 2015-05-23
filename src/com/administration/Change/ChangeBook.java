package com.administration.Change;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class ChangeBook
 */
@WebServlet("/ChangeBook")
public class ChangeBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn= null;
  		Statement smt = null;
  		ResultSet rs = null;
  		
  		try{
  			String[] Names=request.getParameterValues("choice");
  	  		
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
	  		
	  		 for(int i = 0; i<Names.length; i++)
	 	    {
	 	    	String strSql="update Book_list set BookName='"+Names[i]+"'";
	 	    	System.out.println(strSql);
	 	   // PreparedStatement smt=dbConn.prepareStatement(strSql);
	 	    
	 	  	 	smt.executeUpdate(strSql);
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
  		 response.sendRedirect("Change.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
