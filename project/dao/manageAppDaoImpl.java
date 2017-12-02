package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.model.checkadmin;

public class manageAppDaoImpl extends BaseDao implements manageAppDao{

    private List<checkadmin> list = new ArrayList<checkadmin>();
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	PreparedStatement ps = null;
	
	@Override
	public List<checkadmin> getMessage(String account) {
		// TODO Auto-generated method stub
		
		list.clear();
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			
			// 查询信息
			String sql = "select * from checkadmin where account=" + account;
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String account_c = resultSet.getString("account");
				String pwd = resultSet.getString("psw");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String part = resultSet.getString("departMent");
				//int id=Integer.parseInt(resultSet.getString("CheckAdminId"));
				checkadmin c = new checkadmin(account_c, pwd, name, sex, part);
				list.add(c);
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
	public List<checkadmin> getAllMessage() {
		// TODO Auto-generated method stub
		list.clear();
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			// 查询信息
			String sql = "select * from checkadmin;";
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String account_c = resultSet.getString("account");
				String pwd = resultSet.getString("psw");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				String part = resultSet.getString("departMent");
				//int id=Integer.parseInt(resultSet.getString("CheckAdminId"));
				checkadmin c = new checkadmin(account_c, pwd, name, sex, part);
				list.add(c);
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
	public int addApp(checkadmin app) {
		// TODO Auto-generated method stub
		
		int flag = 0;
		int id=0;
		try {
			// 连接数据库
			connection = this.getConnection();
			
			//查找最大id
		    statement = connection.createStatement();
		    String s="select max(CheckAdminId) id from checkadmin;";
		    ResultSet rs=statement.executeQuery(s);
		    while(rs.next()){
		    	id=Integer.parseInt(rs.getString("id"));
		    }
			// 插入语句
			String sql = "insert into checkadmin(account,psw,name,sex,departMent,CheckAdminId) values(?,?,?,?,?,?);";
			ps = connection.prepareStatement(sql);
			ps.setString(1, app.getAccount());
			ps.setString(2, app.getPsw());
			ps.setString(3, app.getName());
			ps.setString(4, app.getSex());
			ps.setString(5, app.getDepartMent());
			ps.setInt(6, id+1);
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
	public int updateApp(checkadmin app) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 更新语句
			String sql = "update checkadmin set name=?,sex=?,departMent=? where account=?;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, app.getName());
			ps.setString(2, app.getSex());
			ps.setString(3, app.getDepartMent());
			ps.setString(4, app.getAccount());
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
	public int deleteApp(checkadmin app) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 更新语句
			String sql = "delete from checkadmin where account=?;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, app.getAccount());
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
