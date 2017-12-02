package com.checkPerson;

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
 *  ����������Ŀ�Ľ���
 */
@WebServlet("/checkExeuPro") 
public class checkExeuPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkExeuPro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ��ȡ��Ҫ������Ŀ��Ϣ
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/jsp");
		
		ResultSet rs=null;
		ResultSet exeurs=null;
		//��ȡ����������Ŀ��Ϣ
		String sql="SELECT ProName,ProMager,ProIntroduce,AlyAmount FROM applyproject where ProID IN"
				+ "(select ProID FROM exeuproject where ExeuSchedule='finished,waitCheck')";
		String exeusql="select PayAmount,ExeuComTime,comment,usedAmount from exeuproject";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);		    
		    PreparedStatement ps=con.prepareStatement(sql);
		    PreparedStatement exeups=con.prepareStatement(exeusql);
		    rs=ps.executeQuery();
		    exeurs=exeups.executeQuery();
		    //System.out.println(rs.getString(1));
		    String str="<h3>�������յ���Ŀ</h3>";
		    str+="<table border=1 cellspacing=0><tr><th>��Ŀ��</th><th>��Ŀ������</th><th>��Ŀ���</th><th>������</th><th>������</th>"
		    		+ "<th>��Ŀ����ʱ��</th><th>���˵��</th><th>ʹ�ý��</th><th>�鿴</th><th>����</th></tr>";
		   
		    while(rs.next()&&exeurs.next()){
		    	
		    	str+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+
			    		"</td><td>"+exeurs.getString(1)+"</td><td>"+exeurs.getString(2)+"</td><td>"+exeurs.getInt(3)+
			    		"</td><td>"+exeurs.getString(4)+"</td><td>"+"<a href='#'>����</a></td>"+"<td><a href='CheckPerson/toCheckPro.jsp'>�������</a></td></tr>";
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
		
		doGet(request, response);
	}

}
