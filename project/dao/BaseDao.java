package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BaseDao {
	
	//建立与数据库的连接基类，包括连接数据库与关闭对象
	public Connection getConnection(){
		Connection conn = null;
		try {
			//加载JDBC驱动
			Class.forName("com.mysql.jdbc.Driver");
			//建立与数据库的连接
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/scienceproject", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭使用的对象
	public void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
