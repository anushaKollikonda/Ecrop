package com.ecrops.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RepLandDataDetails {
	private String mname;
	 private Long totrec;
	 private Long downloadedcnt;
	 private Long notdownloadedcnt;
	 private Long totrev;
	 private String wbdname;
	 private String dname;
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer mcode;
	 private Integer dcode;
	public RepLandDataDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RepLandDataDetails(String mname, Long totrec, Long downloadedcnt, Long notdownloadedcnt, Long totrev,
			String wbdname, String dname, Integer mcode, Integer dcode) {
		super();
		this.mname = mname;
		this.totrec = totrec;
		this.downloadedcnt = downloadedcnt;
		this.notdownloadedcnt = notdownloadedcnt;
		this.totrev = totrev;
		this.wbdname = wbdname;
		this.dname = dname;
		this.mcode = mcode;
		this.dcode = dcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Long getTotrec() {
		return totrec;
	}
	public void setTotrec(Long totrec) {
		this.totrec = totrec;
	}
	public Long getDownloadedcnt() {
		return downloadedcnt;
	}
	public void setDownloadedcnt(Long downloadedcnt) {
		this.downloadedcnt = downloadedcnt;
	}
	public Long getNotdownloadedcnt() {
		return notdownloadedcnt;
	}
	public void setNotdownloadedcnt(Long notdownloadedcnt) {
		this.notdownloadedcnt = notdownloadedcnt;
	}
	public Long getTotrev() {
		return totrev;
	}
	public void setTotrev(Long totrev) {
		this.totrev = totrev;
	}
	public String getWbdname() {
		return wbdname;
	}
	public void setWbdname(String wbdname) {
		this.wbdname = wbdname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Integer getMcode() {
		return mcode;
	}
	public void setMcode(Integer mcode) {
		this.mcode = mcode;
	}
	public Integer getDcode() {
		return dcode;
	}
	public void setDcode(Integer dcode) {
		this.dcode = dcode;
	}
	@Override
	public String toString() {
		return "RepLandDataDetails [mname=" + mname + ", totrec=" + totrec + ", downloadedcnt=" + downloadedcnt
				+ ", notdownloadedcnt=" + notdownloadedcnt + ", totrev=" + totrev + ", wbdname=" + wbdname + ", dname="
				+ dname + ", mcode=" + mcode + ", dcode=" + dcode + "]";
	}
	

}
