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
 * 根据填写的颁发奖励的信息，提交到数据库
 */
@WebServlet("/giveReward")
public class giveReward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public giveReward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result=0;
		String sql="INSERT INTO gainproject(GainExplain,reward,rewardWay,emitPson,ProID) VALUES(?,?,?,?,?)";
		
		PrintWriter out=response.getWriter();		
		response.setContentType("text/jsp");
		//立项通过，获取审核信息
		String GainExplain=request.getParameter("gainComment");
		String reward=request.getParameter("reward");
		String rewardWay=request.getParameter("rewardWay");
		String emitPson=request.getParameter("emitPson");	
		String proid=request.getParameter("proid");
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);
			
		    PreparedStatement ps=con.prepareStatement(sql);
		   
		    ps.setString(1, GainExplain);
		    ps.setString(2,reward);
		    ps.setString(3, rewardWay);
		    ps.setString(4, emitPson);
		    ps.setString(5, proid); 
		    result=ps.executeUpdate();		  
		    
		    if(result>0){  //立项通过，从申请表中删除
		    	 JOptionPane.showMessageDialog(null,
						 "奖励发放成功", "", JOptionPane.INFORMATION_MESSAGE);
		    	 
//		    	 String delsql="delete from applyproject where ProID=?";
//		    	 PreparedStatement delps=con.prepareStatement(sql);
//		    	 delps.setString(1, proid);
//		    	 delps.executeUpdate();
		    	 
		    	 response.sendRedirect("/ProjectVerify/rewardPro");
		    }else{
		    	JOptionPane.showMessageDialog(null,
						 "奖励发放失败，请重新尝试", "", JOptionPane.INFORMATION_MESSAGE);
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
