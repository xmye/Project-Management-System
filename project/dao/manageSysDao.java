package com.project.dao;

import java.util.List;

import com.project.model.admin;

public interface manageSysDao { 
	public List<admin> getMessage(String admin_account);// ����account��ѯ����Ա��Ϣ
	
	public List<admin> getAllMessage();//��ѯ������Ϣ
	
	public int addSys(admin sAdmin);//���ϵͳ����Ա
	
	public int updateSys(admin sAdmin);//���¹���Ա��Ϣ
	
	public int deleteSys(admin sAdmin);//ɾ������Ա

}
