package com.chinaSofti.reglog;

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
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result=0;
		String sql="insert into ApplyPerson(AlyPsonEmail,AlyPsonPsw,AlyPsonName,AlyPsonSex,AlyPsonAge,AlyPsonOrgan,AlyPsonTel,AlyPsonAddr) values(?,?,?,?,?,?,?,?)";
		
		PrintWriter out=response.getWriter();		
		response.setContentType("text/jsp;charset=utf-8");
		
		request. setCharacterEncoding("UTF-8");
		
		String email=request.getParameter("email");
		String psw=request.getParameter("psw");
		String pswAgain=request.getParameter("pswAgain");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String org=request.getParameter("org");
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");
		
		if(psw.equals(pswAgain)){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);
		    
		    PreparedStatement ps=con.prepareStatement(sql);
		    
		    		
		    ps.setString(1, email);
		    ps.setString(2,psw);
		    ps.setString(3, name);
		    ps.setString(4, sex);
		    ps.setString(5, age);
		    ps.setString(6, org);
		    ps.setString(7, tel);
		    ps.setString(8, addr);
		   
		    result=ps.executeUpdate();
		    out.println("hello from post method "+email + psw + addr +pswAgain);
		    if(result>0){
		    	 JOptionPane.showMessageDialog(null,
						 "注册成功", "", JOptionPane.INFORMATION_MESSAGE);
		    	 response.sendRedirect("main/loginPage.jsp");
		    	 return;
		    }else{
		    	JOptionPane.showMessageDialog(null,
						 "注册失败，请重新尝试", "", JOptionPane.INFORMATION_MESSAGE);
		    }   
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		out.close();	
	}else{
		 JOptionPane.showMessageDialog(null,
				 "两次密码不一致，请重新输入", "", JOptionPane.INFORMATION_MESSAGE);
	}		
	}
}
