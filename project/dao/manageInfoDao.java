package com.project.dao;

import java.util.List;

import com.project.model.info;

//import sun.text.UCompactIntArray;

public interface manageInfoDao {

	public int addInfo(info information);//�����Ϣ������֪ͨͨ����߿Ƽ���̬
	
	public List<info> getAllMessage(String type);//�������ͻ�ȡ������Ϣ
	
	public List<info> getMessage(int id);//����id��ȡ��Ϣ
	
	public int deleteInfo(int id);//����idɾ����Ϣ
	
	public int updateInfo(info information);//������Ϣ
}
