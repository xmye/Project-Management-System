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
@WebServlet("/checkCreatPro") // 申请立项项目界面
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
		// 获取申请立项项目信息
		String sql = "SELECT ProName,ProType,ProMager,ProBegTime,ProEndTime,AlyAmount,ProIntroduce,Remarks,"
				+ "Material,ProMember1,ProMember2,ProMember3,ProMember4,ProMember5,exeuPlan,ProID FROM applyproject";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(DB_URL, USER, PSW);
			PreparedStatement ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			String str = "<h3>申请立项的项目</h3>";
			str += "<table border=1 cellspacing=0><tr><th>项目名</th><th>项目编号</th><th>项目类型</th><th>项目负责人</th><th>项目开始时间</th>"
					+ "<th>项目结束时间</th><th>申请金额</th><th>项目简介</th><th>备注</th><th>材料</th><th>项目成员1</th>"
					+ "<th>项目成员2</th><th>项目成员3</th><th>项目成员4</th><th>项目成员5</th><th>实施计划</th><th>操作</th></tr>";

			while (rs.next()) {

				str += "<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(16) + "</td><td>" + rs.getString(2)
						+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
						+ "</td><td>" + rs.getInt(6) + "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(8)
						+ "</td><td>" + rs.getString(9) + "</td><td>" + rs.getString(10) + "</td><td>"
						+ rs.getString(11) + "</td><td>" + rs.getString(12) + "</td><td>" + rs.getString(13)
						+ "</td><td>" + rs.getString(14) + "</td><td>" + rs.getString(15) + "</td><td>"
						+ "<a href=CheckPerson/checkCreatPro.jsp>立项审核</a></td></tr>";
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
