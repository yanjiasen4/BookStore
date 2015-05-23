package ShowCart;

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

/**
 * Servlet implementation class ShowCart
 */
@WebServlet("/ShowCart")
public class ShowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
  		if(session == null)
  		{
  			response.sendRedirect("toLogin.html");
		    return;
  		}else if(session.getAttribute("userid") == null)
  		{
  			response.sendRedirect("toLogin.html");
  			return;
  		}
		
		Connection conn= null;
  		Statement smt = null;
  		ResultSet rs = null;
  		
  		String UserID = session.getAttribute("userid").toString();
  		
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
	  		
	  		rs = smt.executeQuery("select * from cart where U_ID = '" + UserID + "'");
	  		if(rs.next())
	  		{
	  			rs.beforeFirst();
	  			int total_price = 0;
	  			while(rs.next())
	  			{
	  				total_price += rs.getInt("Price")*rs.getInt("Num"); //计算总价
	  			}
	  			rs.beforeFirst();
	  			request.setAttribute("emptycart", 0);  //结果不为空
	  			request.setAttribute("data",rs);
	  			request.setAttribute("price",total_price);
	  		}
	  		else
	  		{
	  			request.setAttribute("emptycart", 1);
	  		}
	  		request.getRequestDispatcher("Cart.jsp").forward(request,response);
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
  		//response.sendRedirect("Index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
