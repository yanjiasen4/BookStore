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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Statistics_Single_User
 */
@WebServlet("/Statistics_Single_User")
public class Statistics_Single_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistics_Single_User() {
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
  		
  		/*HttpSession session = request.getSession(false);
  		if(session == null)
  		{
  			return;
  		}
  		else if(session.getAttribute("userid") == null)
  		{
  			return;
  		}*/
  		
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
	  		
	  		HttpSession session = request.getSession(false);
	  		String userid = session.getAttribute("userid").toString();
	  		PrintWriter out=response.getWriter();
	  		String stat = "";
	  		//rs = smt.executeQuery("select Price,Num,Date from orders where U_ID='"+userid+"'");
	  		rs = smt.executeQuery("select sum(Price*Num),Date from orders where U_ID='"+userid+"' group by Date order by Date");
	  		
	  		while(rs.next())
	  		{
	  			stat += rs.getString("sum(Price*Num)");
	  			if(!rs.isLast())
	  				stat += ",";
	  		}
	  		
	  		if(stat != "")
	  		{
	  			out.println(stat);
	  			System.out.println(stat);
	  			//out.println(stat.toString());
	  			out.flush();
	  			out.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
