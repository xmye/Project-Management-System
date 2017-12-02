package com.applyPerson;

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
 * Servlet implementation class creatProject
 */
@WebServlet("/creatProject")  //�����걨
public class creatProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//�������ݿ�
	static final String DB_URL="jdbc:mysql://localhost:3306/scienceproject";
	static final String USER="root";
	static final String PSW="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creatProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  ,proMaterial
		int result=0;
		String sql="INSERT INTO applyproject(ProName,ProMager,ProType,AlyAmount,ProIntroduce,exeuPlan,ProBegTime,ProEndTime) VALUES(?,?,?,?,?,?,?,?)";
		
		PrintWriter out=response.getWriter();		
		response.setContentType("text/jsp");
		//��ȡҳ���ύ������
		String proName=request.getParameter("proName");
		String ProMester=request.getParameter("ProMester");
		String proType=request.getParameter("proType");
		String amount=request.getParameter("amount");
		String comment=request.getParameter("comment");
		String exeuPlan=request.getParameter("exeuPlan");
		String beginTime=request.getParameter("beginTime");
		String endTime=request.getParameter("endTime");
		//String proMaterial=request.getParameter("proMaterial");
		
	
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(DB_URL, USER, PSW);
		    
		    PreparedStatement ps=con.prepareStatement(sql);
		   
		    ps.setString(1, proName);
		    ps.setString(2,ProMester);
		    ps.setString(3, proType);
		    ps.setString(4, amount);
		    ps.setString(5, comment);
		    ps.setString(6, exeuPlan);
		    ps.setString(7, beginTime);
		    ps.setString(8, endTime);
		    //ps.setString(9, proMaterial);
		    out.println("proName = "+proName + result);
		   
		    result=ps.executeUpdate(); //ִ��sql����������
		    out.println("proName = "+proName + "result="+result);
		    if(result>0){
		    	 JOptionPane.showMessageDialog(null,
						 "����ɹ�", "", JOptionPane.INFORMATION_MESSAGE);
		    	 response.sendRedirect("applyPerson/AlyPsonMainPage.jsp");
		    }else{
		    	JOptionPane.showMessageDialog(null,
						 "����ʧ��", "", JOptionPane.INFORMATION_MESSAGE);
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
