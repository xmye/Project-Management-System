package com.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.model.info;

public class manageInfoDaoImpl extends BaseDao implements manageInfoDao {

	private List<info> list = new ArrayList<info>();
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	PreparedStatement ps = null;

	@Override
	public int addInfo(info information) {
		// TODO Auto-generated method stub

		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 插入语句
			String sql = "insert into info(title,publishPson,publishTime,updateTime,type,content,InfoId) "
					+ "values(?,?,?,?,?,?,null);";
			ps = connection.prepareStatement(sql);
			ps.setString(1, information.getTitle());
			ps.setString(2, information.getPublishPson());
			ps.setDate(3, information.getPublishTime());
			ps.setDate(4, information.getUpdateTime());
			ps.setString(5, information.getType());
			ps.setString(6, information.getContent());
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
	public List<info> getAllMessage(String type) {
		// TODO Auto-generated method stub
		list.clear();
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			// 查询信息
			String sql = "select * from info where type='" + type + "' order by InfoId desc;";
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String publisher = resultSet.getString("publishPson");
				String type_info = resultSet.getString("type");
				// 处理时间,将字符串替换为date类型
				Date pub_time=resultSet.getDate("publishTime");
				Date update_time = resultSet.getDate("updateTime");
				String content = resultSet.getString("content");
				int id = Integer.parseInt(resultSet.getString("InfoId"));

				info information = new info(title, publisher, pub_time, update_time, type_info, content, id);
				list.add(information);
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
		return list;
	}

	@Override
	public List<info> getMessage(int id) {
		// TODO Auto-generated method stub
		list.clear();
		try {
			// 连接数据库
			connection = this.getConnection();
			statement = connection.createStatement();
			
			// 查询信息
			String sql = "select * from info where InfoId='" + id+"';";
			// 得到结果并处理结果
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String publishPson = resultSet.getString("publishPson");
				Date publishTime = resultSet.getDate("publishTime");
				Date updateTime = resultSet.getDate("updateTime");
				String type = resultSet.getString("type");
				String content=resultSet.getString("content");
				//int id=Integer.parseInt(resultSet.getString("CheckAdminId"));
				info information=new info(title, publishPson, publishTime, updateTime, type, content,id);
				list.add(information);
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
	public int deleteInfo(int id) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 更新语句
			String sql = "delete from info where InfoId=?;";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
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
	public int updateInfo(info information) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			// 连接数据库
			connection = this.getConnection();
			// statement = connection.createStatement();
			// 更新语句
			//String sql = "update checkadmin set name=?,sex=?,departMent=? where account=?;";
			//ps = connection.prepareStatement(sql);
			//ps.setString(1, app.getName());
			//ps.setString(2, app.getSex());
			//ps.setString(3, app.getDepartMent());
			//ps.setString(4, app.getAccount());
			//flag = ps.executeUpdate();
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
