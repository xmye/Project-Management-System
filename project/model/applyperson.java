package com.project.model;

public class applyperson {
	
	private String AlyPsonAccount;
	private String AlyPsonPsw;
	private String AlyPsonName;
	private String AlyPsonSex;
	private int AlyPsonAge;
	private String AlyPsonTel;
	private String AlyPsonAddr;
	private String AlyPsonOrgan;
	private Integer AlyId;
	
	public applyperson(){}

	public applyperson(String alyPsonAccount, String alyPsonPsw, String alyPsonName, String alyPsonSex, int alyPsonAge,
			String alyPsonTel, String alyPsonAddr, String alyPsonOrgan, Integer alyId) {
		super();
		AlyPsonAccount = alyPsonAccount;
		AlyPsonPsw = alyPsonPsw;
		AlyPsonName = alyPsonName;
		AlyPsonSex = alyPsonSex;
		AlyPsonAge = alyPsonAge;
		AlyPsonTel = alyPsonTel;
		AlyPsonAddr = alyPsonAddr;
		AlyPsonOrgan = alyPsonOrgan;
		AlyId = alyId;
	}

	public String getAlyPsonAccount() {
		return AlyPsonAccount;
	}

	public void setAlyPsonAccount(String alyPsonAccount) {
		AlyPsonAccount = alyPsonAccount;
	}

	public String getAlyPsonPsw() {
		return AlyPsonPsw;
	}

	public void setAlyPsonPsw(String alyPsonPsw) {
		AlyPsonPsw = alyPsonPsw;
	}

	public String getAlyPsonName() {
		return AlyPsonName;
	}

	public void setAlyPsonName(String alyPsonName) {
		AlyPsonName = alyPsonName;
	}

	public String getAlyPsonSex() {
		return AlyPsonSex;
	}

	public void setAlyPsonSex(String alyPsonSex) {
		AlyPsonSex = alyPsonSex;
	}

	public int getAlyPsonAge() {
		return AlyPsonAge;
	}

	public void setAlyPsonAge(int alyPsonAge) {
		AlyPsonAge = alyPsonAge;
	}

	public String getAlyPsonTel() {
		return AlyPsonTel;
	}

	public void setAlyPsonTel(String alyPsonTel) {
		AlyPsonTel = alyPsonTel;
	}

	public String getAlyPsonAddr() {
		return AlyPsonAddr;
	}

	public void setAlyPsonAddr(String alyPsonAddr) {
		AlyPsonAddr = alyPsonAddr;
	}

	public String getAlyPsonOrgan() {
		return AlyPsonOrgan;
	}

	public void setAlyPsonOrgan(String alyPsonOrgan) {
		AlyPsonOrgan = alyPsonOrgan;
	}

	public Integer getAlyPsonId() {
		return AlyId;
	}

	public void setAlyPsonId(Integer alyId) {
		AlyId = alyId;
	}

}
