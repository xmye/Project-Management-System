package com.project.dao;

import java.util.List;

import com.project.model.admin;

public interface AdminDao {
	public List<admin> getMessage(String admin_account);// 根据account查询管理员信息

	public int updateAdmin(admin sAdmin);// 根据admin更新admin信息
	
	public int updateAdminPwd(admin sAdmin);//根据account更新密码
	
	public boolean confirmPwd(admin sAdmin);//确认密码是否正确

}
