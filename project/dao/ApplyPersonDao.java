package com.project.dao;

import java.util.List;

import com.project.model.applyperson;

public interface ApplyPersonDao {

	public List<applyperson> getAllMessage();//��ȡ����ע���ߵ���Ϣ
	
	public int deleteReg(String account);//�����˻�ɾ��ע���û�
	
}
