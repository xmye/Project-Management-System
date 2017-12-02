package com.chinaSofti.reglog;

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
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.model.applyperson;
import com.model.checkperson;
import com.project.model.admin;

/**
 * Servlet implementation class login
 */
@WebServlet("/login") 
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
	
    /**
     * @see HttpServlet#HttpServlet()		
     */
    public login() {
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
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		
		String email=request.getParameter("email");
		String psw=request.getParameter("psw");
		String alysql="SELECT * FROM ApplyPerson WHERE AlyPsonEmail=? && AlyPsonPsw=?";
		String checksql="SELECT * FROM checkadmin where account=? && psw=?";
		String adminsql="SELECT * FROM admin where admin_account=? && admin_password=?";
		
		HttpSession session=request.getSession();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);	
			//申请者
		    PreparedStatement ps=con.prepareStatement(alysql);
		    ps.setString(1, email);
		    ps.setString(2, psw);
		    rs=ps.executeQuery();
		    applyperson alypson=null;
		    while(rs.next()){
		    	alypson=new applyperson();
		    	alypson.setAddr(rs.getString("AlyPsonAddr"));
		    	alypson.setAge(rs.getInt("AlyPsonAge"));
		    	alypson.setEmail(rs.getString("AlyPsonEmail"));
		    	alypson.setName(rs.getString("AlyPsonName"));
		    	alypson.setOrg(rs.getString("AlyPsonOrgan"));
		    	alypson.setPsw(rs.getInt("AlyPsonPsw"));
		    	alypson.setSex(rs.getString("AlyPsonSex"));
		    	alypson.setId(rs.getInt("AlyID"));
		    	alypson.setTel(rs.getInt("AlyPsonTel"));
		    }
		    if(alypson!=null){
		    	response.sendRedirect("applyPerson/AlyPsonMainPage.jsp"); //申请者界面
		    	return;
		    }
		    	//验证是否为审核者
		    	PreparedStatement checkps=con.prepareStatement(checksql); 
			    checkps.setString(1, email);
			    checkps.setString(2, psw);
			    checkperson checkpson=null;
			    rs=checkps.executeQuery();
			    while(rs.next()){
			    	checkpson=new checkperson();
			    	checkpson.setAccount(rs.getInt("account"));
			    	checkpson.setDepartment(rs.getString("departMent"));
			    	checkpson.setName(rs.getString("name"));
			    	checkpson.setPsw(rs.getInt("psw"));
			    	checkpson.setId(rs.getInt("CheckAdminID"));
			    	checkpson.setSex(rs.getString("sex"));
			    	System.out.println("department="+rs.getString("departMent"));
			    }
			    if(checkpson!=null&&!checkpson.getDepartment().equals("admin")){  //是审核者而非管理人员
			    	response.sendRedirect("CheckPerson/checkPsonMainPage.jsp");
			    	return ;
			     }
			    
			    //超级管理员
			    String superAdminsql="select admin_account,admin_password from admin where ?='11' && ?='11'";
			    
			    PreparedStatement superAdminps=con.prepareStatement(superAdminsql); 
			    superAdminps.setString(1, email);
			    superAdminps.setString(2, psw);
			    admin admi=null;
			    ResultSet adminrs=superAdminps.executeQuery();
			    
			    while(adminrs.next()){
			    	admi=new admin();
			    	admi.setAdmin_account(adminrs.getString(1));				
			    }
			    if(admi!=null){
			    	session.setAttribute("account", admi.getAdmin_account());
			    	response.sendRedirect("main_page_super/main.jsp");
			    	return ;
			    }
			    
			        //系统管理员
			    	PreparedStatement adminps=con.prepareStatement(adminsql); 
			    	adminps.setString(1, email);
			    	adminps.setString(2, psw);
			    	admin sysadm=new admin();
				    ResultSet sysrs=adminps.executeQuery();
				    while(rs.next()){
				    	sysadm=new admin();
				    	sysadm.setAdmin_account(sysrs.getString(1));				    	
				    }
				    if(sysadm!=null){
				    	session.setAttribute("account", sysadm.getAdmin_account());
				    	response.sendRedirect("main_page_sys/main.jsp");
				    	return;
				    }else{
				   JOptionPane.showMessageDialog(null,
						 "用户名或密码错误，请重新输入", "", JOptionPane.INFORMATION_MESSAGE);	
				    }   
				    
		    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
