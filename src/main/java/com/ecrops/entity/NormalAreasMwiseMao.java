package com.ecrops.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class NormalAreasMwiseMao {
	
	private String mname;
	@Id
	private String wbvname;
	private Integer normalarea;
	public NormalAreasMwiseMao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NormalAreasMwiseMao(String mname, String wbvname, Integer normalarea) {
		super();
		this.mname = mname;
		this.wbvname = wbvname;
		this.normalarea = normalarea;
	}
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getWbvname() {
		return wbvname;
	}
	public void setWbvname(String wbvname) {
		this.wbvname = wbvname;
	}
	public Integer getNormalarea() {
		return normalarea;
	}
	public void setNormalarea(Integer normalarea) {
		this.normalarea = normalarea;
	}
	@Override
	public String toString() {
		return "NormalAreasMwiseMao [mname=" + mname + ", wbvname=" + wbvname + ", normalarea=" + normalarea + "]";
	}
	

}
