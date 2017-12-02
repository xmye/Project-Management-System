package com.project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.project.model.applyperson;

public class ApplyPersonDaoImpl extends BaseDao implements ApplyPersonDao{
	
	List<applyperson> list=new ArrayList<applyperson>();
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	PreparedStatement ps = null;

	@Override
	public List<applyperson> getAllMessage() {
		// TODO Auto-generated method stub
		
		list.clear();
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			// 查询信息
			String sql = "select * from applyperson;";
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String account = resultSet.getString("AlyPsonAccount");
				String pwd = resultSet.getString("AlyPsonPsw");
				String name = resultSet.getString("AlyPsonName");
				String sex = resultSet.getString("AlyPsonSex");
				int age =Integer.parseInt(resultSet.getString("AlyPsonAge"));
				String tel=resultSet.getString("AlyPsonTel");
				String addr=resultSet.getString("AlyPsonAddr");
				String organ=resultSet.getString("AlyPsonOrgan");
				int id=Integer.parseInt(resultSet.getString("AlyId"));
				applyperson apply_p = new applyperson(account, pwd, name, sex, age, tel, addr, organ, id);
				list.add(apply_p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// if (resultSet != null)
				// resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public int deleteReg(String account) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 更新语句
			String sql = "delete from applyperson where AlyPsonAccount=?;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			flag = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

}
