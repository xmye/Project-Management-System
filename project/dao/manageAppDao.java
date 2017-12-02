package com.project.dao;

import java.util.List;

import com.project.model.checkadmin;

public interface manageAppDao {
	
	public List<checkadmin> getMessage(String account);// 根据account查询审批人员信息
	
	public List<checkadmin> getAllMessage();//查询所有信息
	
	public int addApp(checkadmin app);//添加审批人员
	
	public int updateApp(checkadmin app);//更新审批人员信息
	
	public int deleteApp(checkadmin app);//删除审批人员

}
