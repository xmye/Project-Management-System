package com.project.dao;

import java.util.List;

import com.project.model.checkadmin;

public interface manageAppDao {
	
	public List<checkadmin> getMessage(String account);// ����account��ѯ������Ա��Ϣ
	
	public List<checkadmin> getAllMessage();//��ѯ������Ϣ
	
	public int addApp(checkadmin app);//���������Ա
	
	public int updateApp(checkadmin app);//����������Ա��Ϣ
	
	public int deleteApp(checkadmin app);//ɾ��������Ա

}
