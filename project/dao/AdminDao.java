package com.project.dao;

import java.util.List;

import com.project.model.admin;

public interface AdminDao {
	public List<admin> getMessage(String admin_account);// ����account��ѯ����Ա��Ϣ

	public int updateAdmin(admin sAdmin);// ����admin����admin��Ϣ
	
	public int updateAdminPwd(admin sAdmin);//����account��������
	
	public boolean confirmPwd(admin sAdmin);//ȷ�������Ƿ���ȷ

}
