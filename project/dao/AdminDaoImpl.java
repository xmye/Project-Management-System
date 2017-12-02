package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.model.admin;

public class AdminDaoImpl extends BaseDao implements AdminDao {

	private List<admin> admin = new ArrayList<admin>();
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	PreparedStatement ps = null;

	// 根据account查询admin的信息
	@Override
	public List<admin> getMessage(String admin_account) {
		// TODO Auto-generated method stub
		admin.clear();
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			// 查询信息
			String sql = "select * from admin where admin_account=" + admin_account;
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String account = resultSet.getString("admin_account");
				String pwd = resultSet.getString("admin_password");
				String name = resultSet.getString("admin_name");
				String sex = resultSet.getString("admin_sex");
				String phone = resultSet.getString("admin_phone");
				admin ad = new admin(account, pwd, name, sex, phone);
				admin.add(ad);
			}
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
		return admin;
	}

	// 根据account更新
	@Override
	public int updateAdmin(admin sAdmin) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {

			System.out.println(sAdmin.getAdmin_name());
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 更新语句

			String sql = "update admin set admin_name=?,admin_sex=?,admin_phone=? where admin_account=?;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, sAdmin.getAdmin_name());
			ps.setString(2, sAdmin.getAdmin_sex());
			ps.setString(3, sAdmin.getAdmin_phone());
			ps.setString(4, sAdmin.getAdmin_account());
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

	// 更新密码
	@Override
	public int updateAdminPwd(admin sAdmin) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			// 更新语句
			String sql = "update admin set admin_password=? where admin_account=?;";
			ps = connection.prepareStatement(sql);
			ps.setString(2, sAdmin.getAdmin_account());
			ps.setString(1, sAdmin.getAdmin_pwd());
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

	@Override
	public boolean confirmPwd(admin sAdmin) {
		// TODO Auto-generated method stub
		String pwd = null;
		boolean flag = false;
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			// 查询信息
			String sql = "select * from admin where admin_account=" + sAdmin.getAdmin_account();
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				pwd = resultSet.getString("admin_password");
			}
			if (pwd.equals(sAdmin.getAdmin_pwd()))
				flag = true;
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
