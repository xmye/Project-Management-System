package com.checkPerson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * 立项通过信息填写
 */
@WebServlet("/passCreatPro")
public class passCreatPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public passCreatPro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int result=0;
		String sql="INSERT INTO exeuproject(PayAmount,ProID,ExeuTime,ExeuEndTime,CheckPsonName,remasks) VALUES(?,?,?,?,?,?)";
		
		PrintWriter out=response.getWriter();		
		response.setContentType("text/jsp");
		//立项通过，获取审核信息
		String amount=request.getParameter("amount");
		String proid=request.getParameter("proid");
		String beginTime=request.getParameter("beginTime");
		String endTime=request.getParameter("endTime");
		String checkPson=request.getParameter("checkPson");
		String remasks=request.getParameter("remasks");		
	
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);
			
		    PreparedStatement ps=con.prepareStatement(sql);
		   
		    ps.setString(1, amount);
		    ps.setString(2,proid);
		    ps.setString(3, beginTime);
		    ps.setString(4, endTime);
		    ps.setString(5, checkPson);
		    ps.setString(6, remasks);
		   
		    result=ps.executeUpdate();
		    out.print("checkPson..."+checkPson);
		    
		    if(result>0){  //立项通过，从申请表中删除
		    	 JOptionPane.showMessageDialog(null,
						 "立项通过", "", JOptionPane.INFORMATION_MESSAGE);
		    	 
//		    	 String delsql="delete from applyproject where ProID=?";
//		    	 PreparedStatement delps=con.prepareStatement(sql);
//		    	 delps.setString(1, proid);
//		    	 delps.executeUpdate();
		    	 
		    	 response.sendRedirect("/ProjectVerify/checkCreatPro");
		    }else{
		    	JOptionPane.showMessageDialog(null,
						 "立项失败，请重新尝试", "", JOptionPane.INFORMATION_MESSAGE);
		    }   
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		out.close();	
	}
}
