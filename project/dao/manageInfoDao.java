package com.project.dao;

import java.util.List;

import com.project.model.info;

//import sun.text.UCompactIntArray;

public interface manageInfoDao {

	public int addInfo(info information);//添加信息，发布通知通告或者科技动态
	
	public List<info> getAllMessage(String type);//根据类型获取所有信息
	
	public List<info> getMessage(int id);//根据id获取信息
	
	public int deleteInfo(int id);//根据id删除信息
	
	public int updateInfo(info information);//更新信息
}
