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
 * ���������Ϣ��д
 */
@WebServlet("/passExeuPro")
public class passExeuPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
       
    /**
     * @see HttpServlet#HttpServlet()																			
     */
    public passExeuPro() {
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
		String sql="INSERT INTO checkproject(CheckTime,CheckPsonName,CheckComment,ProID) VALUES(?,?,?,?)";
		
		PrintWriter out=response.getWriter();		
		response.setContentType("text/jsp");
		//����ͨ������ȡ�����Ϣ
		String proid=request.getParameter("proid");
		String checktime=request.getParameter("checktime");
		String CheckComment=request.getParameter("CheckComment");
		String checkPson=request.getParameter("checkPson");		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);
			
		    PreparedStatement ps=con.prepareStatement(sql);
		    
		    ps.setString(1, checktime);
		    ps.setString(2,checkPson);
		    ps.setString(3, CheckComment);
		    ps.setString(4, proid);
		    out.print(proid+"proid...."+checktime+CheckComment+checkPson);
		    result=ps.executeUpdate();
		    
		    
		    out.print("checkPson..."+checkPson);
		    if(result>0){
		    	 JOptionPane.showMessageDialog(null,
						 "����ͨ��", "", JOptionPane.INFORMATION_MESSAGE);
		    	 response.sendRedirect("/ProjectVerify/checkExeuPro");
		    }else{
		    	JOptionPane.showMessageDialog(null,
						 "����ʧ��", "", JOptionPane.INFORMATION_MESSAGE);
		    }   
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		out.close();	
	}
}
