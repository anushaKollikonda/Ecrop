package com.ecrops.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wbvillage_mst")
public class WbMaster {

	@Column(name = "wbedname")
	private String wbedname;

	@Column(name = "wbemname")
	private String wbemname;

	@Column(name = "wbevname")
	private String wbevname;

	@Column(name = "wbdcode")
	private Integer wbdcode;

	@Column(name = "wbmcode")
	private Integer wbmcode;

	@Id
	@Column(name = "wbvcode")
	private Integer wbvcode;
	
	@Column(name = "mcode")
	private Integer mcode;
	


	public WbMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WbMaster(String wbedname, String wbemname, String wbevname, Integer wbdcode, Integer wbmcode,
			Integer wbvcode, Integer mcode) {
		super();
		this.wbedname = wbedname;
		this.wbemname = wbemname;
		this.wbevname = wbevname;
		this.wbdcode = wbdcode;
		this.wbmcode = wbmcode;
		this.wbvcode = wbvcode;
		this.mcode = mcode;
	
	}

	public String getWbedname() {
		return wbedname;
	}

	public void setWbedname(String wbedname) {
		this.wbedname = wbedname;
	}

	public String getWbemname() {
		return wbemname;
	}

	public void setWbemname(String wbemname) {
		this.wbemname = wbemname;
	}

	public String getWbevname() {
		return wbevname;
	}

	public void setWbevname(String wbevname) {
		this.wbevname = wbevname;
	}

	public Integer getWbdcode() {
		return wbdcode;
	}

	public void setWbdcode(Integer wbdcode) {
		this.wbdcode = wbdcode;
	}

	public Integer getWbmcode() {
		return wbmcode;
	}

	public void setWbmcode(Integer wbmcode) {
		this.wbmcode = wbmcode;
	}

	public Integer getWbvcode() {
		return wbvcode;
	}

	public void setWbvcode(Integer wbvcode) {
		this.wbvcode = wbvcode;
	}

	public Integer getMcode() {
		return mcode;
	}

	public void setMcode(Integer mcode) {
		this.mcode = mcode;
	}



	@Override
	public String toString() {
		return "WbMaster [wbedname=" + wbedname + ", wbemname=" + wbemname + ", wbevname=" + wbevname + ", wbdcode="
				+ wbdcode + ", wbmcode=" + wbmcode + ", wbvcode=" + wbvcode + ", mcode=" + mcode +  "]";
	}

	
	
}