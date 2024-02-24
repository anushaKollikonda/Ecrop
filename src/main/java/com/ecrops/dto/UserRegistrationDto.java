package com.ecrops.dto;


public class UserRegistrationDto {
	
	private String userid;
	private int dcode;
	private int  mcode;
	private String encpassword;
	private String status;
	private String type_user;
	
	//private int  opunitcode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getType_user() {
		return type_user;
	}
	public void setType_user(String type_user) {
		this.type_user = type_user;
	}
	
//	public int getOpunitcode() {
//		return opunitcode;
//	}
//	public void setOpunitcode(int opunitcode) {
//		this.opunitcode = opunitcode;}
		
	public UserRegistrationDto(String userid, int dcode, int mcode, String encpassword, String status, int opunitcode,
			String type_user) {
		super();
		this.userid = userid;
		this.dcode = dcode;
		this.mcode = mcode;
		this.encpassword = encpassword;
		this.status = status;
		this.type_user = type_user;
		//this.opunitcode = opunitcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getDcode() {
		return dcode;
	}
	public void setDcode(int dcode) {
		this.dcode = dcode;
	}
	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public String getEncpassword() {
		return encpassword;
	}
	public void setEncpassword(String encpassword) {
		this.encpassword = encpassword;
	}

}
