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

/**
 * Servlet implementation class seeReward
 */
@WebServlet("/seeReward")
public class seeReward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seeReward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/jsp");
		
		ResultSet rs=null;
		ResultSet exeurs=null;
		
		String sql="select  from gainproject";				
		String prosql="select ProName,ProMager,ProIntroduce from applyproject where ProID IN (select ProID from checkproject)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);		    
		    PreparedStatement ps=con.prepareStatement(sql);
		    PreparedStatement props=con.prepareStatement(prosql);
		    exeurs=ps.executeQuery();
		    rs=props.executeQuery();

		    String str="<h3>��ý�������Ŀ</h3>";
		    str+="<table border=1 cellspacing=0><tr><th>��Ŀ��</th><th>��Ŀ������</th><th>��Ŀ���</th>"
		    		+ "<th>�ɹ�˵��</th><th>����</th><th>������ʽ</th><th>����ʱ��</th><th>������</th><th>�鿴</th><th>����</th></tr>";
		    
		    while(rs.next()&&exeurs.next()){
		    	
		    	str+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"
			    		+exeurs.getString(1)+"</td><td>"+exeurs.getString(2)+"</td><td>"+exeurs.getString(3)+
			    		"</td><td>"+"<a href='#'>����</a></td>"+"<td><a href='CheckPerson/giveReward.jsp'>���Ž���</a></td></tr>";
		    }
		    str+="</table>";
		    out.println(str);
		    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
