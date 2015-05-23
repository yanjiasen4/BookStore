package com.Statistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Statistics
 */
@WebServlet("/Statistics")
public class Statistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistics() {
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
	  		
	  		rs = smt.executeQuery("select type,count(type) from book_list group by type");
	  		String stat = "";
	  		if(rs != null)
	  		{
	  			PrintWriter out=response.getWriter();
	  			while(rs.next())
	  			{
	  				stat += "['"+rs.getString("type")+"',"+rs.getString("count(type)")+"]";
	  				if(rs.isLast()==false)
	  				{
	  					stat += ",";
	  				}
	  			}
	  			System.out.println(stat);
	  			out.println(stat.toString());
	  			out.flush();
	  			out.close();
	  			
	  			//request.setAttribute("data", rs);
	  			//response.sendRedirect("Statistics.jsp");
	  		}
	  		else
	  		{
	  			response.sendRedirect("Index.jsp");
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
		//response.sendRedirect("Statistics.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
