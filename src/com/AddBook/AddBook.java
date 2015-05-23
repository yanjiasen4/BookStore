package com.AddBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userid") == null)
		{
			System.out.println(session.getAttribute("userid"));
			response.sendRedirect("toLogin.html");
		    return;
		}
		String UserIDstr = session.getAttribute("userid").toString();
		int UserID = Integer.parseInt(UserIDstr);
		
		Connection conn= null;
		Statement smt = null;
		ResultSet rs = null;
		
		String SBID=request.getParameter("BookName");
			
		String className="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sample_one";
			
		String userName = "root";
		String userPass ="asdffdsa";
		
		try{
		//加载驱动
		Class.forName(className);
		//得到连接
		conn = DriverManager.getConnection(url,userName,userPass);
		//创建Statement
		smt = conn.createStatement();
			
		rs = smt.executeQuery("select BookName, Price from book_list where BookName='"+ SBID +"'");
		
		if(rs.next())
		{
			int price = rs.getInt("Price");
			String BookName=rs.getString("BookName");
			rs.close();
			ResultSet add = null;
			add = smt.executeQuery("select * from cart where B_Name = '"+ BookName +"' and U_ID = "+ UserID);
			if(add.next())
			{
				int cnt = add.getInt("Num") + 1;
				smt.executeUpdate("update cart set Num = '"+ cnt +"'" + "where U_ID = " + UserID + " and B_Name = '" + BookName + "'");
			}
			else
			{
				smt.executeUpdate("insert into cart values ("+ UserID + ",'" + BookName + "',"+price+",1)");
			}
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
		response.sendRedirect("Index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
