package com.project.dao;

import java.util.List;

import com.project.model.applyperson;

public interface ApplyPersonDao {

	public List<applyperson> getAllMessage();//获取所有注册者的信息
	
	public int deleteReg(String account);//根据账户删除注册用户
	
}
