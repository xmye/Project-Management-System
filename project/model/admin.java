package com.project.model;

public class admin {
	
	private String admin_account;
	private String admin_pwd;
	private String admin_name;
	private String admin_sex;
	private String admin_phone;
	
	public admin(){}
	
	

	public admin(String admin_account, String admin_pwd, String admin_name, String admin_sex,
			String admin_phone) {
		super();
		this.admin_account = admin_account;
		this.admin_pwd = admin_pwd;
		this.admin_name = admin_name;
		this.admin_sex = admin_sex;
		this.admin_phone = admin_phone;
	}



	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getAdmin_pwd() {
		return admin_pwd;
	}

	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_sex() {
		return admin_sex;
	}

	public void setAdmin_sex(String admin_sex) {
		this.admin_sex = admin_sex;
	}

	public String getAdmin_phone() {
		return admin_phone;
	}

	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}

}
