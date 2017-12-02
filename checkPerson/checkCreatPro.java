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
import javax.swing.JOptionPane;

/**
 * Servlet implementation class checkCreatPro
 */
@WebServlet("/checkCreatPro") // ����������Ŀ����
public class checkCreatPro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final String DB_URL = "jdbc:mysql://localhost:3306/scienceproject";
	static final String USER = "root";
	static final String PSW = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkCreatPro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/jsp");

		ResultSet rs = null;
		// ��ȡ����������Ŀ��Ϣ
		String sql = "SELECT ProName,ProType,ProMager,ProBegTime,ProEndTime,AlyAmount,ProIntroduce,Remarks,"
				+ "Material,ProMember1,ProMember2,ProMember3,ProMember4,ProMember5,exeuPlan,ProID FROM applyproject";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(DB_URL, USER, PSW);
			PreparedStatement ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			String str = "<h3>�����������Ŀ</h3>";
			str += "<table border=1 cellspacing=0><tr><th>��Ŀ��</th><th>��Ŀ���</th><th>��Ŀ����</th><th>��Ŀ������</th><th>��Ŀ��ʼʱ��</th>"
					+ "<th>��Ŀ����ʱ��</th><th>������</th><th>��Ŀ���</th><th>��ע</th><th>����</th><th>��Ŀ��Ա1</th>"
					+ "<th>��Ŀ��Ա2</th><th>��Ŀ��Ա3</th><th>��Ŀ��Ա4</th><th>��Ŀ��Ա5</th><th>ʵʩ�ƻ�</th><th>����</th></tr>";

			while (rs.next()) {

				str += "<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(16) + "</td><td>" + rs.getString(2)
						+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
						+ "</td><td>" + rs.getInt(6) + "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8)
						+ "</td><td>" + rs.getString(9) + "</td><td>" + rs.getString(10) + "</td><td>"
						+ rs.getString(11) + "</td><td>" + rs.getString(12) + "</td><td>" + rs.getString(13)
						+ "</td><td>" + rs.getString(14) + "</td><td>" + rs.getString(15) + "</td><td>"
						+ "<a href=CheckPerson/checkCreatPro.jsp>�������</a></td></tr>";
			}
			str += "</table>";
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
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
