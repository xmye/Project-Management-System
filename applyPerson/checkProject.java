package com.applyPerson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * 验收申报
 */
@WebServlet("/checkProject") 
public class checkProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkProject() {
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
		// TODO Auto-generated method stub
		int result=0;
		
		PrintWriter out=response.getWriter();		
		response.setContentType("text/jsp");
		//获取验收申报提交的
		String proName=request.getParameter("proname");
		String endTime=request.getParameter("endTime");
		String amount=request.getParameter("amount");
		String comment=request.getParameter("ccoment");
		String CheckPsonName=request.getParameter("CheckPsonName");
		//String proMaterial=request.getParameter("proMaterial");
		
		
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);
						
			out.print("name="+proName);
			
			String sql="UPDATE exeuproject SET ExeuComTime=?,COMMENT=?,usedAmount=?,CheckPsonName=?,ExeuSchedule='finished,waitCheck' "
					+ "WHERE ProID =(SELECT ProID FROM applyproject WHERE ProName=?)";
		    PreparedStatement ps=con.prepareStatement(sql);
		   
		    ps.setString(1, endTime);
		    ps.setString(3, amount);
		    ps.setString(2, comment);
		    ps.setString(4, CheckPsonName);
		    ps.setString(5, proName);
		    //ps.setString(9, proMaterial);
		    
		    result=ps.executeUpdate();
		    out.print("name="+proName);
		    out.println("proName = "+proName + "result="+result);
		    
		    if(result>0){
		    	 JOptionPane.showMessageDialog(null,
						 "提交成功", "", JOptionPane.INFORMATION_MESSAGE);
		    	 response.sendRedirect("applyPerson/AlyPsonMainPage.jsp");
		    }else{
		    	JOptionPane.showMessageDialog(null,
						 "提交失败，请重新尝试", "", JOptionPane.INFORMATION_MESSAGE);
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
