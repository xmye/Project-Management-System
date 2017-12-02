package com.project.dao;

import java.util.List;

import com.project.model.admin;

public interface manageSysDao { 
	public List<admin> getMessage(String admin_account);// 根据account查询管理员信息
	
	public List<admin> getAllMessage();//查询所有信息
	
	public int addSys(admin sAdmin);//添加系统管理员
	
	public int updateSys(admin sAdmin);//更新管理员信息
	
	public int deleteSys(admin sAdmin);//删除管理员

}
