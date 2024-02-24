package com.ecrops.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_registration", uniqueConstraints = @UniqueConstraint(columnNames = "userid"))
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private String userid;

	private String encpassword;
	
  @Column(name="wbdcode")
	private int dcode;
  
  @Column(name="wbmcode")

	private int mcode;

	private String status;

	private String type_user;
	
	// private int opunitcode;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "id"))
	private Collection<Roles> roles;

	public AppUser() {
		super();
	}

	public AppUser(String userid, String encpassword, int dcode, int mcode, String status, String type_user,
			Collection<Roles> roles) {
		super();
		this.userid = userid;
		this.encpassword = encpassword;
		this.dcode = dcode;
		this.mcode = mcode;
		this.status = status;
		this.type_user = type_user;
		this.roles = roles;

		// this.opunitcode = opunitcode;
		
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEncpassword() {
		return encpassword;
	}

	public void setEncpassword(String encpassword) {
		this.encpassword = encpassword;
	}

	public Collection<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
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

//	 	public int getOpunitcode() { 
//		return opunitcode; }
//	
//	 public void setOpunitcode(int opunitcode) { 
//	 this.opunitcode = opunitcode; 
//	 }

}
