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
@WebServlet("/Statistics_User")
public class Statistics_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistics_User() {
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
	  		
	  		rs = smt.executeQuery("select U_ID,sum(Price*Num) from orders group by U_ID order by sum(Price*Num) limit 8");
	  		String stat = "";
	  		if(rs != null)
	  		{
	  			PrintWriter out=response.getWriter();
	  			while(rs.next())
	  			{
	  				String userid = rs.getString("U_ID");
	  				Statement usmt = conn.createStatement();
	  				ResultSet urs = usmt.executeQuery("select Name from users where ID='"+userid+"'");
	  				if(urs.next())
	  				{
	  					stat += "['"+urs.getString("Name")+"',"+rs.getString("sum(Price*Num)")+"]";
	  				}
	  				if(rs.isLast()==false)
	  				{
	  					stat += ",";
	  				}
	  				urs.close();
	  				usmt.close();
	  			}
	  			System.out.println(stat);
	  			out.println(stat.toString());
	  			out.flush();
	  			out.close();
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