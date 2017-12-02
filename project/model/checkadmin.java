package com.project.model;

public class checkadmin {
	
	private String account;
	private String psw;
	private String name;
	private String sex;
	private String departMent;
	private Integer CheckAdminId;
	
	public checkadmin(){}

	public checkadmin(String account, String psw, String name, String sex, String departMent) {
		super();
		this.account = account;
		this.psw = psw;
		this.name = name;
		this.sex = sex;
		this.departMent = departMent;
		//CheckAdminId = checkAdminId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartMent() {
		return departMent;
	}

	public void setDepartMent(String departMent) {
		this.departMent = departMent;
	}

	public Integer getCheckAdminId() {
		return CheckAdminId;
	}

	public void setCheckAdminId(Integer checkAdminId) {
		CheckAdminId = checkAdminId;
	}
	
}
