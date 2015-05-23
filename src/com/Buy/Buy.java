package com.Buy;

import java.io.IOException;

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
import java.util.Date;
/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
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
	  		
	  		HttpSession session = request.getSession(false);
	  		String UserID = session.getAttribute("userid").toString();
	  		String BookName;
	  		String Num;
	  		String Price;
	  		String OrderID;
	  		long currentTime=new Date().getTime();  
  			OrderID = "bb"+currentTime;
	  		rs = smt.executeQuery("select * from cart where U_ID = '"+ UserID + "'");
	  		while(rs.next())
	  		{
	  			Statement order_smt = conn.createStatement();
	  			BookName = rs.getString("B_Name");
	  			Num = rs.getString("Num");
	  			Price = rs.getString("Price");
	  			//System.out.println("insert into orders(U_ID,B_Name,Num,Price) values ('"+UserID+"','"+BookName+"','"+Num+"','"+Price+"')");
	  			order_smt.executeUpdate("insert into orders(OrderID,U_ID,B_Name,Num,Price) values ('" +OrderID+"','"+UserID+"','"+BookName+"','"+Num+"','"+Price+"')");
	  			order_smt.close();
	  		}
	  		smt.executeUpdate("delete from cart where U_ID = '" +UserID + "'");
	  		response.sendRedirect("BuySuc.html");
	  		
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
