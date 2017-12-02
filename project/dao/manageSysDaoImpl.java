package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.model.admin;

public class manageSysDaoImpl extends BaseDao implements manageSysDao {

    private List<admin> list = new ArrayList<admin>();
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	PreparedStatement ps = null;

	// ����account��ѯadmin����Ϣ
	@Override
	public List<admin> getMessage(String admin_account) {
		// TODO Auto-generated method stub
		list.clear();
		try {
			// �������ݿ�
			connection = this.getConnection();
			statement = connection.createStatement();
			// ��ѯ��Ϣ
			String sql = "select * from admin where admin_account=" + admin_account;
			// �õ������������
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String account = resultSet.getString("admin_account");
				String pwd = resultSet.getString("admin_password");
				String name = resultSet.getString("admin_name");
				String sex = resultSet.getString("admin_sex");
				String phone = resultSet.getString("admin_phone");
				admin ad = new admin(account, pwd, name, sex, phone);
				list.add(ad);
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
	public int addSys(admin sAdmin) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// �������ݿ�
			connection = this.getConnection();
			// statement = connection.createStatement();
			// �������
			String sql = "insert into admin values(?,?,?,?,?);";
			ps = connection.prepareStatement(sql);
			ps.setString(1, sAdmin.getAdmin_account());
			ps.setString(2, sAdmin.getAdmin_account());
			ps.setString(3, sAdmin.getAdmin_name());
			ps.setString(4, sAdmin.getAdmin_sex());
			ps.setString(5, sAdmin.getAdmin_phone());
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
	
	// ����account����
	@Override
	public int updateSys(admin sAdmin) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// �������ݿ�
			connection = this.getConnection();
			// statement = connection.createStatement();
			// �������
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


	@Override
	public int deleteSys(admin sAdmin) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// �������ݿ�
			connection = this.getConnection();
			// statement = connection.createStatement();
			// �������
			String sql = "delete from admin where admin_account=?;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, sAdmin.getAdmin_account());
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
	public List<admin> getAllMessage() {
		// TODO Auto-generated method stub
		list.clear();
		try {
			// �������ݿ�
			connection = this.getConnection();
			statement = connection.createStatement();
			// ��ѯ��Ϣ
			String sql = "select * from admin where admin_account!='111111';";
			// �õ������������
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String account = resultSet.getString("admin_account");
				String pwd = resultSet.getString("admin_password");
				String name = resultSet.getString("admin_name");
				String sex = resultSet.getString("admin_sex");
				String phone = resultSet.getString("admin_phone");
				admin ad = new admin(account, pwd, name, sex, phone);
				list.add(ad);
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

	
}
